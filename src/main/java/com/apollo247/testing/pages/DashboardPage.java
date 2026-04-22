package com.apollo247.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.apollo247.testing.utilities.WebdriverUtility;

public class DashboardPage {

	public WebDriverWait wait;
	public WebDriver driver;
	public WebdriverUtility utilities = new WebdriverUtility();

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		utilities.initializeDriver(driver);

	}

	// ====== locators ======

	// header login button
	@FindBy(xpath = "//span[text()= 'Login']")
	private WebElement loginBtn;

	// phone number field
	@FindBy(css = "[name='mobileNumber']")
	private WebElement mobileNumberField;

	// whatsapp notification checkbox
	@FindBy(css = "[type='checkbox']")
	private WebElement whatsappNotificationCheckbox;

	// continue button
	@FindBy(xpath = "//button[text() = 'Continue']")
	private WebElement continueBtn;

	// click on continue button
	@FindBy(xpath = "//button[text()='Verify']")
	private WebElement verifyBtn;

	// buy medicine module
	@FindBy(linkText = "Buy Medicines")
	private WebElement buyMedicineModule;

	// Find Doctors module
	@FindBy(linkText = "Find Doctors")
	private WebElement findDoctorModule;

	// Lab Tests module
	@FindBy(linkText = "Lab Tests")
	private WebElement labTestModule;

	// Buy Insurance module
	@FindBy(linkText = "Buy Insurance")
	private WebElement insuranceModule;

	// my account module
	@FindBy(css = "[title='Login/SignUp']")
	private WebElement myAccountModule;

	// profile image after login
	@FindBy(xpath = "//div[@id='loginPopup']//img")
	private WebElement profilePic;

	// user account popup
	@FindBy(xpath = "//div[@id = 'loginPopup' and text() = 'My Account']")
	private WebElement userAccountPopup;

	// ===== getters and setter ======

	// login button
	public WebElement getLoginBtn() {
		return loginBtn;
	}

	// mobile number input field
	public WebElement getMobileNumberField() {
		return mobileNumberField;
	}

	// whatsapp notification checkbox
	public WebElement getWhatsappNotification() {
		return whatsappNotificationCheckbox;
	}

	// continue button
	public WebElement getContinuebtn() {
		return continueBtn;
	}

	// verify button
	public WebElement getVerifyBtn() {
		return verifyBtn;
	}

	// buy medicine module
	public WebElement getBuyMedicineModule() {
		return buyMedicineModule;
	}

	// find doctor module
	public WebElement getFindDoctorModule() {
		return findDoctorModule;
	}

	// lab test module
	public WebElement getLabTestModule() {
		return labTestModule;
	}

	// insurance module
	public WebElement getInsuranceModule() {
		return insuranceModule;
	}

	// My Account module
	public WebElement getMyAccountModule() {
		return myAccountModule;
	}

	// profile pic popup
	public WebElement getProfilePic() {
		return profilePic;
	}

	// user account popup
	public WebElement getUserAccountPopup() {
		return userAccountPopup;
	}

	// ====== business logic ======

	// closing dom popup
	public void closeDomPopup() {

		try {
			// Wait for shadow host and locate the hidden host
			WebElement domPopup = utilities.waituntilPresenceOfElementLocated(20L,
					By.cssSelector("ct-web-popup-imageonly"));

			// Access shadow root
			SearchContext shadowDom = domPopup.getShadowRoot();

			// Find close button INSIDE shadow DOM
			WebElement closeBtn = shadowDom.findElement(By.id("close"));

			if (closeBtn.isDisplayed()) {
				closeBtn.click();
				System.out.println("Popup closed successfully.");
			}

		} catch (Exception e) {
			System.out.println("No Popup found");
		}


	// clicking on header login button
	public void clickLoginButton() {
		getLoginBtn().click();
	}

	// enter mobile number to login
	public void enterMobileNumber(String number) {
		utilities.waitUntilElementIsVisibility(15L, getMobileNumberField());
		getMobileNumberField().sendKeys(number);
	}

	// perform login action
	public void login(String number) {
		clickLoginButton();
		enterMobileNumber(number);
		getContinuebtn().click();
	}

	public void enterOtpAndclickVerify() {
		WebElement verify = utilities.waitUntilElementIsCLickable(60L, getVerifyBtn());
		verify.click();
	}

	public void clickOnModule(String module) {
		WebElement moduleName = driver.findElement(By.linkText(module));
		moduleName.click();
	}
	public void clickBuyMedicines() {
	    utilities.waitUntilElementIsCLickable(20L, getBuyMedicineModule()).click();
	}

	public void clickonHealthInsuranceModule() {
		By buyInsuranceLocator = By.cssSelector("[href='https://apollo247insurance.com/health-insurance']");
		utilities.waituntilPresenceOfElementLocated(10L, buyInsuranceLocator).click();
	}

	public void clickOnMyAccountBtn() {
		getMyAccountModule().click();
	}

	public boolean isUserLoggedIn() {
		try {
			getProfilePic().click();
			return getUserAccountPopup().isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}


	public void clickBuyMedicines() {
		getBuyMedicineModule().click();
	}

	public void clickProfileIcon() {
	    clickOnMyAccountBtn();
	}

}