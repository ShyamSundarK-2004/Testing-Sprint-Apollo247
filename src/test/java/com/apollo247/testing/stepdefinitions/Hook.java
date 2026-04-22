package com.apollo247.testing.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.apollo247.testing.utilities.BaseClass;
import com.apollo247.testing.utilities.ExtendsReportsUtilities;
import com.apollo247.testing.utilities.Pages;
import com.apollo247.testing.utilities.ReaderUtilities;
import com.apollo247.testing.utilities.SessionManager;
import com.apollo247.testing.utilities.TakeScreenShotUtility;
import com.apollo247.testing.utilities.WebdriverUtility;
import com.aventstack.extentreports.Status;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook extends WebdriverUtility {

	private BaseClass b;

	WebDriver Basedriver;

	// dependency Injection
	public Hook(BaseClass b) {
		this.b = b;
	}

	ReaderUtilities readerUtil = new ReaderUtilities();

	@Before
	public void setup(Scenario scenario) throws Exception {
		// reading from property file
		String browser = readerUtil.getPropertyKeyValue("browser");
		// browser setup and launching

		if (browser.equals("chrome")) {
			Basedriver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			Basedriver = new EdgeDriver();
		}

		// set driver instance for parallel execution
		b.setDriver(Basedriver);

		// initialize driver to utlitlies
		initializeDriver(b.getDriver());

		// launching browser in maximize window
		configMaximizeBrowser();

		// adding a implicit wait for the page to load
		waitForElements(70);

		// First run login manually Or if Logged in already use the same
		// sessions/cookies
		SessionManager.ManageSession(b.getDriver());

		// initialize all the pages with driver using page factory
		Pages pages = new Pages(b.getDriver());
		b.setPages(pages);

		pages.dashboardPage.closeDomPopup();

		ExtendsReportsUtilities.createTest(scenario.getName());
		ExtendsReportsUtilities.getTest().log(Status.INFO, "🚀 Test Started: " + scenario.getName());
	}

	@After
	public void teadDown(Scenario scenario) {
		try {
			if (scenario.isFailed()) {

				TakeScreenShotUtility ts = new TakeScreenShotUtility();
				String path = ts.takeScreenShot(b.getDriver(), scenario.getName());

				ExtendsReportsUtilities.getTest().fail("Test Failed");
				ExtendsReportsUtilities.getTest().addScreenCaptureFromPath(path);

			} else {
				ExtendsReportsUtilities.getTest().pass("Test Passed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Flush report after each scenario
		ExtendsReportsUtilities.flushReport();

		quitBroswerWindow();
		b.unload();
	}
}