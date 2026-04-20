package com.apollo247.testing.utilities;

import org.openqa.selenium.WebDriver;

public class BaseClass {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static ThreadLocal<Pages> pages = new ThreadLocal<Pages>();

	// Set driver
	public void setDriver(WebDriver driverInstance) {
		driver.set(driverInstance);
	}

	// Set Pages instance
	public void setPages(Pages pagesInstance) {
		pages.set(pagesInstance);
	}

	// Get driver
	public WebDriver getDriver() {
		return driver.get();
	}

	// Get pages instance
	public Pages getPages() {
		return pages.get();
	}

	// Remove driver and pages instance
	public void unload() {
		driver.remove();
		pages.remove();
	}

}