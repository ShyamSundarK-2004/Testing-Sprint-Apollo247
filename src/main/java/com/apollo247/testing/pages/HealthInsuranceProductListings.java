package com.apollo247.testing.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.apollo247.testing.utilities.JavaScriptUtilities;
import com.apollo247.testing.utilities.WebdriverUtility;

public class HealthInsuranceProductListings {

	// ==================== FIELDS ====================

	private WebDriver driver;
	private WebDriverWait wait;
	private WebdriverUtility utility;
	private JavaScriptUtilities jsUtil;

	// ==================== CONSTRUCTOR ====================

	public HealthInsuranceProductListings(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		this.utility = new WebdriverUtility();
		this.utility.initializeDriver(driver);

		this.jsUtil = new JavaScriptUtilities(driver);
	}

	// ==================== LOCATORS ====================

	@FindBy(xpath = "//span[.='View Plans']")
	private WebElement viewPlansButton;

	@FindBy(xpath = "//p[.='View Plans']")
	private WebElement viewPlansHeader;

	@FindBy(xpath = "//p[normalize-space()='Filter']")
	private WebElement filterButton;

	@FindBy(xpath = "//p[normalize-space()='Sort By']")
	private WebElement sortByButton;

	@FindBy(xpath = "//span[normalize-space()='Coverage']")
	private WebElement coverageOption;

	@FindBy(xpath = "//span[normalize-space()='Room rent type']")
	private WebElement roomRentTypeOption;

	@FindBy(xpath = "//span[normalize-space()='Apply']")
	private WebElement applyButton;

	@FindBy(xpath = "//button[normalize-space()='Apply']")
	private WebElement sortByApplyButton;

	@FindBy(xpath = "//span[normalize-space()='clear selection']")
	private WebElement clearSelectionButton;

	@FindBy(id = "premium_desc")
	private WebElement premiumOption;

	@FindBy(xpath = "//button[contains(@id,'headlessui-listbox-button')]")
	private WebElement plansCoverageAmountValue;

	@FindBy(xpath = "//h3[normalize-space()='Premium']")
	private WebElement premiumPlanHeader;

	@FindBy(xpath = "//span[normalize-space()='Proceed to Customise']")
	private WebElement proceedToCutomizeButton;

	// ==================== GETTERS ====================

	public WebElement getViewPlans() {
		return viewPlansButton;
	}

	public WebElement getPlansCoverageAmountValue() {
		return plansCoverageAmountValue;
	}

	public WebElement getPremiumPlanHeader() {
		return premiumPlanHeader;
	}

	public WebElement getViewPlansHeader() {
		return viewPlansHeader;
	}

	// ==================== CLICK ACTIONS ====================

	public void clickFilter() {
		utility.waitUntilElementIsCLickable(10L, filterButton).click();
	}

	public void clickSortBy() {
		utility.waitUntilElementIsCLickable(10L, sortByButton).click();
	}

	public void clicksortByApplyButton() {
		utility.waitUntilElementIsCLickable(10L, sortByApplyButton).click();
	}

	public void clickCoverageOption() {
		utility.waitUntilElementIsCLickable(10L, coverageOption).click();
	}

	public void clickRoomRentTypeOption() {
		utility.waitUntilElementIsCLickable(10L, roomRentTypeOption).click();
	}

	public void clickApplyButton() {
		utility.waitUntilElementIsCLickable(10L, applyButton).click();
	}

	public void clickClearSelectionButton() {
		utility.waitUntilElementIsCLickable(10L, clearSelectionButton).click();
	}

	public void clickProceedsPlan() {
		utility.waitUntilElementIsCLickable(10L, proceedToCutomizeButton).click();
	}

	// ==================== BUSINESS LOGIC ====================

	public void performViewPlans() {
		utility.waitUntilElementIsCLickable(10L, getViewPlans()).click();
	}

