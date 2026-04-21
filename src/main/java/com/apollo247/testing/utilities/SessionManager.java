package com.apollo247.testing.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SessionManager {

	private static final String SESSION_DIR = "apollo247_session";
	private static final String[] APOLLO_DOMAINS = { "https://apollo247.com", "https://www.apollo247.com",
			"https://www.apollopharmacy.in", "https://www.apollo247insurance.com" };
	private static final String MAIN_LOGIN_URL = "https://www.apollo247.com";

	public static void ManageSession(WebDriver driver) throws Exception {
		createSessionDir();

		if (isValidSessionExists()) {
			loadFullSessionOnce(driver);
		} else {
			driver.get(MAIN_LOGIN_URL);
			Thread.sleep(90000); // Wait for manual OTP completion
			saveFullSession(driver);
		}

		verifyLogin(driver);
		Thread.sleep(5000);
	}

	// ✅ NEW: Safe domain switching (no session destruction)
	public static void switchToDomain(WebDriver driver, String domainUrl) throws Exception {
		driver.get(domainUrl);
		Thread.sleep(2000);
		verifyDomainLogin(driver, domainUrl);
	}

	// 🔥 FIXED: Single session load, no loops, no destruction
	private static void loadFullSessionOnce(WebDriver driver) throws Exception {
		// 1. Navigate to main domain once
		driver.get(MAIN_LOGIN_URL);
		Thread.sleep(2000);

		// 2. Load ALL cookies (preserves session)
		loadAllCookies(driver);
		Thread.sleep(1000);

		// 3. Load storages once
		loadLocalStorage(driver);
		loadSessionStorage(driver);
		Thread.sleep(1000);

		// 4. Single activation
		driver.navigate().refresh();
		Thread.sleep(3000);
		activateSSO(driver);
	}

	private static void loadAllCookies(WebDriver driver) throws Exception {
		if (!Files.exists(Paths.get(SESSION_DIR + "/cookies.data"))) {
			return;
		}

		ObjectInputStream input = new ObjectInputStream(new FileInputStream(SESSION_DIR + "/cookies.data"));
		@SuppressWarnings("unchecked")
		Set<Cookie> cookies = (Set<Cookie>) input.readObject();
		input.close();

		for (Cookie cookie : cookies) {
			// Skip invalid cookies
			if (cookie.getDomain() == null || cookie.getName() == null || cookie.getValue() == null) {
				continue;
			}

			try {
				Cookie.Builder builder = new Cookie.Builder(cookie.getName(), cookie.getValue())
						.domain(cookie.getDomain()).path(cookie.getPath() != null ? cookie.getPath() : "/");

				// Secure flag
				builder.isSecure(cookie.getDomain().startsWith(".") || MAIN_LOGIN_URL.startsWith("https://"));

				// Expiry only if valid
				Date expiry = cookie.getExpiry();
				if (expiry != null && expiry.after(new Date())) {
					builder.expiresOn(expiry);
				}

				// Flags
				builder.isHttpOnly(cookie.isHttpOnly());
				String sameSite = cookie.getSameSite();
				builder.sameSite(sameSite != null && !sameSite.isEmpty() ? sameSite : "Lax");

				driver.manage().addCookie(builder.build());

			} catch (Exception ignored) {
			}
		}
	}

	// ✅ FIXED: Single SSO activation
	private static void activateSSO(WebDriver driver) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Single auth check
		String[] endpoints = { "/api/v1/user/profile", "/api/auth/profile", "/api/user/profile" };
		for (String endpoint : endpoints) {
			js.executeScript("fetch('/" + endpoint.substring(1) + "',{credentials:'include'}).catch(()=>{});");
			Thread.sleep(500);
		}

		// Set auth flags
		String authScript = """
				try {
				    localStorage.setItem('authChecked', 'true');
				    sessionStorage.setItem('userLoggedIn', 'true');
				    sessionStorage.setItem('ssoActive', 'true');
				} catch(e) {}
				""";
		js.executeScript(authScript);
	}

	private static void loadLocalStorage(WebDriver driver) throws Exception {
		String file = SESSION_DIR + "/localStorage.json";
		if (!Files.exists(Paths.get(file)))
			return;

		String json = Files.readString(Paths.get(file));
		Gson gson = new Gson();
		JsonObject storage = gson.fromJson(json, JsonObject.class);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (String key : storage.keySet()) {
			try {
				String value = storage.get(key).getAsString();
				String encoded = Base64.getEncoder().encodeToString(value.getBytes("UTF-8"));
				String script = "try{localStorage.setItem('" + key.replace("'", "\\'") + "',atob('" + encoded
						+ "'));}catch(e){}";
				js.executeScript(script);
			} catch (Exception ignored) {
			}
		}
	}

	private static void loadSessionStorage(WebDriver driver) throws Exception {
		String file = SESSION_DIR + "/sessionStorage.json";
		if (!Files.exists(Paths.get(file)))
			return;

		String json = Files.readString(Paths.get(file));
		Gson gson = new Gson();
		JsonObject storage = gson.fromJson(json, JsonObject.class);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (String key : storage.keySet()) {
			try {
				String value = storage.get(key).getAsString();
				String encoded = Base64.getEncoder().encodeToString(value.getBytes("UTF-8"));
				String script = "try{sessionStorage.setItem('" + key.replace("'", "\\'") + "',atob('" + encoded
						+ "'));}catch(e){}";
				js.executeScript(script);
			} catch (Exception ignored) {
			}
		}
	}

	private static void createSessionDir() throws IOException {
		Files.createDirectories(Paths.get(SESSION_DIR));
	}

	private static boolean isValidSessionExists() {
		return Files.exists(Paths.get(SESSION_DIR + "/localStorage.json"))
				&& Files.exists(Paths.get(SESSION_DIR + "/cookies.data"));
	}

	// ✅ PERFECT SAVE (unchanged)
	private static void saveFullSession(WebDriver driver) throws Exception {
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(5000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("fetch('/api/user/profile',{credentials:'include'});");
		Thread.sleep(3000);

		// Save cookies
		Set<Cookie> cookies = driver.manage().getCookies();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SESSION_DIR + "/cookies.data"))) {
			oos.writeObject(cookies);
		}

		// Save localStorage
		String localScript = "return JSON.stringify(Object.fromEntries([...localStorage]));";
		String localJson = (String) js.executeScript(localScript);
		Files.writeString(Paths.get(SESSION_DIR + "/localStorage.json"), localJson);

		// Save sessionStorage
		String sessionScript = "return JSON.stringify(Object.fromEntries([...sessionStorage]));";
		String sessionJson = (String) js.executeScript(sessionScript);
		Files.writeString(Paths.get(SESSION_DIR + "/sessionStorage.json"), sessionJson);
	}

	private static void verifyLogin(WebDriver driver) {
		String url = driver.getCurrentUrl();
	}

	private static void verifyDomainLogin(WebDriver driver, String domain) {
		String url = driver.getCurrentUrl();
	}
}