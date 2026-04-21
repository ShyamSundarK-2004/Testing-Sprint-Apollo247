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
			System.out.println("🔄 LOADING FULL SESSION FOR ALL DOMAINS...");
			loadFullSession(driver);
		} else {
			System.out.println("📱 MANUAL LOGIN REQUIRED - Complete OTP then wait 90s");
			driver.get(MAIN_LOGIN_URL);
			Thread.sleep(90000); // Wait for manual OTP completion
			saveFullSession(driver);
		}

		verifyLogin(driver);
		Thread.sleep(5000);
		System.out.println("✅ SESSION MANAGER READY!");
	}

	public static void injectSessionToDomain(WebDriver driver, String domainUrl) throws Exception {
		System.out.println("\n🚀 INJECTING SESSION TO: " + domainUrl);

		// 1. Navigate to domain
		driver.get(domainUrl);
		Thread.sleep(2000);

		// 2. Clear existing cookies
		driver.manage().deleteAllCookies();
		Thread.sleep(1000);

		// 3. Load domain-specific cookies
		loadCookiesForDomainStrict(driver, domainUrl);
		Thread.sleep(2000);

		// 4. Refresh to activate cookies
		driver.navigate().refresh();
		Thread.sleep(4000);

		// 5. Load storages
		loadLocalStorage(driver);
		loadSessionStorage(driver);
		Thread.sleep(2000);

		// 6. Activate SSO
		activateFullSSO(driver, domainUrl);

		System.out.println("✅ SESSION INJECTION COMPLETE: " + domainUrl);
		verifyDomainLogin(driver, domainUrl);
	}

	// 🔥 PERFECTLY FIXED COOKIE LOADER
	private static void loadCookiesForDomainStrict(WebDriver driver, String targetUrl) throws Exception {
		String targetDomain = extractDomainStrict(targetUrl);
		System.out.println("🍪 Loading cookies for: " + targetDomain);

		if (!Files.exists(Paths.get(SESSION_DIR + "/cookies.data"))) {
			System.out.println("⚠️ No cookies.data found!");
			return;
		}

		ObjectInputStream input = new ObjectInputStream(new FileInputStream(SESSION_DIR + "/cookies.data"));
		@SuppressWarnings("unchecked")
		Set<Cookie> cookies = (Set<Cookie>) input.readObject();
		input.close();

		int loaded = 0, skipped = 0;

		for (Cookie cookie : cookies) {
			// Skip invalid cookies
			if (cookie.getDomain() == null || cookie.getName() == null || cookie.getValue() == null) {
				skipped++;
				continue;
			}

			String cookieDomain = cookie.getDomain().toLowerCase();
			String urlDomain = targetDomain.toLowerCase();

			// Domain matching logic
			boolean domainMatch = cookieDomain.equals(urlDomain) || cookieDomain.equals("." + urlDomain)
					|| urlDomain.equals("." + cookieDomain) || urlDomain.contains(cookieDomain)
					|| cookieDomain.contains(urlDomain);

			if (domainMatch) {
				try {
					Cookie.Builder builder = new Cookie.Builder(cookie.getName(), cookie.getValue())
							.domain(cookie.getDomain()).path(cookie.getPath() != null ? cookie.getPath() : "/");

					// ✅ SECURE FLAG: Auto-detect from HTTPS
					builder.isSecure(targetUrl.startsWith("https://"));

					// ✅ EXPIRY: Only if valid future date
					Date expiry = cookie.getExpiry();
					if (expiry != null && expiry.after(new Date())) {
						builder.expiresOn(expiry);
					}

					// ✅ HTTPONLY & SAMESITE
					builder.isHttpOnly(cookie.isHttpOnly());
					String sameSite = cookie.getSameSite();
					builder.sameSite(sameSite != null && !sameSite.isEmpty() ? sameSite : "Lax");

					Cookie newCookie = builder.build();
					driver.manage().addCookie(newCookie);
					loaded++;

					String type = (expiry != null) ? "persistent" : "session";
					System.out.println("   ✅ " + cookie.getName() + " [" + type + "]");

				} catch (Exception e) {
					skipped++;
					System.out.println("   ❌ " + cookie.getName() + " skipped: " + e.getClass().getSimpleName());
				}
			}
		}
		System.out.println("🍪 RESULT: " + loaded + "/" + cookies.size() + " loaded (" + skipped + " skipped)");
	}

	private static String extractDomainStrict(String url) {
		String domain = url.replace("https://", "").replace("http://", "");
		if (domain.startsWith("www."))
			domain = domain.substring(4);

		if (domain.contains("apollopharmacy.in"))
			return "apollopharmacy.in";
		if (domain.contains("apollo247insurance.com"))
			return "apollo247insurance.com";
		if (domain.contains("apollo247.com"))
			return "apollo247.com";
		return "apollo247.com";
	}

	private static void activateFullSSO(WebDriver driver, String domainUrl) throws Exception {
		System.out.println("⚡ ACTIVATING SSO...");

		String[] endpoints = { "/api/v1/user/profile", "/api/auth/profile", "/api/user/profile", "/api/v1/auth/me",
				"/api/me", "/api/account" };

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Trigger auth endpoints
		for (String endpoint : endpoints) {
			String script = "fetch('" + domainUrl + endpoint + "', {credentials: 'include'}).catch(()=>{});";
			js.executeScript(script);
			Thread.sleep(800);
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
		Thread.sleep(2000);

		driver.navigate().refresh();
		Thread.sleep(3000);
	}

	private static void loadFullSession(WebDriver driver) throws Exception {
		// Load main domain first
		injectSessionToDomain(driver, MAIN_LOGIN_URL);

		// Pre-warm other domains
		for (String domain : APOLLO_DOMAINS) {
			if (!domain.equals(MAIN_LOGIN_URL)) {
				System.out.println("\n🔥 Pre-warming: " + domain);
				injectSessionToDomain(driver, domain);
				driver.get(MAIN_LOGIN_URL); // Return to main
				Thread.sleep(1000);
			}
		}

		driver.get(MAIN_LOGIN_URL);
		System.out.println("✅ ALL DOMAINS LOADED!");
	}

	private static void loadCookies(WebDriver driver) throws Exception {
		loadCookiesForDomainStrict(driver, MAIN_LOGIN_URL);
	}

	private static void loadLocalStorage(WebDriver driver) throws Exception {
		String file = SESSION_DIR + "/localStorage.json";
		if (!Files.exists(Paths.get(file)))
			return;

		String json = Files.readString(Paths.get(file));
		Gson gson = new Gson();
		JsonObject storage = gson.fromJson(json, JsonObject.class);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		int count = 0;

		for (String key : storage.keySet()) {
			try {
				String value = storage.get(key).getAsString();
				String encoded = Base64.getEncoder().encodeToString(value.getBytes("UTF-8"));
				String script = "try{localStorage.setItem('" + key.replace("'", "\\'") + "',atob('" + encoded
						+ "'));}catch(e){}";
				js.executeScript(script);
				count++;
			} catch (Exception ignored) {
			}
		}
		System.out.println("💾 LocalStorage: " + count + " items");
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
		System.out.println("📦 SessionStorage loaded");
	}

	private static void createSessionDir() throws IOException {
		Files.createDirectories(Paths.get(SESSION_DIR));
	}

	private static boolean isValidSessionExists() {
		return Files.exists(Paths.get(SESSION_DIR + "/localStorage.json"))
				&& Files.exists(Paths.get(SESSION_DIR + "/cookies.data"));
	}

	// 🔥 PERFECT SAVE METHOD
	private static void saveFullSession(WebDriver driver) throws Exception {
		System.out.println("💾 SAVING FULL SESSION...");

		// Wait + refresh for complete cookie set
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(5000);

		// Trigger auth to capture all cookies
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("fetch('/api/user/profile',{credentials:'include'});");
		Thread.sleep(3000);

		// Save cookies
		Set<Cookie> cookies = driver.manage().getCookies();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SESSION_DIR + "/cookies.data"))) {
			oos.writeObject(cookies);
		}
		System.out.println("🍪 Saved " + cookies.size() + " cookies");

		// Save localStorage
		String localScript = "return JSON.stringify(Object.fromEntries([...localStorage]));";
		String localJson = (String) js.executeScript(localScript);
		Files.writeString(Paths.get(SESSION_DIR + "/localStorage.json"), localJson);

		// Save sessionStorage
		String sessionScript = "return JSON.stringify(Object.fromEntries([...sessionStorage]));";
		String sessionJson = (String) js.executeScript(sessionScript);
		Files.writeString(Paths.get(SESSION_DIR + "/sessionStorage.json"), sessionJson);

		System.out.println("✅ SESSION SAVED SUCCESSFULLY!");
		System.out.println("   📁 Delete 'apollo247_session' folder to re-login");
	}

	private static void verifyLogin(WebDriver driver) {
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		System.out.println("\n🔍 VERIFICATION:");
		System.out.println("   URL: " + url);
		System.out.println("   Title: " + title);
		System.out.println("   Logged In: " + (!url.contains("login") && !url.contains("otp")));
	}

	private static void verifyDomainLogin(WebDriver driver, String domain) {
		String url = driver.getCurrentUrl();
		boolean success = !url.contains("login") && !url.contains("otp")
				&& (url.contains("apollo") || url.contains("dashboard"));
		System.out.println("✅ " + domain + ": " + (success ? "LOGGED IN ✅" : "CHECK MANUALLY ⚠️"));
	}
}