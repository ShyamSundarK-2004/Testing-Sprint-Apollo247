package com.apollo247.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.apollo247.testing.utilities.WebdriverUtility;

public class UploadPrescriptionPage {

	WebdriverUtility utilities = new WebdriverUtility();

	public UploadPrescriptionPage(WebDriver driver) {
		utilities.initializeDriver(driver);
	}

	// ====== Locators ======

	// prescription upload
	@FindBy(id = "icon-button-file")
	private WebElement fileupload;

	// attached prescription
	@FindBy(xpath = "//span[contains(@class,'PreviewUploadedPrescription')]//img")
	private WebElement fileAttached;

	// wrong file upload popup
	@FindBy(xpath = "//p[contains(text(), 'Invalid File')]")
	private WebElement wrongFileUploadPopup;

	// proceed button
	@FindBy(xpath = "//button/span[text() = 'Proceed']")
	private WebElement preceedBtn;

	// invalid message popup ok button
	@FindBy(xpath = "//span[contains(text(),'Okay')]//parent::button")
	private WebElement closeInvalidMessagePopup;

	// ====== Getters ======

	public WebElement getFileUpload() {
		return fileupload;
	}

	public WebElement getFileAttached() {
		return fileAttached;
	}

	public WebElement getWrongFileUpload() {
		return wrongFileUploadPopup;
	}

	public WebElement getProceedBtn() {
		return preceedBtn;
	}

	public WebElement getCloseInvalidMessagePopup() {
		return closeInvalidMessagePopup;
	}
	// ====== Business Logics ======

	public void uploadFile(String path) {
		utilities.waituntilPresenceOfElementLocated(30, By.id("icon-button-file"));
		getFileUpload().sendKeys(path);

	}

	public boolean isFileAttached() {
		utilities.waitUntilElementIsVisibility(15L, getFileAttached());
		return getFileAttached().isDisplayed();
	}

	public boolean isWrongFileUploaded() {
		utilities.waitUntilElementIsVisibility(15L, getWrongFileUpload());
		return getWrongFileUpload().isDisplayed();
	}

	public boolean isProceedBtnEnabled() {
		return getProceedBtn().isEnabled();
	}

	public void closeInvalidMessagePopup() {
		getCloseInvalidMessagePopup().click();
	}
}
