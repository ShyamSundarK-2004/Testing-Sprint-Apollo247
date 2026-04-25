package com.apollo247.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.apollo247.testing.utilities.WebdriverUtility;

public class TestCartPage {

	WebDriver driver;
	WebdriverUtility utilities = new WebdriverUtility();

	public TestCartPage(WebDriver driver) {
		this.driver = driver;
		utilities.initializeDriver(driver);
	}

	// ====== Locators ======

	// click on book test button
	@FindBy(xpath = "//button[.//span[normalize-space() ='Book Tests']]")
	private WebElement bookTestBtn;

	// click on update and proceed button
	@FindBy(xpath = "//button[contains(normalize-space(),'Update')]")
	private WebElement updateAndProceedBtn;

	// click on proceed to pay button
	@FindBy(xpath = "//button[.//span[contains(normalize-space(),'Proceed to Pay')]]")
	private WebElement proceedToPay;

	// ====== Getters ======

	public WebElement getBookTestBtn() {
		return bookTestBtn;
	}

	public WebElement getUpdateAndProceedBtn() {
		return updateAndProceedBtn;
	}

	public WebElement getProceedToPay() {
		return proceedToPay;
	}

	// ====== Business Logics ======

	public void clickOnBookTestBtn() {
		getBookTestBtn().click();
	}

	public void clickOnUpdateAndProceedbtn() {
		utilities.waitUntilElementIsCLickable(15L, getUpdateAndProceedBtn());
		getUpdateAndProceedBtn().click();
	}

	public void clickOnProceedToPay() {
		utilities.waitUntilElementIsCLickable(15L, getProceedToPay());
		getProceedToPay().click();
	}

	public String getCurrentPageUrl() {
		utilities.waitUntilUrlContains(15L, "payments");
		return utilities.fetchApplicationURL();
	}

}
