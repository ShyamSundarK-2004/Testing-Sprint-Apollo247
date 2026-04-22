package com.apollo247.testing.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.apollo247.testing.utilities.WebdriverUtility;

public class HealthInsuranceProductListings {

	public WebDriverWait wait;
	public WebDriver driver;
	public WebdriverUtility utility;

	public HealthInsuranceProductListings(WebDriver driver) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.driver = driver;
		this.utility = new WebdriverUtility();
		this.utility.initializeDriver(driver); // Pass the active driver to utility
	}

	// --------------Locator finding-------------

	@FindBy(xpath = "//span[.='View Plans']")
	private WebElement viewPlansButton;

	@FindBy(xpath = "//p[.='View Plans']")
	private WebElement viewPlansHeader;
	
	@FindBy(xpath = "//p[normalize-space()='Filter']")
	private WebElement filterButton;
	
	@FindBy(xpath = "//p[normalize-space()='Sort By']")
	private WebElement sortByButton;
	
	@FindBy (xpath = "//span[normalize-space()='Coverage']")
	private WebElement coverageOption;
	
	@FindBy(xpath = "//span[normalize-space()='Room rent type']")
	private WebElement roomRentTypeOption;
	
	@FindBy (xpath = "//span[normalize-space()='Apply']")
	private WebElement applyButton;
	
	@FindBy(xpath = "//button[normalize-space()='Apply']")
	private WebElement sortByApplyButton;
	
	@FindBy (xpath = "//span[normalize-space()='clear selection']")
	private WebElement clearSelectionButton;
	
	@FindBy(id = "premium_desc")
	private WebElement premiumOption;
	
	@FindBy(xpath = "//button[contains(@id,'headlessui-listbox-button')]")
	private WebElement plansCoverageAmountValue;
	
	@FindBy(xpath = "//h3[normalize-space()='Premium']")
	private WebElement premiumPlanHeader;
	// ----------Getters---------------
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
	public void clickFilter() {
		filterButton.click();
		
	}
	public void clickSortBy() {
		sortByButton.click();
	}
	
	public void clicksortByApplyButton() {
		sortByApplyButton.click();
	}
	public void clickCoverageOption() {
		 coverageOption.click();;
	}
	public void clickRoomRentTypeOption() {
		roomRentTypeOption.click();
	}
	public void clickApplyButton() {
		 applyButton.click();
	}
	public void clickClearSelectionButton() {
		clearSelectionButton.click();
	}
	

	// --------------Business logic--------

	public void performViewPlans() {
		getViewPlans().click();

	}

	public WebElement viewPlanHeader() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[.='View Plans']")));
	}

	public List<WebElement> viewNumberOfPlans() {
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[contains(@class,'plan') or .//button[contains(text(),'Proceed')]]")));

	}

	public boolean isNoPlansMessageDisplayed() {

		WebElement msg = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'No plans found')]")));
		return msg.isDisplayed();
	}

	public boolean isPlansAvailable() {
		// Wait for container
		WebElement container = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='NewProductListing_listingContainer__HHxUE']")));

		// Get all plan cards inside container
		List<WebElement> plans = container.findElements(
				By.xpath(".//div[contains(@class,'ProductPlanVariantsList_productPlanVariantsListWrapper__79oGU')]"));

		return plans.size() > 0;
	}

	public void coverageAmount(String coverageRange) {
	    clickFilter();
	    // Click coverage option
	    clickCoverageOption();

	    // Dynamic locator for coverage
	    By coverageLocator = By.xpath(
	        "//label[contains(normalize-space(),'" + coverageRange + "')]/preceding-sibling::input[@type='radio']"
	    );

	    // Wait and click
	    WebElement coverageElement = wait.until(ExpectedConditions.elementToBeClickable(coverageLocator));
	    coverageElement.click();

	    // Apply filter
	    clickApplyButton();
	}
	
	public void roomRentType(String roomRentType) {
		clickFilter();
		clickRoomRentTypeOption();
		
		By roomRentLocator = By.xpath(
		        "//label[contains(normalize-space(),'"+roomRentType+"')]/preceding-sibling::input[@type='radio']"
		    );
		WebElement roomRentElement = wait.until(ExpectedConditions.elementToBeClickable(roomRentLocator));
	    roomRentElement.click();
	    clickApplyButton();
	}
	
	public void sortByPlans(String plan) {
		
		clickSortBy();
		
		By planLocator = By.xpath(
		        "//label[contains(normalize-space(),'"+plan+"')]/preceding-sibling::input[@type='radio']"
		    );
		WebElement planElement = wait.until(ExpectedConditions.elementToBeClickable(planLocator));
	    planElement.click();
	    
	    clicksortByApplyButton();
		
	}
	
	public WebElement coverageRange(String coverageRange) {
		By coverageLocator = By.xpath(
		        "//label[contains(normalize-space(),'" + coverageRange + "')]/preceding-sibling::input[@type='radio']"
		    );
		WebElement coverageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(coverageLocator));
	    return coverageElement;		
		
	}
	
	public  boolean isMatch(String rangeStr) {

        // normalize
		//rangeStr=coverageRange(rangeStr).getText();
		String valueStr=getPlansCoverageAmountValue().getText();
    	
        rangeStr = rangeStr.toLowerCase().replaceAll("[^0-9.\\-a-z]", "");
        valueStr = valueStr.toLowerCase().replaceAll("[^0-9.a-z]", "");

        // extract unit
        String rangeUnit = rangeStr.contains("crore") ? "crore" : "lakh";
        String valueUnit = valueStr.contains("crore") ? "crore" : "lakh";

        // ❌ unit mismatch → reject
        if (!rangeUnit.equals(valueUnit)) {
            return false;
        }

        // extract numbers
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
	    String actualText = wait.until(ExpectedConditions.visibilityOf(getPremiumPlanHeader())).getText().trim();
	    return actualText.equalsIgnoreCase(expectedText.trim());
	}
}