package com.apollo247.testing.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.apollo247.testing.utilities.BaseClass;
import com.apollo247.testing.utilities.ExtendsReportsUtilities;
import com.apollo247.testing.utilities.Pages;
import com.apollo247.testing.utilities.ReaderUtilities;
import com.apollo247.testing.utilities.SessionManager;
import com.apollo247.testing.utilities.TakeScreenShotUtility;
import com.apollo247.testing.utilities.WebdriverUtility;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook extends WebdriverUtility {

	private BaseClass b;
	WebDriver Basedriver;

	// Constructor Injection gets shared BaseClass instance
	public Hook(BaseClass b) {
		this.b = b;
	}

	ReaderUtilities readerUtil = new ReaderUtilities();

	@Before
	public void setup(Scenario scenario) throws Exception {

		// Read browser from properties file
		String browser = readerUtil.getPropertyKeyValue("browser");
		// String serverUrl = readerUtil.getPropertyKeyValue("serverUrl");

		if (browser.equalsIgnoreCase("chrome")) {
			// launching browsers in selenium grid
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless=new");
			// creating remotewebdriver to handle all browsers
			// Basedriver = new RemoteWebDriver(new URL(serverUrl), chromeOptions);

			Basedriver = new ChromeDriver(chromeOptions);

		} else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--headless=new");
			// creating remotewebdriver to handle all browsers
			// Basedriver = new RemoteWebDriver(new URL(serverUrl), edgeOptions);

			Basedriver = new EdgeDriver(edgeOptions);

		} else {
			throw new RuntimeException("Invalid browser: " + browser);
		}

		// Store driver in BaseClass
		b.setDriver(Basedriver);

		// WebDriver common setup
		initializeDriver(b.getDriver());
		configMaximizeBrowser();
		waitForElements(70);

		// Manage session
		SessionManager.ManageSession(b.getDriver());

		// Initialize all page objects
		Pages pages = new Pages(b.getDriver());
		b.setPages(pages);

		// Close any popup if present on dashboard
		pages.dashboardPage.closeDomPopup();

		// Create new test in Extent Report for each scenario
		ExtendsReportsUtilities.createTest(scenario.getName());
	}

	@AfterStep
	public void afterStep(Scenario scenario) {

		try {

			// Scenario fails
			if (scenario.isFailed()) {

				String path = new TakeScreenShotUtility().takeScreenShot(b.getDriver(), scenario.getName());
				// capture screenshot and log fail
				ExtendsReportsUtilities.fail("Step Failed");
				ExtendsReportsUtilities.attachScreenshot(path);

			} else if (scenario.getStatus().name().equals("SKIPPED")) {
				ExtendsReportsUtilities.skip("Step Skipped");
			} else {
				// step passes
				ExtendsReportsUtilities.pass("Step executed successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {

		// Flush Extent report
		ExtendsReportsUtilities.flushReport();

		// Close browser and cleanup driver instance
		quitBroswerWindow();
		b.unload();
	}
}