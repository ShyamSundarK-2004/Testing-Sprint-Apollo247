package com.apollo247.testing.pages;

import org.openqa.selenium.By;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.apollo247.testing.utilities.WebdriverUtility;

public class HealthInsurancePage {

	public WebDriverWait wait;
	public WebDriver driver;
	public WebdriverUtility utility;

	public HealthInsurancePage(WebDriver driver) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.driver = driver;
		this.utility = new WebdriverUtility();
		this.utility.initializeDriver(driver); // Pass the active driver to utility
	}

	// -----------Locator Finding---------------

	@FindBy(css = "[href='https://apollo247insurance.com/health-insurance']")
	private WebElement buyInsuranceButton;

	@FindBy(css = "[type=\"number\"]")
	private WebElement pincodeNumber;

	@FindBy(css = "[alt=\"cross icon\"]")
	private WebElement cancelSelectLocation;

	@FindBy(xpath = "//button[.='Submit']")
	private WebElement submitSelectLocation;

	@FindBy(xpath = "//button[normalize-space()='Male']")
	private WebElement maleButton;

	@FindBy(xpath = "//button[normalize-space()='Female']")
	private WebElement femaleButton;

	@FindBy(xpath = "//span[.='Self']")
	private WebElement selectSelfMember;

	@FindBy(xpath = "//span[.='Wife']")
	private WebElement selectWifeMember;

	@FindBy(xpath = "//span[.='Mother']")
	private WebElement selectMotherMember;

	@FindBy(xpath = "//span[.='Father']")
	private WebElement selectFatherMemeber;

	// ----------Getters ------------
	public WebElement getClickBuyInsurance() {
		return buyInsuranceButton;

	}

	public WebElement getEnterPinCode() {
		return pincodeNumber;
	}

	public WebElement getSubmitButton() {
		return submitSelectLocation;
	}

	// ---------Business Logic----------
	public void performEnterPinCode(String pincode) {
		WebElement pinInput = utility.waitUntilElementIsCLickable(10L, getEnterPinCode());

	    // Clear before entering
	    pinInput.clear();
	    pinInput.sendKeys(pincode);

	    WebElement submitBtn = utility.waitUntilElementIsCLickable(10L, getSubmitButton());

	    try {
	        submitBtn.click();
	    } catch (Exception e) {
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
	    }
	}

	public void selectGender(String gender) {
		By locator = By.xpath("//button[normalize-space()='" + gender + "']");
		

		for (int i = 0; i < 3; i++) {
			try {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".PincodeModal_middleSection__RCZiF")));
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator))).click();
				break;
			} catch (StaleElementReferenceException e) {
				System.out.println("Retrying gender click...");
			}
		}
	}

	public void clickViewButton(String buttonName) {

		By locator = By.xpath("//span[normalize-space()='" + buttonName + "']");

		for (int i = 0; i < 3; i++) {
			try {
				// Wait for DOM stability
				wait.until(ExpectedConditions.presenceOfElementLocated(locator));

				WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));

				// Scroll into view (important for this site)
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

				// Click using JS (bypasses stale + overlay)
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

				return;

			} catch (StaleElementReferenceException e) {
				System.out.println("Retrying due to stale element...");
			}
		}

		throw new RuntimeException("Failed to click View Plans after retries");
	}

	public void unselectMember() {
		try {
			WebElement checkbox = driver.findElement(By.xpath("//input[@type=\"checkbox\"][1]"));
			if (checkbox.isSelected()) {
				checkbox.click(); // unselect
			}
		} catch (Exception e) {
			// ignore if not selected
		}

	}

	public String errorMessageNoMemeberSelected() {
		WebElement errorMsg = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Select minimum one adult')]")));
		return errorMsg.getText().trim();
	}

	public void selectMember(String member, String age) {

		wait.pollingEvery(Duration.ofMillis(200));

		// Click member
		By memberLocator = By.xpath("//span[normalize-space()='" + member + "']");
		wait.until(ExpectedConditions.elementToBeClickable(memberLocator)).click();

		// Wait for dropdown
		By dropdown = By.xpath("//div[contains(@class,'MemberTile_popoverIn')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(dropdown));

		// Select age INSIDE dropdown (important)
		By ageLocator = By.xpath("//div[contains(@class,'MemberTile_popoverIn')]//li[normalize-space()='" + age + "']");
		wait.until(ExpectedConditions.elementToBeClickable(ageLocator)).click();

		// Wait for dropdown to disappear
		wait.until(ExpectedConditions.invisibilityOfElementLocated(dropdown));
	}
}