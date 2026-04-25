package com.apollo247.testing.utilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtilities {

	private Actions action;

	// constructor (VERY IMPORTANT)
	public ActionUtilities(WebDriver driver) {
		this.action = new Actions(driver);
	}

	// Pause action (must perform)
	public void pauseOnAction(long timeInMillis) {
		action.pause(timeInMillis).perform();
	}

	// Click using Actions
	public void clickOnElement(WebElement element) {
		action.moveToElement(element).click().perform();
	}

	// SendKeys using Actions
	public void sendKeys(WebElement element, String value) {
		action.moveToElement(element).click().sendKeys(value).perform();
	}

	// Press ESC (IMPORTANT for your case)
	public void pressEscape() {
		action.sendKeys(Keys.ESCAPE).perform();
	}

	// Click on page (to remove overlay)
	public void clickOnPage() {
		action.moveByOffset(0, 0).click().perform();
	}

	// Navigate dropdown using keyboard
	public void navigateDownDropdown(WebElement element, int count) {
		action.moveToElement(element).click().perform();

		for (int i = 0; i < count; i++) {
			action.sendKeys(Keys.ARROW_DOWN).perform();
		}

		action.sendKeys(Keys.ENTER).perform();
	}

	// Hover
	public void hoverOnElement(WebElement element) {
		action.moveToElement(element).perform();
	}
}