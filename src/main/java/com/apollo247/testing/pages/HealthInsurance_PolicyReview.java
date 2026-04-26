package com.apollo247.testing.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.apollo247.testing.utilities.JavaScriptUtilities;
import com.apollo247.testing.utilities.WebdriverUtility;

public class HealthInsurance_PolicyReview {

	// ==================== FIELDS ====================

	private WebDriver driver;
	private WebDriverWait wait;
	private WebdriverUtility utility;
	private JavaScriptUtilities jsUtil;

	// ==================== CONSTRUCTOR ====================

	public HealthInsurance_PolicyReview(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		this.utility = new WebdriverUtility();
		this.utility.initializeDriver(driver);

		this.jsUtil = new JavaScriptUtilities(driver);
	}

	// ==================== LOCATORS ====================

	@FindBy(xpath = "//span[normalize-space()='Proceed']")
	private WebElement policyProceedButton;

	// ==================== GETTERS ====================

	public WebElement getPolicyProceedButton() {
		return policyProceedButton;
	}

	// ==================== BUSINESS LOGIC ====================

	public void clickPolicyProceedButton() {
		// Wait until clickable (stronger than presenceOfElementLocated)
		utility.waitUntilElementIsCLickable(10L, getPolicyProceedButton()).click();
	}
}