	public WebElement viewPlanHeader() {
		return utility.waitUntilVisibilityOfElementLocated(10L, By.xpath("//p[.='View Plans']"));
	}

	public List<WebElement> viewNumberOfPlans() {
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[contains(@class,'plan') or .//button[contains(text(),'Proceed')]]")));
	}

	public boolean isNoPlansMessageDisplayed() {
		WebElement msg = utility.waitUntilVisibilityOfElementLocated(10L,
				By.xpath("//*[contains(text(),'No plans found')]"));
		return msg.isDisplayed();
	}

	public boolean isPlansAvailable() {
		WebElement container = utility.waitUntilVisibilityOfElementLocated(10L,
				By.xpath("//div[@class='NewProductListing_listingContainer__HHxUE']"));

		List<WebElement> plans = container.findElements(
				By.xpath(".//div[contains(@class,'ProductPlanVariantsList_productPlanVariantsListWrapper__79oGU')]"));

		return plans.size() > 0;
	}

	public void coverageAmount(String coverageRange) {
		clickFilter();
		clickCoverageOption();

		By coverageLocator = By.xpath(
				"//label[contains(normalize-space(),'" + coverageRange + "')]/preceding-sibling::input[@type='radio']");

		utility.waitUntilElementIsCLickable(10L, driver.findElement(coverageLocator)).click();

		clickApplyButton();
	}

	public void roomRentType(String roomRentType) {
		clickFilter();
		clickRoomRentTypeOption();

		By roomRentLocator = By.xpath(
				"//label[contains(normalize-space(),'" + roomRentType + "')]/preceding-sibling::input[@type='radio']");

		for (int i = 0; i < 3; i++) {
			try {
				WebElement element = wait
						.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(roomRentLocator)));
				element.click();
				break;
			} catch (StaleElementReferenceException e) {
				if (i == 2)
					throw e;
			}
		}

		utility.waitUntilElementIsCLickable(10L, applyButton).click();
	}

	public void sortByPlans(String plan) {
		clickSortBy();

		By planLocator = By
				.xpath("//label[contains(normalize-space(),'" + plan + "')]/preceding-sibling::input[@type='radio']");

		utility.waitUntilElementIsCLickable(10L, driver.findElement(planLocator)).click();

		clicksortByApplyButton();
	}

	public WebElement coverageRange(String coverageRange) {
		By coverageLocator = By.xpath(
				"//label[contains(normalize-space(),'" + coverageRange + "')]/preceding-sibling::input[@type='radio']");
		return utility.waitUntilVisibilityOfElementLocated(10L, coverageLocator);
	}

	public boolean isMatch(String rangeStr) {
		String valueStr = getPlansCoverageAmountValue().getText();

		rangeStr = rangeStr.toLowerCase().replaceAll("[^0-9.\\-a-z]", "");
		valueStr = valueStr.toLowerCase().replaceAll("[^0-9.a-z]", "");

		String rangeUnit = rangeStr.contains("crore") ? "crore" : "lakh";
		String valueUnit = valueStr.contains("crore") ? "crore" : "lakh";

		if (!rangeUnit.equals(valueUnit)) {
			return false;
		}

		String rangeNumbers = rangeStr.replaceAll("[^0-9.\\-]", "");
		String valueNumberStr = valueStr.replaceAll("[^0-9.]", "");

		double value = Double.parseDouble(valueNumberStr);
		double min, max;

		if (rangeNumbers.contains("-")) {
			String[] parts = rangeNumbers.split("-");
			min = Double.parseDouble(parts[0]);
			max = Double.parseDouble(parts[1]);
		} else {
			min = Double.parseDouble(rangeNumbers);
			max = min;
		}

		return value >= min && value <= max;
	}

	public boolean isTextMatching(String expectedText) {
		utility.waitUntilElementIsVisibility(10L, getPremiumPlanHeader());
		return getPremiumPlanHeader().getText().trim().equalsIgnoreCase(expectedText.trim());
	}
}