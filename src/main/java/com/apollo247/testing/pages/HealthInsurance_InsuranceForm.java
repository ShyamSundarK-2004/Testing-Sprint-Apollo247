package com.apollo247.testing.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
		selectDropdownByText(feetBtn, feet+" ft");

// Height - Inches
		selectDropdownByText(inchsBtn, inch+" in");

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

	public void fillAddressProofForm(String type, String idNO) throws InterruptedException {

		By selectBox = By.xpath(
				"//strong[contains(text(),'Upload documents for address proof')]/ancestor::form//input[@placeholder='Select']");

		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(selectBox));

		safeClick(dropdown); // ✅ FIX: prevents footer overlap

		// Select document type
		By option = By.xpath("//div[contains(@class,'Dropdown_options')]//p[normalize-space()='" + type + "']");
		safeClick(wait.until(ExpectedConditions.visibilityOfElementLocated(option)));

		// Enter ID number
		WebElement idField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//strong[contains(text(),'Upload documents for address proof')]/ancestor::form//input[@placeholder='Enter id number']")));
		idField.clear();
		idField.sendKeys(idNO);

		// Upload file
		String filePath = new File("src/test/resources/FileUpload/F1_picture.png").getAbsolutePath();
		WebElement upload = driver.findElement(By.xpath(
				"//strong[contains(text(),'Upload documents for address proof')]/ancestor::form//input[@type='file']"));
		upload.sendKeys(filePath);
	}

	public void scrollToCenter(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-120)");
	}

	public void fillAddressDetails(String flatNO, String location, String pincode) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Step 1: JS click Add New Address
		WebElement addNewAddr = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[contains(@class,'AddressField_addButton')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
				addNewAddr);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewAddr);
		Thread.sleep(2000); // ✅ Wait for modal to fully open

		// ✅ Step 2: Remove overflow:hidden blocking the modal
		((JavascriptExecutor) driver).executeScript("document.documentElement.style.overflow = 'auto';");
		Thread.sleep(500);

		// Step 3: Flat Number - JS click + sendKeys
		WebElement flat = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='flatNumber']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", flat);
		((JavascriptExecutor) driver).executeScript("arguments[0].value='';", flat);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", flat); // ✅ JS click
		flat.sendKeys(flatNO);
		System.out.println("✅ Flat number entered");

		// Step 4: Colony - JS click + sendKeys
		WebElement colony = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='colony']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].value='';", colony);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", colony); // ✅ JS click
		colony.sendKeys(location);
		System.out.println("✅ Colony entered");

		// Step 5: Pincode - JS click + sendKeys
		WebElement pin = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='pincode']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].value='';", pin);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", pin); // ✅ JS click
		pin.sendKeys(pincode);
		System.out.println("✅ Pincode entered");

		// Step 6: Wait for city/state auto-fetch
		wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(By.id("pincode"), "value", "")));
		Thread.sleep(1500);

		// Step 7: JS click Add button
		WebElement addButton = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Add']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", addButton);
		Thread.sleep(500);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
		System.out.println("✅ Address added successfully");
	}

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

	public void fillBankAccountDetails(String accNO, String accType, String IFSCcode) {

		driver.findElement(By.id("accountNumber")).sendKeys(accNO);

		By selectBox = By.xpath(
				"// strong[contains(text(),'Account Type')]/ancestor::p/following-sibling::div//input[@placeholder='Select']");
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(selectBox));

		safeClick(dropdown);
		By option = By.xpath("//p[normalize-space()='" + accType + "']");
		safeClick(wait.until(ExpectedConditions.visibilityOfElementLocated(option)));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.findElement(By.xpath("//input[@id='IFSC']")).sendKeys(IFSCcode);
		// Step 4: Wait until City/State auto-fetched (pincode lookup done)
		wait.until(
				ExpectedConditions.not(ExpectedConditions.attributeToBe(By.xpath("//input[@id='IFSC']"), "value", "")));

	}

	public void acceptsTC() {
		driver.findElement(By.xpath(
				"//p[contains(text(),'I hereby confirm and declare that all information provided by me is true')]"))
				.click();

		driver.findElement(By.xpath(
				"//p[contains(text(),'I hereby declare, on my behalf and on behalf of all persons proposed to be insured, that the above statements, answers and/or particulars given by me are true and complete in all respects to the best of my knowledge and that I am authorized to propose on behalf of these other persons.')]"))
				.click();
		driver.findElement(By.xpath(
				"//p[contains(text(),'I/We authorize the Company to share information pertaining to my / our proposal including the medical records of the Insured / Proposer for the sole purpose of Service Delivery with our empaneled provider.')]"))
				.click();
		safeClick(By.xpath("//button[.//span[normalize-space()='Next']]"));

	}

	public boolean reviewPolicyDetails(String actualstr) {
		String expectedStr = driver.findElement(By.xpath("//p[.='SELF']/following-sibling::p")).getText();
		return expectedStr.equalsIgnoreCase(actualstr);

	}

	public void policyBUY_NOW(String buynow) {
		safeClick(By.xpath("//span[normalize-space()='Buy Now']"));
	}

	public void clickNextBtn() {
		safeClick(By.xpath("//button[.//span[normalize-space()='Next']]"));
	}

	public boolean isCallPopupVisible() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			return wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//strong[normalize-space()='Unable to process your request. Please contact support.']")))
					.isDisplayed();

		} catch (Exception e) {
			return false;
		}
	}

	public void closeCallPopupIfPresent() {
		try {
			if (isCallPopupVisible()) {

				System.out.println("⚠️ Call popup detected, closing it...");

				WebElement closeBtn = driver.findElement(By.xpath("//span[@class='Lb']"));

				closeBtn.click();

				// wait until popup disappears
				new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOf(closeBtn));
			}

		} catch (Exception e) {
			System.out.println("Popup handling failed: " + e.getMessage());
		}
	}

	public boolean verifyPaymentPage(String payment) throws IOException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {
			// ✅ Step 1: Wait for page load
			wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState")
					.equals("complete"));

			// ✅ Step 2: Handle popup if it appears
			closeCallPopupIfPresent();

			// ✅ Step 3: Wait for either condition (IMPORTANT)
			boolean isPaymentLoaded = wait.until(driver -> {

				String currentUrl = driver.getCurrentUrl().toLowerCase();

				boolean urlMatch = currentUrl.contains(payment.toLowerCase());

				boolean paymentTextVisible = driver.findElements(By.xpath("//*[contains(text(),'Payment Options')]"))
						.size() > 0;

				return urlMatch || paymentTextVisible;
			});

			if (isPaymentLoaded) {
				System.out.println("✅ Payment page loaded");
				return true;
			}

			return false;

		} catch (Exception e) {

			System.out.println("❌ Payment flow failed: " + e.getMessage());

			// Screenshot
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("screenshots/payment_issue.png"));

			return false;
		}
	}

	public boolean verifyPaymentOption(String expectedText) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			return wait
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + expectedText + "')]")))
					.isDisplayed();

		} catch (Exception e) {
			return false; // don't fail test immediately
		}
	}

	public boolean verifyAmtVisible(String expectedText) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			return wait
					.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + expectedText + "')]")))
					.isDisplayed();

		} catch (Exception e) {
			return false;
		}
	}

}