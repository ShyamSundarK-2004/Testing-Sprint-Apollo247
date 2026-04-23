package com.apollo247.testing.pages;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.apollo247.testing.utilities.WebdriverUtility;

public class HealthInsurance_InsuranceForm {
	public WebDriverWait wait;
	public WebDriver driver;
	public WebdriverUtility utility;

	public HealthInsurance_InsuranceForm(WebDriver driver) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.driver = driver;
		this.utility = new WebdriverUtility();
		this.utility.initializeDriver(driver); // Pass the active driver to utility

	}

	// ------Loctor finding-----------
	@FindBy(css = "input[placeholder='First Name']")
	WebElement firstNameField;

	@FindBy(id = "lastName")
	WebElement lastNameField;

	@FindBy(css = "input[placeholder='dd/mm/yyyy']")
	WebElement dobElement;

	@FindBy(xpath = "//abbr[normalize-space()='13']")
	WebElement date;
	@FindBy(xpath = "//span[normalize-space()='Done']")
	WebElement dateDone;

	@FindBy(xpath = "//input[@placeholder='Feet']")
	WebElement feetBtn;
	@FindBy(xpath = "//p[normalize-space()='5 ft']")
	WebElement selectFeet;

	@FindBy(xpath = "//input[@placeholder='Inches']")
	WebElement inchsBtn;

	@FindBy(xpath = "//p[normalize-space()='5 in']")
	WebElement selectInch;

	@FindBy(xpath = "//input[@id='weight']")
	WebElement weightField;

	@FindBy(xpath = "//span[normalize-space()='Next']")
	WebElement NextBtn;

	@FindBy(css = "img[alt='Back']")
	WebElement policyFormBack;

	@FindBy(id = "email")
	WebElement email;

	@FindBy(id = "mobileNumber")
	WebElement mobNo;

	@FindBy(id = "emergencyMobileNumber")
	WebElement emMobNo;

	@FindBy(id = "proposerFatherName")
	WebElement proFN;

	@FindBy(id = "panNumber")
	WebElement PANNumber;

	// -----------Getters----------

	// ---------Business Logic------

	public void fillMemberDetails(String firstName, String lastName, String dob, String feet, String inch,
			String weight) {

//first Name
		utility.waitUntilElementIsVisibility(10L, firstNameField);
//firstNameField.click();
		firstNameField.sendKeys(firstName);

//lastName
		utility.waitUntilElementIsVisibility(10L, lastNameField);
//lastNameField.click();
		lastNameField.sendKeys(lastName);

// DOB
		dobElement.click();
		By dateLocator = By.xpath("//abbr[@aria-label='" + dob + "']");
		wait.until(ExpectedConditions.elementToBeClickable(dateLocator)).click();
		dateDone.click();

// Height - Feet
		selectDropdownByText(feetBtn, feet);

// Height - Inches
		selectDropdownByText(inchsBtn, inch);

// Weight
		weightField.clear();
		weightField.sendKeys(weight);

// Click Next
		NextBtn.click();
	}

	public void selectDropdownByText(WebElement dropdown, String value) {
		wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();

		By option = By.xpath("//p[normalize-space()='" + value + "']");
		wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	}

