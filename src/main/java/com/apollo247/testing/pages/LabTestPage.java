package com.apollo247.testing.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.apollo247.testing.utilities.JavaScriptUtilities;
import com.apollo247.testing.utilities.WebdriverUtility;

public class LabTestPage {

	WebDriver driver;
	WebdriverUtility utilities = new WebdriverUtility();
	JavaScriptUtilities jsUtil;

	public LabTestPage(WebDriver driver) {
		this.driver = driver;
		utilities.initializeDriver(driver);
		this.jsUtil = new JavaScriptUtilities(driver);

	}

	// ====== LOCATORS ======

	// labtest search bar
	@FindBy(css = "[placeholder='Search for lab tests']")
	private WebElement searchBar;

	// call back popup
	@FindBy(xpath = " //div[@class='CallbackWidget_popUpOpen__5Zh2n']//child::img[@alt='close']")
	private WebElement popupCloseBtn;

	// radiology booking section
	@FindBy(css = "[href='/lab-tests/radiology']")
	private WebElement radiologyBookingBtn;

	// prescription test booking module
	@FindBy(xpath = "//h3[text() = 'Upload and Order']")
	private WebElement bookByPrescriptionModule;

	// view orders in my orders page button
	@FindBy(xpath = "//h3[text() = 'View Reports in My Orders']")
	private WebElement viewReportInMyOrder;

	// ===== GETTERS =====

	public WebElement getSearchBar() {
		return searchBar;
	}

	public WebElement getPopupCloseBtn() {
		return popupCloseBtn;
	}

	public WebElement getRadiologyBookingBtn() {
		return radiologyBookingBtn;
	}

	public WebElement getBookByMPescriptionModule() {
		return bookByPrescriptionModule;
	}

	public WebElement getViewReportInMyOrder() {
		return viewReportInMyOrder;
	}

	// ====== BUSINESS LOGIC ======

	// click on search box
	public void clickOnSearchBox() {
		utilities.waitUntilElementIsVisibility(35L, getSearchBar());
		getSearchBar().click();
	}

	// enter search text
	public void searchTest(String text) {
		clickOnSearchBox();
		getSearchBar().sendKeys(text);
		if (!text.isEmpty()) {
			getSearchBar().sendKeys(Keys.ENTER);
		}
	}

	public void closePopupIfPresent() {
		try {
			utilities.waitUntilElementIsCLickable(50L, getPopupCloseBtn());
			getPopupCloseBtn().click();
		} catch (Exception e) {
		}
	}

	public String getCurrentPageUrl() {
		utilities.waitUntilUrlContains(20L, "lab-tests");
		return utilities.fetchApplicationURL();
	}

	public boolean isNoActionPerformed() {
		String value = getSearchBar().getAttribute("value");
		return value == null || value.isEmpty();
	}

	public void clickOnBookByPrescriptionModule() {
		utilities.waitUntilElementIsVisibility(30L, getBookByMPescriptionModule());
		jsUtil.jsClick(getBookByMPescriptionModule());
	}

	public void clickOnRadiologyBookingBtn() {
		utilities.waitUntilElementIsVisibility(30L, getRadiologyBookingBtn());
		getRadiologyBookingBtn().click();
		utilities.switchToWindowByURL("radiology");

	}

	public void clickOnViewReportInMyOrder() {
		utilities.waitUntilElementIsVisibility(30L, getViewReportInMyOrder());
		jsUtil.jsClick(getViewReportInMyOrder());
	}

}
