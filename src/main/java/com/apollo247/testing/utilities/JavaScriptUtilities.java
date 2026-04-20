package com.apollo247.testing.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtilities {

	JavascriptExecutor js;

	public JavaScriptUtilities(WebDriver driver) {
		this.js = (JavascriptExecutor) driver;
	}

	public void jsClick(WebElement element) {
		js.executeScript("arguments[0].click()", element);
	}

	public void jsScrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	public void scrollByPixels(int pixels) {
		js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
	}

}