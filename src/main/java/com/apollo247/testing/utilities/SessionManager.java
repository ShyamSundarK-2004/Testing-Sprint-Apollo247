package com.apollo247.testing.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SessionManager {

	private static final String SESSION_DIR = "apollo247_session/";
	private static final String APOLLO_URL = "https://www.apollo247.com";

	public static void ManageSession(WebDriver driver) throws Exception {

		createSessionDir();

		if (isValidSessionExists()) {
			System.out.println("🔄 LOADING FULL SESSION...");
			loadFullSession(driver);
		} else {
			System.out.println("📱 MANUAL LOGIN - Complete OTP then wait 90s");
			driver.get(APOLLO_URL);
			Thread.sleep(90000); // 90 seconds for manual login
			saveFullSession(driver);
		}

		verifyLogin(driver);
		Thread.sleep(10000); // Stay logged in to verify

	}

	private static void createSessionDir() throws IOException {
		Files.createDirectories(Paths.get(SESSION_DIR));
	}

	private static boolean isValidSessionExists() {
		return Files.exists(Paths.get(SESSION_DIR + "localStorage.json"))
				&& Files.exists(Paths.get(SESSION_DIR + "cookies.data"));
	}

	private static void loadFullSession(WebDriver driver) throws Exception {
		System.out.println("🌐 Navigating to Apollo247...");
		driver.get(APOLLO_URL);
		Thread.sleep(3000);

		// 1. COOKIES FIRST
		loadCookies(driver);

		// 2. LOCALSTORAGE (ESCAPED PROPERLY)
		loadLocalStorage(driver);

		// 3. SESSIONSTORAGE
		loadSessionStorage(driver);

		// 4. FINAL REFRESH
		System.out.println("🔄 Refreshing with session...");
		driver.navigate().to(APOLLO_URL);
		Thread.sleep(10000); // Apollo247 needs time
	}

	private static void loadCookies(WebDriver driver) throws Exception {
		driver.manage().deleteAllCookies();
		Thread.sleep(1000);

		ObjectInputStream input = new ObjectInputStream(new FileInputStream(SESSION_DIR + "cookies.data"));
		Set<Cookie> cookies = (Set<Cookie>) input.readObject();
		input.close();

		int count = 0;
		for (Cookie cookie : cookies) {
			if (cookie.getDomain() != null
					&& (cookie.getDomain().contains("apollo247.com") || cookie.getDomain().contains("apollo247"))) {
				driver.manage().addCookie(cookie);
				count++;
			}
		}
		System.out.println("🍪 Loaded " + count + "/" + cookies.size() + " Apollo cookies");
	}

	// 🔥 FIXED: BASE64 + PROPER ESCAPING
	private static void loadLocalStorage(WebDriver driver) throws Exception {
		String localStorageJson = Files.readString(Paths.get(SESSION_DIR + "localStorage.json"));

		Gson gson = new Gson();
		JsonObject storage = gson.fromJson(localStorageJson, JsonObject.class);

		int loaded = 0;
		for (String key : storage.keySet()) {
			try {
				String value = storage.get(key).getAsString();

				// BASE64 encode to avoid ALL escaping issues
				String encodedValue = Base64.getEncoder().encodeToString(value.getBytes());

				// SAFE JavaScript injection
				String script = "try { " + "var decoded = atob('" + encodedValue + "'); " + "localStorage.setItem('"
						+ key.replace("'", "\\'") + "', decoded); " + "} catch(e) { console.log('Failed to set: " + key
						+ "'); }";

				((JavascriptExecutor) driver).executeScript(script);
				loaded++;

			} catch (Exception e) {
				System.out.println("⚠️ Skipped localStorage key: " + key);
			}
		}
		System.out.println("💾 Loaded " + loaded + "/" + storage.size() + " localStorage items");
	}

	private static void loadSessionStorage(WebDriver driver) throws Exception {
		if (!Files.exists(Paths.get(SESSION_DIR + "sessionStorage.json"))) {
			return;
		}

		String sessionStorageJson = Files.readString(Paths.get(SESSION_DIR + "sessionStorage.json"));

		Gson gson = new Gson();
		JsonObject storage = gson.fromJson(sessionStorageJson, JsonObject.class);

		for (String key : storage.keySet()) {
			try {
				String value = storage.get(key).getAsString();
				String encodedValue = Base64.getEncoder().encodeToString(value.getBytes());

				String script = "try { " + "var decoded = atob('" + encodedValue + "'); " + "sessionStorage.setItem('"
						+ key.replace("'", "\\'") + "', decoded); " + "} catch(e) {}";

				((JavascriptExecutor) driver).executeScript(script);
			} catch (Exception e) {
				// Ignore sessionStorage errors
			}
		}
		System.out.println("📦 SessionStorage loaded");
	}

	private static void saveFullSession(WebDriver driver) throws Exception {
		Thread.sleep(5000);

		// 1. COOKIES
		Set<Cookie> cookies = driver.manage().getCookies();
		ObjectOutputStream cookieOut = new ObjectOutputStream(new FileOutputStream(SESSION_DIR + "cookies.data"));
		cookieOut.writeObject(cookies);
		cookieOut.close();

		// 2. LOCALSTORAGE - BASE64 SAFE
		String localScript = "var items = {}; " + "for(var i = 0; i < localStorage.length; i++) { "
				+ "  var k = localStorage.key(i); " + "  if(k) items[k] = localStorage.getItem(k); " + "} "
				+ "return JSON.stringify(items);";

		String localStorageJson = (String) ((JavascriptExecutor) driver).executeScript(localScript);
		Files.writeString(Paths.get(SESSION_DIR + "localStorage.json"), localStorageJson);

		// 3. SESSIONSTORAGE
		String sessionScript = "var items = {}; " + "for(var i = 0; i < sessionStorage.length; i++) { "
				+ "  var k = sessionStorage.key(i); " + "  if(k) items[k] = sessionStorage.getItem(k); " + "} "
				+ "return JSON.stringify(items);";

		String sessionStorageJson = (String) ((JavascriptExecutor) driver).executeScript(sessionScript);
		Files.writeString(Paths.get(SESSION_DIR + "sessionStorage.json"), sessionStorageJson);

		System.out.println("✅ FULL SESSION SAVED!");
		System.out.println("📁 Check folder: " + SESSION_DIR);
	}

	private static void verifyLogin(WebDriver driver) {
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();

		System.out.println("\n🔍 VERIFICATION:");
		System.out.println("🌐 URL: " + url);
		System.out.println("📄 Title: " + title);

		// Check logged-in state
		boolean loggedIn = !url.contains("login") && !url.contains("otp") && !url.contains("auth")
				&& (title.contains("Apollo") || title.contains("24/7") || url.contains("dashboard")
						|| url.contains("profile"));

		if (loggedIn) {
			System.out.println("🎉 SUCCESS! LOGGED IN ✅");
		} else {
			System.out.println("❌ LOGIN FAILED");
			System.out.println("💡 Delete 'apollo247_session/' and retry manual login");
		}
	}

}