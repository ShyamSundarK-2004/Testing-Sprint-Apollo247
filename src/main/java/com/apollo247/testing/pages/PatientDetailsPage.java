package com.apollo247.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.apollo247.testing.utilities.WebdriverUtility;

public class PatientDetailsPage {

	WebDriver driver;
	WebdriverUtility utilities = new WebdriverUtility();

	public PatientDetailsPage(WebDriver driver) {
		this.driver = driver;
		utilities.initializeDriver(driver);
	}

	// ====== Locators ======

	// add member button
	@FindBy(xpath = "//button[@aria-label='Button' and contains(@class,'L')]")
	private WebElement addMemberBtn;

	// first Name input field
	@FindBy(xpath = "//input[@placeholder='First Name']")
	private WebElement firstNameField;

	// last name input field
	@FindBy(css = "[placeholder='Last name']")
	private WebElement lastNameField;

	// ====== Getters ======

	public WebElement getAddMemberBtn() {
		return addMemberBtn;
	}

	public WebElement getFirstNameField() {
		return firstNameField;
	}

	public WebElement getLastNameField() {
		return lastNameField;
	}

	// ====== Business Logics ======

	public void clickOnAddMember() {
		utilities.waitUntilUrlContains(15L, "selected-patients");
		getAddMemberBtn().click();
	}

	public void enterFirstName(String patientName) {
		getFirstNameField().sendKeys(patientName);
	}

	public void enterLastName(String lastName) {
		getLastNameField().sendKeys(lastName);
	}
}
