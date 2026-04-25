package com.apollo247.testing.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.apollo247.testing.utilities.JavaScriptUtilities;
import com.apollo247.testing.utilities.WebdriverUtility;

public class PatientDetailsPage {

	WebDriver driver;
	WebdriverUtility utilities = new WebdriverUtility();
	JavaScriptUtilities jsUtil;

	public PatientDetailsPage(WebDriver driver) {
		this.driver = driver;
		utilities.initializeDriver(driver);
		jsUtil = new JavaScriptUtilities(driver);
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

	// Date of month field
	@FindBy(css = "[placeholder='dd/mm/yyyy']")
	private WebElement dobField;

	// relation dropdown field
	@FindBy(xpath = "//div[contains(@class,'AphSelect_select')]")
	private WebElement relationDropdownField;

	// emailID field
	@FindBy(css = "[placeholder='name@email.com']")
	private WebElement emailIDField;

	// save patient details button
	@FindBy(css = "[title='save']")
	private WebElement saveBtn;

	// saved patient details field
	@FindBy(xpath = "//div[contains(@class,'SelectCartPatient_patientsTile')]")
	private List<WebElement> patientList;

	// continue button
	@FindBy(xpath = "//button[.//span[text()='CONTINUE']]")
	private WebElement continueBtn;

	// confirm button
	@FindBy(xpath = "//button[.//span[normalize-space() ='CONFIRM']]")
	private WebElement confirmBtn;

	// patient details saved popup ok button
	@FindBy(xpath = "//button[.//span[normalize-space() ='OK']]")
	private WebElement profileCreatedPopupBtn;

	// patient details saved popup
	@FindBy(xpath = "//div[contains(@class,'MascotWithMessage_signUpBar')]")
	private WebElement profileCreatedPopup;

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

	public WebElement getDOBField() {
		return dobField;
	}

	public WebElement getRelationField() {
		return relationDropdownField;
	}

	public WebElement getEmailIDField() {
		return emailIDField;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public List<WebElement> getPatientList() {
		return patientList;
	}

	public WebElement getContinueBtn() {
		return continueBtn;
	}

	public WebElement getConfirmBtn() {
		return confirmBtn;
	}

	public WebElement getProfileCreatedPopupBtn() {
		return profileCreatedPopupBtn;
	}

	public WebElement getProfileCreatedPopup() {
		return profileCreatedPopup;
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

	public void enterDOB(String dateOfBirth) {
		getDOBField().sendKeys(dateOfBirth);
	}

	public void selectGender(String gender) {

		WebElement genderBtn = driver.findElement(
				By.xpath("//div[contains(@class,'AddNewProfile_btnGroup')]//span[normalize-space()='" + gender + "']"));

		genderBtn.click();
	}

	public void selectRelation(String relation) {

		utilities.waitUntilElementIsCLickable(20L, relationDropdownField).click();

		By option = By.xpath("//li[@data-value='" + relation.toUpperCase() + "']");

		WebElement relationOption = utilities.waituntilPresenceOfElementLocated(20L, option);
		relationOption.click();
	}

	public void enterEmailID(String emailID) {
		getEmailIDField().sendKeys(emailID);
	}

	public void clickSaveBtn() {
		getSaveBtn().click();
	}

	public void selectSavedPatient(String patientName) {

		for (WebElement patient : getPatientList()) {

			String text = patient.getText().trim();

			if (text.toLowerCase().contains(patientName.toLowerCase())) {

				// add created patient details
				WebElement plusBtn = patient.findElement(By.xpath(".//img[contains(@src,'circleplus')]"));

				jsUtil.jsClick(plusBtn);
				return;
			}
		}
	}

	public void clickOnContinueBtn() {
		utilities.waitUntilElementIsCLickable(15L, getContinueBtn());
		getContinueBtn().click();
	}

	public void clickOnConfirmBtn() {
		getConfirmBtn().click();
	}

	public void clickOnPatientSavedPopupBtn() {
		getProfileCreatedPopupBtn().click();
	}

	public boolean isPatientDetailsPopupShown() {
		return getProfileCreatedPopup().isDisplayed();
	}

	public void enterPatientDetails(String firstName, String lastName, String DOB, String gender, String relation,
			String emailID) {
		enterFirstName(firstName);
		enterLastName(lastName);
		enterDOB(DOB);
		selectGender(gender);
		selectRelation(relation);
		enterEmailID(emailID);
	}

}
