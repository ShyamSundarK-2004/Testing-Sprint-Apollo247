package com.apollo247.testing.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

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

	// Constructor Injection → gets shared BaseClass instance
	public Hook(BaseClass b) {
		this.b = b;
	}

	ReaderUtilities readerUtil = new ReaderUtilities();

	@Before
	public void setup(Scenario scenario) throws Exception {

		// Read browser from properties file
		String browser = readerUtil.getPropertyKeyValue("browser");

		if (browser.equalsIgnoreCase("chrome")) {

			// Configure Chrome options (Headless mode for faster execution)
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=new");

			Basedriver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("edge")) {

			// Launch Edge browser
			Basedriver = new EdgeDriver();

		} else {
			throw new RuntimeException("Invalid browser: " + browser);
		}

		// Store driver in BaseClass (shared across steps)
		b.setDriver(Basedriver);

		// WebDriver common setup
		initializeDriver(b.getDriver());
		configMaximizeBrowser();
		waitForElements(90);

		// Manage session (login once, reuse across domains)
		SessionManager.ManageSession(b.getDriver());

		// Initialize all page objects
		Pages pages = new Pages(b.getDriver());
		b.setPages(pages);

		// Close any popup if present on dashboard
		pages.dashboardPage.closeDomPopup();

		// Create new test in Extent Report (Scenario level)
		ExtendsReportsUtilities.createTest(scenario.getName());
	}

	@AfterStep
	public void afterStep(Scenario scenario) {

		try {

			// If step fails → capture screenshot + log fail
			if (scenario.isFailed()) {

				String path = new TakeScreenShotUtility().takeScreenShot(b.getDriver(), scenario.getName());

				ExtendsReportsUtilities.fail("Step Failed");
				ExtendsReportsUtilities.attachScreenshot(path);

			} else {

				// If step passes → log success
				ExtendsReportsUtilities.pass("Step Passed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {

		// Flush Extent report (write results to file)
		ExtendsReportsUtilities.flushReport();

		// Close browser and cleanup
//		quitBroswerWindow();
//		b.unload();
	}
}