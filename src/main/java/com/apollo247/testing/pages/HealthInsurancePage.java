package com.apollo247.testing.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.apollo247.testing.utilities.ActionUtilities;
import com.apollo247.testing.utilities.JavaScriptUtilities;
import com.apollo247.testing.utilities.WebdriverUtility;

public class HealthInsurancePage {

	// ==================== FIELDS ====================

	private WebDriver driver;
	private WebDriverWait wait;
	private WebdriverUtility utility;
	private JavaScriptUtilities jsUtil;
	private ActionUtilities actionUtil;

	// ==================== CONSTRUCTOR ====================

	public HealthInsurancePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.wait.pollingEvery(Duration.ofMillis(200));

		this.utility = new WebdriverUtility();
		this.utility.initializeDriver(driver);

		this.jsUtil = new JavaScriptUtilities(driver);
		this.actionUtil = new ActionUtilities(driver);
	}

	// ==================== LOCATORS ====================

	@FindBy(css = "[href='https://apollo247insurance.com/health-insurance']")
	private WebElement buyInsuranceButton;

	@FindBy(css = "[type=\"number\"]")
	private WebElement pincodeNumber;

	@FindBy(css = "img[alt='cross icon']")
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

	// ==================== GETTERS ====================

	public WebElement getClickBuyInsurance() {
		return buyInsuranceButton;
	}

	public WebElement getEnterPinCode() {
		return pincodeNumber;
	}

	public WebElement getSubmitButton() {
		return submitSelectLocation;
	}

	// ==================== BUSINESS LOGIC ====================

	public void performEnterPinCode(String pincode) {
		// Use utility wait instead of raw WebDriverWait
		WebElement pinInput = utility.waitUntilElementIsCLickable(10L, getEnterPinCode());
		pinInput.clear();
		pinInput.sendKeys(pincode);

		WebElement submitBtn = utility.waitUntilElementIsCLickable(10L, getSubmitButton());

		try {
			submitBtn.click();
		} catch (Exception e) {
			// Fallback: JS click via JavaScriptUtilities
			jsUtil.jsClick(submitBtn);
		}
	}

	public void selectGender(String gender) {
		By locator = By.xpath("//button[normalize-space()='" + gender + "']");
		By modal = By.cssSelector(".PincodeModal_middleSection__RCZiF");

		// Wait for modal overlay to disappear
		utility.waitUntilInvisibilityOfElementLocated(10L, modal);

		// Retry for stale/intercept issues
		for (int i = 0; i < 3; i++) {
			try {
				WebElement element = utility.waitUntilElementIsCLickable(10L,
						driver.findElement(locator));
				element.click();
				return;
			} catch (StaleElementReferenceException e) {
				System.out.println("Retrying due to stale element...");
			} catch (ElementClickInterceptedException e) {
				System.out.println("Retrying due to click interception...");
				// Fallback: JS click via JavaScriptUtilities
				jsUtil.jsClick(driver.findElement(locator));
				return;
			}
		}

		throw new RuntimeException("Unable to click gender: " + gender);
	}

	public void clickViewButton(String buttonName) {
		By locator = By.xpath("//span[normalize-space()='" + buttonName + "']");

		for (int i = 0; i < 3; i++) {
			try {
				// Wait until element is present in DOM
				utility.waituntilPresenceOfElementLocated(10L, locator);

				WebElement button = utility.waitUntilElementIsCLickable(10L,
						driver.findElement(locator));

				// Scroll into view via JavaScriptUtilities
				jsUtil.jsScrollIntoView(button);

				// Click via JavaScriptUtilities (bypasses overlay)
				jsUtil.jsClick(button);

				return;
			} catch (StaleElementReferenceException e) {
				System.out.println("Retrying due to stale element...");
			}
		}

		throw new RuntimeException("Failed to click View Plans after retries");
	}
	
	public WebElement viewPlansButton() {
		return driver.findElement(By.xpath("//span[normalize-space()='View Plans']"));		
	}

	public void unselectMember() {
		try {
			WebElement checkbox = driver.findElement(By.xpath("//input[@type=\"checkbox\"][1]"));
			if (checkbox.isSelected()) {
				checkbox.click();
			}
		} catch (Exception e) {
			// Ignore if not present or already unselected
		}
	}

	public String errorMessageNoMemeberSelected() {
		By errorLocator = By.xpath("//div[contains(text(),'Select minimum one adult')]");
		WebElement errorMsg = utility.waitUntilVisibilityOfElementLocated(10L, errorLocator);
		return errorMsg.getText().trim();
	}

	public void selectMember(String member, String age) {
		// Click member span
		By memberLocator = By.xpath("//span[normalize-space()='" + member + "']");
		utility.waitUntilElementIsCLickable(10L, driver.findElement(memberLocator)).click();

		// Wait for age dropdown to appear
		By dropdown = By.xpath("//div[contains(@class,'MemberTile_popoverIn')]");
		utility.waitUntilVisibilityOfElementLocated(10L, dropdown);

		// Select age inside dropdown
		By ageLocator = By.xpath(
				"//div[contains(@class,'MemberTile_popoverIn')]//li[normalize-space()='" + age + "']");
		utility.waitUntilElementIsCLickable(10L, driver.findElement(ageLocator)).click();

		// Wait for dropdown to close
		utility.waitUntilInvisibilityOfElementLocated(10L, dropdown);
	}

	public void clickCancelSelectLocation() {
		cancelSelectLocation.click();
	}
}