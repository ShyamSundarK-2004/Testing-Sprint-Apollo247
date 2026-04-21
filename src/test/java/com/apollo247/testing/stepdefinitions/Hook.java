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

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook extends WebdriverUtility {

	private BaseClass b;
	WebDriver Basedriver;

	public Hook(BaseClass b) {
		this.b = b;
	}

	ReaderUtilities readerUtil = new ReaderUtilities();

	@Before
	public void setup(Scenario scenario) throws Exception {

		String browser = readerUtil.getPropertyKeyValue("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			Basedriver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			Basedriver = new EdgeDriver();
		} else {
			throw new RuntimeException("Invalid browser: " + browser);
		}

		b.setDriver(Basedriver);

		initializeDriver(b.getDriver());
		configMaximizeBrowser();
		waitForElements(70);

		SessionManager.ManageSession(b.getDriver());

		Pages pages = new Pages(b.getDriver());
		b.setPages(pages);

		pages.dashboardPage.closeDomPopup();

		// Extent Report Start
		ExtendsReportsUtilities.createTest(scenario.getName());
		ExtendsReportsUtilities.info("Test Started: " + scenario.getName());
	}

	@After
	public void teadDown(Scenario scenario) {

		try {

			if (scenario.isFailed()) {

				String path = new TakeScreenShotUtility().takeScreenShot(b.getDriver(), scenario.getName());

				ExtendsReportsUtilities.getTest().fail("Test Failed").addScreenCaptureFromPath(path);

			} else {
				ExtendsReportsUtilities.getTest().pass("Test Passed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// flush report
		ExtendsReportsUtilities.flushReport();

		quitBroswerWindow();
		b.unload();
	}
}