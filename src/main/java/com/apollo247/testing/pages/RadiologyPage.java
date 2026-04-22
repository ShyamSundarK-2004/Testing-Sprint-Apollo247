package com.apollo247.testing.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.apollo247.testing.utilities.ActionUtilities;
import com.apollo247.testing.utilities.JavaScriptUtilities;
import com.apollo247.testing.utilities.WebdriverUtility;

public class RadiologyPage {

	WebdriverUtility utilities = new WebdriverUtility();
	JavaScriptUtilities jsUtil;
	ActionUtilities actions;
	WebDriver driver;

	public RadiologyPage(WebDriver driver) {
		this.driver = driver;
		utilities.initializeDriver(driver);
		jsUtil = new JavaScriptUtilities(driver);
		actions = new ActionUtilities(driver);
	}

	// ====== Locators ======

	// select city input field
	@FindBy(xpath = "//div[text() = 'Choose City']")
	private WebElement chooseCityField;

	// select hospital or clinic field
	@FindBy(xpath = "(//div[contains(@class,'AphSelect_select')])[3]")
	private WebElement selectHospitalField;

	// select date field
	@FindBy(xpath = "//button[contains(@class,'datePickerBtn')]")
	private WebElement pickPreferedDate;

	// select test name
	@FindBy(xpath = "(//div[contains(@class,'AphSelect_select')])[4]")
	private WebElement selectTestName;

	// add test details field
	@FindBy(css = "[placeholder='Add Tests Details']")
	private WebElement addtestDetailsField;

	// upload prescription file field
	@FindBy(xpath = "//input[@type = 'file']")
	private WebElement uploadPrescriptionField;

	// popup dialog box
	@FindBy(xpath = "//div[contains(@class,'AphDialog_paper')]")
	private WebElement bookRadiologyPopup;

	// popup close button
	@FindBy(css = "[alt='close']")
	private WebElement closePopupBtn;

	// current month in calendar
	@FindBy(xpath = "//span[contains(@class,'react-calendar')]")
	private WebElement currentMonth;

	// go to next calender month
	@FindBy(xpath = "//button[text() = '›']")
	private WebElement clickNextBtn;

	// request call button
	@FindBy(xpath = "//button[contains(@class,'Radiology_requestButton')]")
	private WebElement requestCallBtn;

	// go to previous calendar month
	@FindBy(xpath = "//button[text()='‹']")
	private WebElement clickPreviousBtn;

	// ====== Getters ======

	public WebElement getChooseCityField() {
		return chooseCityField;
	}

	public WebElement getSelectHospitalField() {
		return selectHospitalField;
	}

	public WebElement getPickPreferedDate() {
		return pickPreferedDate;
	}

	public WebElement getSelectTestName() {
		return selectTestName;
	}

	public WebElement getAddtestDetailsField() {
		return addtestDetailsField;
	}

	public WebElement getUploadPrescriptionField() {
		return uploadPrescriptionField;
	}

	public WebElement getBookRadiologyPopup() {
		return bookRadiologyPopup;
	}

	public WebElement getClosePopupBtn() {
		return closePopupBtn;
	}

	public WebElement getCurrentMonth() {
		return currentMonth;
	}

	public WebElement getClickNextBtn() {
		return clickNextBtn;
	}

	public WebElement getClickPreviousBtn() {
		return clickPreviousBtn;
	}

	public WebElement getRequestCallbtn() {
		return requestCallBtn;
	}

	// ====== Business Logic ======

	public void closeRadiologyPopup() {
		try {
			if (getBookRadiologyPopup().isDisplayed()) {
				getClosePopupBtn().click();
			}
		} catch (Exception e) {

		}
	}

	public String getCurrentPageUrl() {
		return utilities.fetchApplicationURL();
	}

	public void chooseCity(String cityName) {
		jsUtil.scrollByPixels(-185);
		utilities.waitUntilElementIsVisibility(25L, getChooseCityField());
		jsUtil.jsClick(getChooseCityField());
		utilities.waituntilPresenceOfElementLocated(25L, By.xpath("//li[text() = '" + cityName + "']"));
		WebElement chooseCity = driver.findElement(By.xpath("//li[normalize-space() = '" + cityName + "']"));
		chooseCity.click();
	}

	public void chooseHospital(String hospitalAreaName) {
		getSelectHospitalField().click();
		WebElement selecthospital = driver
				.findElement(By.xpath("//li[contains(normalize-space(),'" + hospitalAreaName + "')]"));
		selecthospital.click();
	}

	public void chooseTestName(String testName) {
		String[] names = testName.split(",");
		getSelectTestName().click();
		for (String name : names) {
			WebElement test = driver
					.findElement(By.xpath("//label[normalize-space() = '" + name + "']/preceding-sibling::input"));
			test.click();
		}
	}

	public void chooseDate(String dateinput) {

		String[] parts = dateinput.split("-");
		String targetMonth = parts[0];
		String day = parts[1];
		getPickPreferedDate().click();
		while (true) {
			String currentMonth = getCurrentMonth().getText().split(" ")[0];
			if (currentMonth.equalsIgnoreCase(targetMonth)) {
				break;
			}
			if (isFutureMonth(currentMonth, targetMonth) && getClickNextBtn().isEnabled()) {
				getClickNextBtn().click(); // forward
			} else {
				if (getClickPreviousBtn().isEnabled()) {
					getClickPreviousBtn().click();
				}
			}
		}
		driver.findElement(By.xpath("//abbr[text()='" + day + "']")).click();

		actions.pressEscape();
	}

	private boolean isFutureMonth(String current, String target) {

		List<String> months = List.of("January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December");

		int currentIndex = months.indexOf(current);
		int targetIndex = months.indexOf(target);

		return targetIndex > currentIndex;
	}

	public void UploadPrescription(String path) {
		getUploadPrescriptionField().sendKeys(path);
	}

	public boolean isRequestCallBtnEnabled() {
		return getRequestCallbtn().isEnabled();
	}

}