//	//strong[contains(normalize-space(),'" + questionText + "')]
//    /ancestor::div
//    //label[normalize-space()='" + normalizedAnswer + "']

	public void safeClick(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		// Scroll to center (avoids footer overlap)
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void safeClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void fillMedicalQuestions(String opt1, String opt2, String opt3, String opt4, String opt5) {

		safeClick(By.xpath(
				"//input[@name='chronicHealthQuestion']/following-sibling::label[normalize-space()='" + opt1 + "']"));

		safeClick(By.xpath(
				"//input[@name='managedHealthQuestion']/following-sibling::label[normalize-space()='" + opt2 + "']"));

		safeClick(By.xpath(
				"//input[@name='medicalHistoryQuestion']/following-sibling::label[normalize-space()='" + opt3 + "']"));

		safeClick(By.xpath(
				"//input[@name='insuranceClaimQuestion']/following-sibling::label[normalize-space()='" + opt4 + "']"));

		safeClick(By.xpath("//input[@name='recreationalSubstanceQuestion']/following-sibling::label[normalize-space()='"
				+ opt5 + "']"));

		safeClick(By.xpath("//button[normalize-space()='Next']"));
	}

	public void fillProposerDetailForm(String emailid, String phNO, String EmPhNo, String fatherName, String PANno) {
		// Enter Email ID
		utility.waitUntilElementIsVisibility(10L, email);
		driver.findElement(By.id("email")).sendKeys(emailid);

		// Enter Mobile Number
		utility.waitUntilElementIsVisibility(10L, mobNo);
		driver.findElement(By.id("mobileNumber")).sendKeys(phNO);

		// Enter Emergency Mobile Number
		utility.waitUntilElementIsVisibility(10L, emMobNo);
		driver.findElement(By.id("emergencyMobileNumber")).sendKeys(EmPhNo);

		// Enter Father Name
		utility.waitUntilElementIsVisibility(10L, proFN);
		driver.findElement(By.id("proposerFatherName")).sendKeys(fatherName);

		// Enter PAN Number
		utility.waitUntilElementIsVisibility(10L, PANNumber);
		driver.findElement(By.id("panNumber")).sendKeys(PANno);
	}

	public void fillKYCVerificationForm(String type, String idNo) {

		By selectBox = By.xpath("//strong[contains(text(),'Upload documents for KYC verification')]"
				+ "/ancestor::form//input[@placeholder='Select']");

		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(selectBox));

		safeClick(dropdown); // ✅ FIX: prevents footer overlap

		// Select document type
		By option = By.xpath("//div[contains(@class,'Dropdown_options')]//p[normalize-space()='" + type + "']");
		safeClick(wait.until(ExpectedConditions.visibilityOfElementLocated(option)));

		// Enter ID number
		WebElement idField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//strong[contains(text(),'Upload documents for KYC verification')]"
						+ "/ancestor::form//input[@placeholder='Enter id number']")));
		idField.clear();
		idField.sendKeys(idNo);

		// Upload file
		String filePath = new File("src/test/resources/FileUpload/F1_picture.png").getAbsolutePath();
		WebElement upload = driver
				.findElement(By.xpath("//strong[contains(text(),'Upload documents for KYC verification')]"
						+ "/ancestor::form//input[@type='file']"));
		upload.sendKeys(filePath);
	}

	public void fillAddressProofForm(String type, String idNO) {
		By selectBox = By.xpath("//strong[contains(text(),'Upload documents for address proof')]"
				+ "/ancestor::form//input[@placeholder='Select']");

		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(selectBox));

		safeClick(dropdown); // ✅ FIX: prevents footer overlap

		// Select document type
		By option = By.xpath("//div[contains(@class,'Dropdown_options')]//p[normalize-space()='" + type + "']");
		safeClick(wait.until(ExpectedConditions.visibilityOfElementLocated(option)));

		// Enter ID number
		WebElement idField = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'Upload documents for address proof')]"
						+ "/ancestor::form//input[@placeholder='Enter id number']")));
		idField.clear();
		idField.sendKeys(idNO);

		// Upload file
		String filePath = new File("src/test/resources/FileUpload/F1_picture.png").getAbsolutePath();
		WebElement upload = driver
				.findElement(By.xpath("//strong[contains(text(),'Upload documents for address proof')]"
						+ "/ancestor::form//input[@type='file']"));
		upload.sendKeys(filePath);

	}
	
	public void fillAddressDetails(String flatNO, String location, String pincode) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // 🔹 Add New Address
	    By addAddressBtn = By.xpath("//div[contains(@class,'AddressField_addButton') and normalize-space()='+ Add New Address']");
	    WebElement addBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(addAddressBtn));

	    scrollToCenter(addBtn);
	    safeClick(addBtn);

	    // 🔹 Fill Fields
	    WebElement flat = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flatNumber")));
	    flat.clear();
	    flat.sendKeys(flatNO);

	    WebElement colony = driver.findElement(By.id("colony"));
	    colony.clear();
	    colony.sendKeys(location);

	    WebElement pin = driver.findElement(By.id("pincode"));
	    pin.clear();
	    pin.sendKeys(pincode);

	    // Wait for pincode processing
	    wait.until(ExpectedConditions.not(
	            ExpectedConditions.attributeToBe(By.id("pincode"), "value", "")
	    ));

	    // 🔹 Add Button (inside modal)
	    By addBtnLocator = By.xpath("//button[contains(@class,'Footer_blackFull')]//span[normalize-space()='Add']");
	    WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(addBtnLocator));

	    scrollToCenter(add);

	    try {
	        wait.until(ExpectedConditions.elementToBeClickable(add)).click();
	    } catch (ElementClickInterceptedException e) {
	        // 🔥 fallback for modal overlay
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", add);
	    }
	}
	
	
	public void scrollToCenter(WebElement element) {
	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].scrollIntoView({block:'center'});", element
	    );
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-120)");
	}

