package com.apollo247.testing.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.apollo247.testing.utilities.BaseClass;
import com.apollo247.testing.utilities.Pages;
import com.apollo247.testing.utilities.ReaderUtilities;
import com.apollo247.testing.utilities.SessionManager;
import com.apollo247.testing.utilities.WebdriverUtility;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook extends WebdriverUtility {

	private BaseClass b;

	WebDriver Basedriver;

	// dependency Injection
	public Hook(BaseClass b) {
		this.b = b;
	}

	ReaderUtilities readerUtil = new ReaderUtilities();

	@Before
	public void setup() throws Exception {
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
		waitForElements(40);

		// First run login manually Or if Logged in already use the same
		// sessions/cookies
		SessionManager.ManageSession(b.getDriver());

		// initialize all the pages with driver using page factory
		Pages pages = new Pages(b.getDriver());
		b.setPages(pages);
		pages.dashboardPage.closeDomPopup();
	}

	@After
	public void teadDown() {
		quitBroswerWindow();
		b.unload();
	}
}