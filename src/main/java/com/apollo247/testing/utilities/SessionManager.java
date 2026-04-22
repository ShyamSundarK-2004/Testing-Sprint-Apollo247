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
	private static final String MAIN_LOGIN_URL = "https://www.apollo247.com";

	// ========================= MAIN ENTRY =========================

	public static void ManageSession(WebDriver driver) throws Exception {
		createSessionDir();

		if (isValidSessionExists()) {
			loadSession(driver);
		} else {
			driver.get(MAIN_LOGIN_URL);
			Thread.sleep(90000); // Manual OTP once
			saveSession(driver);
		}

		Thread.sleep(3000);
	}

	// ========================= SWITCH DOMAIN =========================

	public static void switchToDomain(WebDriver driver, String domainUrl) throws Exception {

		driver.get(domainUrl);
		Thread.sleep(2000);

		injectCookiesForDomain(driver, domainUrl);
		Thread.sleep(2000);

		loadLocalStorage(driver);
		loadSessionStorage(driver);

		activateSSO(driver);

		driver.navigate().refresh();
		Thread.sleep(4000);
	}

	// ========================= LOAD SESSION =========================

	private static void loadSession(WebDriver driver) throws Exception {

		driver.get(MAIN_LOGIN_URL);
		Thread.sleep(2000);

		loadAllCookies(driver);
		loadLocalStorage(driver);
		loadSessionStorage(driver);

		driver.navigate().refresh();
		Thread.sleep(3000);

		activateSSO(driver);
	}

	// ========================= COOKIE INJECTION =========================

	private static void injectCookiesForDomain(WebDriver driver, String domainUrl) throws Exception {

		if (!Files.exists(Paths.get(SESSION_DIR + "/cookies.data")))
			return;

		ObjectInputStream input = new ObjectInputStream(new FileInputStream(SESSION_DIR + "/cookies.data"));

		@SuppressWarnings("unchecked")
		Set<Cookie> cookies = (Set<Cookie>) input.readObject();
		input.close();

		String targetDomain = domainUrl.replace("https://", "").replace("http://", "").replace("www.", "");

		for (Cookie cookie : cookies) {

			if (cookie.getDomain() == null)
				continue;

			String cookieDomain = cookie.getDomain().replace(".", "");

			if (targetDomain.contains(cookieDomain) || cookieDomain.contains(targetDomain)) {

				try {
					Cookie.Builder builder = new Cookie.Builder(cookie.getName(), cookie.getValue())
							.domain(cookie.getDomain()).path("/").isSecure(true).isHttpOnly(cookie.isHttpOnly());

					Date expiry = cookie.getExpiry();
					if (expiry != null && expiry.after(new Date())) {
						builder.expiresOn(expiry);
					}

					driver.manage().addCookie(builder.build());

				} catch (Exception ignored) {
				}
			}
		}
	}

	private static void loadAllCookies(WebDriver driver) throws Exception {

		if (!Files.exists(Paths.get(SESSION_DIR + "/cookies.data")))
			return;

		ObjectInputStream input = new ObjectInputStream(new FileInputStream(SESSION_DIR + "/cookies.data"));

		@SuppressWarnings("unchecked")
		Set<Cookie> cookies = (Set<Cookie>) input.readObject();
		input.close();

		for (Cookie cookie : cookies) {

			if (cookie.getDomain() == null)
				continue;

			try {
				Cookie.Builder builder = new Cookie.Builder(cookie.getName(), cookie.getValue())
						.domain(cookie.getDomain()).path("/").isSecure(true).isHttpOnly(cookie.isHttpOnly());

				Date expiry = cookie.getExpiry();
				if (expiry != null && expiry.after(new Date())) {
					builder.expiresOn(expiry);
				}

				driver.manage().addCookie(builder.build());

			} catch (Exception ignored) {
			}
		}
	}

	// ========================= STORAGE =========================

	private static void loadLocalStorage(WebDriver driver) throws Exception {
		String file = SESSION_DIR + "/localStorage.json";
		if (!Files.exists(Paths.get(file)))
			return;

		String json = Files.readString(Paths.get(file));
		JsonObject storage = new Gson().fromJson(json, JsonObject.class);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (String key : storage.keySet()) {
			try {
				String value = storage.get(key).getAsString();
				String encoded = Base64.getEncoder().encodeToString(value.getBytes("UTF-8"));

				js.executeScript("localStorage.setItem('" + key.replace("'", "\\'") + "', atob('" + encoded + "'));");

			} catch (Exception ignored) {
			}
		}
	}

	private static void loadSessionStorage(WebDriver driver) throws Exception {
		String file = SESSION_DIR + "/sessionStorage.json";
		if (!Files.exists(Paths.get(file)))
			return;

		String json = Files.readString(Paths.get(file));
		JsonObject storage = new Gson().fromJson(json, JsonObject.class);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (String key : storage.keySet()) {
			try {
				String value = storage.get(key).getAsString();
				String encoded = Base64.getEncoder().encodeToString(value.getBytes("UTF-8"));

				js.executeScript("sessionStorage.setItem('" + key.replace("'", "\\'") + "', atob('" + encoded + "'));");

			} catch (Exception ignored) {
			}
		}
	}

	// ========================= SSO =========================

	private static void activateSSO(WebDriver driver) throws Exception {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String[] endpoints = { "/api/v1/user/profile", "/api/auth/profile", "/api/user/profile" };

		for (String endpoint : endpoints) {
			js.executeScript("fetch('" + endpoint + "',{credentials:'include'}).catch(()=>{});");
			Thread.sleep(500);
		}

		js.executeScript("""
				try {
				    localStorage.setItem('authChecked', 'true');
				    sessionStorage.setItem('userLoggedIn', 'true');
				    sessionStorage.setItem('ssoActive', 'true');
				} catch(e) {}
				""");
	}

	// ========================= SAVE SESSION =========================

	private static void saveSession(WebDriver driver) throws Exception {

		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(5000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("fetch('/api/user/profile',{credentials:'include'});");
		Thread.sleep(3000);

		Set<Cookie> cookies = driver.manage().getCookies();

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SESSION_DIR + "/cookies.data"))) {
			oos.writeObject(cookies);
		}

		// ✅ SAFE localStorage extraction
		String localScript = """
		    var data = {};
		    for (var i = 0; i < localStorage.length; i++) {
		        var key = localStorage.key(i);
		        data[key] = localStorage.getItem(key);
		    }
		    return JSON.stringify(data);
		""";

		String localJson = (String) js.executeScript(localScript);
		Files.writeString(Paths.get(SESSION_DIR + "/localStorage.json"), localJson);

		// ✅ SAFE sessionStorage extraction
		String sessionScript = """
		    var data = {};
		    for (var i = 0; i < sessionStorage.length; i++) {
		        var key = sessionStorage.key(i);
		        data[key] = sessionStorage.getItem(key);
		    }
		    return JSON.stringify(data);
		""";

		String sessionJson = (String) js.executeScript(sessionScript);
		Files.writeString(Paths.get(SESSION_DIR + "/sessionStorage.json"), sessionJson);
	}

	// ========================= UTIL =========================

	private static void createSessionDir() throws IOException {
		Files.createDirectories(Paths.get(SESSION_DIR));
	}

	private static boolean isValidSessionExists() {
		return Files.exists(Paths.get(SESSION_DIR + "/localStorage.json"))
				&& Files.exists(Paths.get(SESSION_DIR + "/cookies.data"));
	}
}