//	public void fillAddressDetails(String flatNO, String location, String pincode) {
//
//		driver.findElement(
//				By.xpath("//div[contains(@class,'AddressField_addButton') and normalize-space()='+ Add New Address']"))
//				.click();
//		// Step 1: Enter Flat Number
//		driver.findElement(By.id("flatNumber")).clear();
//		driver.findElement(By.id("flatNumber")).sendKeys(flatNO);
//
//		// Step 2: Enter Colony
//		driver.findElement(By.xpath("//input[@id='colony']")).clear();
//		driver.findElement(By.xpath("//input[@id='colony']")).sendKeys(location);
//
//		// Step 3: Enter Pincode
//		driver.findElement(By.xpath("//input[@id='pincode']")).clear();
//		driver.findElement(By.xpath("//input[@id='pincode']")).sendKeys(pincode);
//
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//
//		// Step 4: Wait until City/State auto-fetched (pincode lookup done)
//		wait.until(ExpectedConditions
//				.not(ExpectedConditions.attributeToBe(By.xpath("//input[@id='pincode']"), "value", "")));
//
//		// Step 5: Wait until Add button is ENABLED (not disabled)
//		WebElement addButton = wait
//				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Add']")));
//
//		// Step 6: Click Add button
//		addButton.click();
//	}

	public void fillNomineeSelection(String nomineeFN, String nomineeLN, String propRelation, String dob,
			String appointeeFN, String appointeeLN, String apDOB, String apRelation) {

		// nominee first and last name
		driver.findElement(By.xpath(
				"//strong[contains(text(),'First Name')]/ancestor::div[contains(@class,'SearchableDropdown_textAreaLabel')]/following-sibling::div//input[@placeholder='First Name']"))
				.sendKeys(nomineeFN);
		driver.findElement(By.xpath(
				"//strong[.='Last Name']/ancestor::label/following-sibling::div//input[@placeholder='Last Name']"))
				.sendKeys(nomineeLN);

		By proselectBox = By.xpath(
				"//strong[contains(text(),'Relation to Proposer')]/ancestor::p/following-sibling::div//input[@placeholder='Select']");
		WebElement prodropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(proselectBox));

		safeClick(prodropdown);

		// nominee relation
		By option1 = By.xpath("//div[contains(@class,'Dropdown_options')]/p[normalize-space()='" + propRelation + "']");
		safeClick(wait.until(ExpectedConditions.visibilityOfElementLocated(option1)));

		// nominee dob
		driver.findElement(By.xpath("//label[.='DOB']/following-sibling::div//input[@placeholder='dd/mm/yyyy']"))
				.click();
		By dateLocator1 = By.xpath("//abbr[@aria-label='" + dob + "']");
		wait.until(ExpectedConditions.elementToBeClickable(dateLocator1)).click();
		dateDone.click();

		// appointee first and last name
		driver.findElement(By.xpath(
				"//strong[contains(text(),'Appointee First Name')]/ancestor::label/following-sibling::div//input[@placeholder='First Name']"))
				.sendKeys(appointeeFN);
		driver.findElement(By.xpath(
				"//strong[contains(text(),'Appointee Last Name')]/ancestor::label/following-sibling::div//input[@placeholder='Last Name']"))
				.sendKeys(appointeeLN);

		By aposelectBox = By.xpath(
				"//strong[contains(text(),'Appointee Relation')]/ancestor::p/following-sibling::div//input[@placeholder='Select']");
		WebElement apodropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(aposelectBox));

		safeClick(apodropdown);

		// appointee relation
		By option2 = By.xpath("//div[contains(@class,'Dropdown_options')]/p[normalize-space()='" + apRelation + "']");
		safeClick(wait.until(ExpectedConditions.visibilityOfElementLocated(option2)));

		driver.findElement(
				By.xpath("//label[.='Appointee DOB']/following-sibling::div//input[@placeholder='dd/mm/yyyy']"))
				.click();
		By dateLocator2 = By.xpath("//abbr[@aria-label='" + apDOB + "']");
		wait.until(ExpectedConditions.elementToBeClickable(dateLocator2)).click();
		dateDone.click();

	}
	
	public void fillBankAccountDetails(String accNO, String accType,String IFSCcode) {
		
		driver.findElement(By.id("accountNumber")).sendKeys(accNO);
		
		By selectBox = By.xpath(
				"// strong[contains(text(),'Account Type')]/ancestor::p/following-sibling::div//input[@placeholder='Select']");
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(selectBox));

		safeClick(dropdown);
		By option = By.xpath("//p[normalize-space()='"+accType+"']");
		safeClick(wait.until(ExpectedConditions.visibilityOfElementLocated(option)));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		driver.findElement(By.xpath("//input[@id='IFSC']")).sendKeys(IFSCcode);
		// Step 4: Wait until City/State auto-fetched (pincode lookup done)
		wait.until(ExpectedConditions
				.not(ExpectedConditions.attributeToBe(By.xpath("//input[@id='IFSC']"), "value", "")));
		
		

		
		
	}
	public void acceptsTC() {
		driver.findElement(By.xpath("//p[contains(text(),'I hereby confirm and declare that all information provided by me is true')]")).click();
		
		driver.findElement(By.xpath("//p[contains(text(),'I hereby declare, on my behalf and on behalf of all persons proposed to be insured, that the above statements, answers and/or particulars given by me are true and complete in all respects to the best of my knowledge and that I am authorized to propose on behalf of these other persons.')]")).click();
		driver.findElement(By.xpath("//p[contains(text(),'I/We authorize the Company to share information pertaining to my / our proposal including the medical records of the Insured / Proposer for the sole purpose of Service Delivery with our empaneled provider.')]")).click();
		safeClick(By.xpath("//button[normalize-space()='Next']"));
		
	}
	
	public boolean reviewPolicyDetails(String actualstr) {
		String expectedStr=driver.findElement(By.xpath("//p[.='SELF']/following-sibling::p")).getText();
		return expectedStr.equalsIgnoreCase(actualstr);
		
	}
	
	public void policyBUY_NOW() {
		driver.findElement(By.xpath("//span[normalize-space()='Buy Now']")).click();
	}
	
	public void clickNextBtn(String nextbtn) {
		safeClick(By.xpath("//button[normalize-space()='"+nextbtn+"']"));
	}

	// driver.findElement(By.id("email"));
	// driver.findElement(By.id("mobileNumber"));
	// driver.findElement(By.id("emergencyMobileNumber"));
	// driver.findElement(By.id("proposerFatherName"));
	// driver.findElement(By.id("panNumber"));
	// strong[contains(text(),'Upload documents for KYC
	// verification')]/ancestor::div[contains(@class,'ProposerForm_boxHeading')]/following-sibling::div//input[@placeholder='Select']
	// strong[contains(text(),'Upload documents for address
	// proof:')]/ancestor::div[contains(@class,'ProposerForm_boxHeading')]/following-sibling::div//input[@placeholder='Select']
	// driver.find_element(By.XPATH, "//p[normalize-space()='PAN Card']").click()
	// strong[contains(text(),'Upload documents for KYC
	// verification')]/ancestor::form//input[@placeholder='Enter id number']
	// strong[contains(text(),'Upload documents for address
	// proof:')]/ancestor::form//input[@placeholder='Enter id number']
	//// strong[contains(text(),'Upload documents for KYC
	// verification')]/ancestor::form//input[@type='file']
	//// strong[contains(text(),'Upload documents for address
	// proof:')]/ancestor::form//input[@type='file']
	// driver.findElement(By.className("AddressField_addButton__Cm_qj"))
	// driver.findElement(By.id("flatNumber")) driver.findElement(By.id("colony"))
	// driver.findElement(By.id("pincode"))driver.findElement(By.xpath("//span[normalize-space()='Add']"))

	// driver.findElement(By.cssSelector("input[placeholder='First Name']"))
	// driver.findElement(By.id("lastName"))
	//// strong[contains(text(),'Relation to
	// Proposer')]/ancestor::p/following-sibling::div//input[@placeholder='Select']
	//// div[contains(@class,'Dropdown_options')]/p[normalize-space()='Wife']
	// dob handle
	// label[.='DOB']/following-sibling::div//input[@placeholder='dd/mm/yyyy']
	//// label[.='Appointee
	// DOB']/following-sibling::div//input[@placeholder='dd/mm/yyyy']

	// div[contains(@class,'Dropdown_options')]/p[normalize-space()='Brother']

	// driver.findElement(By.id("accountNumber"))
	//// strong[contains(text(),'Account Type')]/ancestor::p/following-sibling::div//input[@placeholder='Select']
	// driver.findElement(By.xpath("//p[normalize-space()='Savings']"))
	// driver.findElement(By.id("IFSC"))

}