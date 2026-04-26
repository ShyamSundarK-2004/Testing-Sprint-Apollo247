package com.apollo247.testing.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

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

import com.apollo247.testing.utilities.ActionUtilities;
import com.apollo247.testing.utilities.JavaScriptUtilities;
import com.apollo247.testing.utilities.WebdriverUtility;

public class HealthInsurance_InsuranceForm {

	// ==================== FIELDS ====================

	private WebDriver driver;
	private WebDriverWait wait;
	private WebdriverUtility utility;
	private JavaScriptUtilities jsUtil;
	private ActionUtilities actionUtil;

	// ==================== CONSTRUCTOR ====================

	public HealthInsurance_InsuranceForm(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		this.utility = new WebdriverUtility();
		this.utility.initializeDriver(driver);

		this.jsUtil = new JavaScriptUtilities(driver);
		this.actionUtil = new ActionUtilities(driver);
	}

	// ==================== LOCATORS ====================

	@FindBy(css = "input[placeholder='First Name']")
	private WebElement firstNameField;

	@FindBy(id = "lastName")
	private WebElement lastNameField;

	@FindBy(css = "input[placeholder='dd/mm/yyyy']")
	private WebElement dobElement;

	@FindBy(xpath = "//abbr[normalize-space()='13']")
	private WebElement date;

	@FindBy(xpath = "//span[normalize-space()='Done']")
	private WebElement dateDone;

	@FindBy(xpath = "//input[@placeholder='Feet']")
	private WebElement feetBtn;

	@FindBy(xpath = "//p[normalize-space()='5 ft']")
	private WebElement selectFeet;

	@FindBy(xpath = "//input[@placeholder='Inches']")
	private WebElement inchsBtn;

	@FindBy(xpath = "//p[normalize-space()='5 in']")
	private WebElement selectInch;

	@FindBy(xpath = "//input[@id='weight']")
	private WebElement weightField;

	@FindBy(xpath = "//span[normalize-space()='Next']")
	private WebElement NextBtn;

	@FindBy(css = "img[alt='Back']")
	private WebElement policyFormBack;

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(id = "mobileNumber")
	private WebElement mobNo;

	@FindBy(id = "emergencyMobileNumber")
	private WebElement emMobNo;

	@FindBy(id = "proposerFatherName")
	private WebElement proFN;

	@FindBy(id = "panNumber")
	private WebElement PANNumber;

	// ==================== INTERNAL UTILS ====================

	/**
	 * pause() — centralized sleep that properly handles InterruptedException
	 * instead of scattering Thread.sleep() + swallowing exceptions everywhere.
	 */
	private void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * safeClick(By) — waits for visibility, scrolls into view,
	 * nudges -120px to clear sticky header, then JS click to
	 * bypass ElementClickInterceptedException.
	 */
	public void safeClick(By locator) {
		WebElement element = utility.waitUntilVisibilityOfElementLocated(10L, locator);
		jsUtil.jsScrollIntoView(element);
		jsUtil.scrollByPixels(-120);
		jsUtil.jsClick(element);
	}

	public void safeClick(WebElement element) {
		jsUtil.jsScrollIntoView(element);
		jsUtil.scrollByPixels(-120);
		jsUtil.jsClick(element);
	}

	public void selectDropdownByText(WebElement dropdown, String value) {
		utility.waitUntilElementIsCLickable(10L, dropdown).click();
		By option = By.xpath("//p[normalize-space()='" + value + "']");
		utility.waitUntilElementIsCLickable(10L, driver.findElement(option)).click();
	}

	public void scrollToCenter(WebElement element) {
		jsUtil.jsScrollIntoView(element);
		jsUtil.scrollByPixels(-120);
	}

	// ==================== BUSINESS LOGIC ====================

	public void fillMemberDetails(String firstName, String lastName, String dob, String feet, String inch,
			String weight) {

		// First Name
		utility.waitUntilElementIsVisibility(10L, firstNameField);
		firstNameField.sendKeys(firstName);

		// Last Name
		utility.waitUntilElementIsVisibility(10L, lastNameField);
		lastNameField.sendKeys(lastName);

		// DOB — wait for loader to disappear, then scroll + JS click
		utility.waitUntilInvisibilityOfElementLocated(10L, By.cssSelector("[class*='Loader_overlay']"));
		jsUtil.jsScrollIntoView(dobElement);
		jsUtil.jsClick(dobElement);

		By dateLocator = By.xpath("//abbr[@aria-label='" + dob + "']");
		utility.waitUntilElementIsCLickable(10L, driver.findElement(dateLocator)).click();
		utility.waitUntilElementIsCLickable(10L, dateDone).click();

		// Height - Feet & Inches
		selectDropdownByText(feetBtn, feet + " ft");
		selectDropdownByText(inchsBtn, inch + " in");

		// Weight
		weightField.clear();
		weightField.sendKeys(weight);

		NextBtn.click();
	}

	public void fillMedicalQuestions(String opt1, String opt2, String opt3, String opt4, String opt5) {
		safeClick(By.xpath("//input[@name='chronicHealthQuestion']/following-sibling::label[normalize-space()='" + opt1 + "']"));
		safeClick(By.xpath("//input[@name='managedHealthQuestion']/following-sibling::label[normalize-space()='" + opt2 + "']"));
		safeClick(By.xpath("//input[@name='medicalHistoryQuestion']/following-sibling::label[normalize-space()='" + opt3 + "']"));
		safeClick(By.xpath("//input[@name='insuranceClaimQuestion']/following-sibling::label[normalize-space()='" + opt4 + "']"));
		safeClick(By.xpath("//input[@name='recreationalSubstanceQuestion']/following-sibling::label[normalize-space()='" + opt5 + "']"));
		safeClick(By.xpath("//button[normalize-space()='Next']"));
	}

	public void fillProposerDetailForm(String emailid, String phNO, String EmPhNo, String fatherName, String PANno) {
		utility.waitUntilElementIsVisibility(10L, email);
		email.sendKeys(emailid);

		utility.waitUntilElementIsVisibility(10L, mobNo);
		mobNo.sendKeys(phNO);

		utility.waitUntilElementIsVisibility(10L, emMobNo);
		emMobNo.sendKeys(EmPhNo);

		utility.waitUntilElementIsVisibility(10L, proFN);
		proFN.sendKeys(fatherName);

		utility.waitUntilElementIsVisibility(10L, PANNumber);
		PANNumber.sendKeys(PANno);
	}

	public void fillKYCVerificationForm(String type, String idNo) {
		By selectBox = By.xpath("//strong[contains(text(),'Upload documents for KYC verification')]"
				+ "/ancestor::form//input[@placeholder='Select']");

		safeClick(utility.waitUntilVisibilityOfElementLocated(10L, selectBox));

		By option = By.xpath("//div[contains(@class,'Dropdown_options')]//p[normalize-space()='" + type + "']");
		safeClick(utility.waitUntilVisibilityOfElementLocated(10L, option));

		WebElement idField = utility.waitUntilVisibilityOfElementLocated(10L,
				By.xpath("//strong[contains(text(),'Upload documents for KYC verification')]"
						+ "/ancestor::form//input[@placeholder='Enter id number']"));
		idField.clear();
		idField.sendKeys(idNo);

		String filePath = new File("src/test/resources/FileUpload/F1_picture.png").getAbsolutePath();
		driver.findElement(By.xpath("//strong[contains(text(),'Upload documents for KYC verification')]"
				+ "/ancestor::form//input[@type='file']")).sendKeys(filePath);
	}

	public void fillAddressProofForm(String type, String idNO) {
		By selectBox = By.xpath("//strong[contains(text(),'Upload documents for address proof')]"
				+ "/ancestor::form//input[@placeholder='Select']");

		safeClick(utility.waitUntilVisibilityOfElementLocated(10L, selectBox));

		By option = By.xpath("//div[contains(@class,'Dropdown_options')]//p[normalize-space()='" + type + "']");
		safeClick(utility.waitUntilVisibilityOfElementLocated(10L, option));

		WebElement idField = utility.waitUntilVisibilityOfElementLocated(10L,
				By.xpath("//strong[contains(text(),'Upload documents for address proof')]"
						+ "/ancestor::form//input[@placeholder='Enter id number']"));
		idField.clear();
		idField.sendKeys(idNO);

		String filePath = new File("src/test/resources/FileUpload/F1_picture.png").getAbsolutePath();
		driver.findElement(By.xpath("//strong[contains(text(),'Upload documents for address proof')]"
				+ "/ancestor::form//input[@type='file']")).sendKeys(filePath);
	}

	public void fillAddressDetails(String flatNO, String location, String pincode) {
		WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Click Add New Address
		WebElement addNewAddr = longWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[contains(@class,'AddressField_addButton')]")));
		jsUtil.jsScrollIntoView(addNewAddr);
		pause(1000);
		jsUtil.jsClick(addNewAddr);
		pause(2000);

		// Remove overflow:hidden blocking the modal
		((JavascriptExecutor) driver).executeScript("document.documentElement.style.overflow = 'auto';");
		pause(500);

		// Flat Number
		WebElement flat = longWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[@id='flatNumber']")));
		jsUtil.jsScrollIntoView(flat);
		flat.clear();
		jsUtil.jsClick(flat);
		flat.sendKeys(flatNO);
		System.out.println("✅ Flat number entered");

		// Colony
		WebElement colony = longWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[@id='colony']")));
		colony.clear();
		jsUtil.jsClick(colony);
		colony.sendKeys(location);
		System.out.println("✅ Colony entered");

		// Pincode
		WebElement pin = longWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[@id='pincode']")));
		pin.clear();
		jsUtil.jsClick(pin);
		pin.sendKeys(pincode);
		System.out.println("✅ Pincode entered");

		// Wait for city/state auto-fetch
		longWait.until(ExpectedConditions.not(
				ExpectedConditions.attributeToBe(By.id("pincode"), "value", "")));
		pause(1500);

		// Click Add button
		WebElement addButton = longWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//span[normalize-space()='Add']")));
		jsUtil.jsScrollIntoView(addButton);
		pause(500);
		jsUtil.jsClick(addButton);
		System.out.println("✅ Address added successfully");
	}

	public void fillNomineeSelection(String nomineeFN, String nomineeLN, String propRelation, String dob,
			String appointeeFN, String appointeeLN, String apDOB, String apRelation) {

		driver.findElement(By.xpath(
				"//strong[contains(text(),'First Name')]/ancestor::div[contains(@class,'SearchableDropdown_textAreaLabel')]"
						+ "/following-sibling::div//input[@placeholder='First Name']")).sendKeys(nomineeFN);

		driver.findElement(By.xpath(
				"//strong[.='Last Name']/ancestor::label/following-sibling::div//input[@placeholder='Last Name']"))
				.sendKeys(nomineeLN);

		By proselectBox = By.xpath(
				"//strong[contains(text(),'Relation to Proposer')]/ancestor::p/following-sibling::div//input[@placeholder='Select']");
		safeClick(utility.waitUntilVisibilityOfElementLocated(10L, proselectBox));

		By option1 = By.xpath("//div[contains(@class,'Dropdown_options')]/p[normalize-space()='" + propRelation + "']");
		safeClick(utility.waitUntilVisibilityOfElementLocated(10L, option1));

		driver.findElement(By.xpath("//label[.='DOB']/following-sibling::div//input[@placeholder='dd/mm/yyyy']")).click();
		utility.waitUntilElementIsCLickable(10L, driver.findElement(By.xpath("//abbr[@aria-label='" + dob + "']"))).click();
		dateDone.click();

		driver.findElement(By.xpath(
				"//strong[contains(text(),'Appointee First Name')]/ancestor::label/following-sibling::div//input[@placeholder='First Name']"))
				.sendKeys(appointeeFN);

		driver.findElement(By.xpath(
				"//strong[contains(text(),'Appointee Last Name')]/ancestor::label/following-sibling::div//input[@placeholder='Last Name']"))
				.sendKeys(appointeeLN);

		By aposelectBox = By.xpath(
				"//strong[contains(text(),'Appointee Relation')]/ancestor::p/following-sibling::div//input[@placeholder='Select']");
		safeClick(utility.waitUntilVisibilityOfElementLocated(10L, aposelectBox));

		By option2 = By.xpath("//div[contains(@class,'Dropdown_options')]/p[normalize-space()='" + apRelation + "']");
		safeClick(utility.waitUntilVisibilityOfElementLocated(10L, option2));

		driver.findElement(By.xpath(
				"//label[.='Appointee DOB']/following-sibling::div//input[@placeholder='dd/mm/yyyy']")).click();
		utility.waitUntilElementIsCLickable(10L, driver.findElement(By.xpath("//abbr[@aria-label='" + apDOB + "']"))).click();
		dateDone.click();
	}

	public void fillBankAccountDetails(String accNO, String accType, String IFSCcode) {
		driver.findElement(By.id("accountNumber")).sendKeys(accNO);

		By selectBox = By.xpath(
				"//strong[contains(text(),'Account Type')]/ancestor::p/following-sibling::div//input[@placeholder='Select']");
		safeClick(utility.waitUntilVisibilityOfElementLocated(10L, selectBox));

		By option = By.xpath("//p[normalize-space()='" + accType + "']");
		safeClick(utility.waitUntilVisibilityOfElementLocated(10L, option));

		WebElement ifscField = driver.findElement(By.xpath("//input[@id='IFSC']"));
		ifscField.sendKeys(IFSCcode);

		wait.until(ExpectedConditions.not(
				ExpectedConditions.attributeToBe(By.xpath("//input[@id='IFSC']"), "value", "")));
	}

	// ==================== acceptsTC() — LAZY-LOAD FIXED ====================

	public void acceptsTC() {
		try {
			WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));

			// Step 1: Wait for full page load
			longWait.until(d -> ((JavascriptExecutor) d)
					.executeScript("return document.readyState").equals("complete"));

			// Step 2: Scroll down gradually — forces lazy-rendered checkboxes into DOM
			for (int i = 0; i < 5; i++) {
				jsUtil.scrollByPixels(600);
				pause(500);
			}

			// Step 3: Wait for checkboxes to be present after scrolling
			longWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']")));
			pause(1000);

			// Step 4: Fetch all checkboxes and JS click each unselected one
			List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

			if (checkboxes.isEmpty()) {
				throw new RuntimeException("No checkboxes found on the declaration page.");
			}

			for (WebElement checkbox : checkboxes) {
				try {
					jsUtil.jsScrollIntoView(checkbox);
					jsUtil.scrollByPixels(-120); // clear sticky header
					pause(300);
					if (!checkbox.isSelected()) {
						jsUtil.jsClick(checkbox);
						pause(400);
					}
				} catch (Exception e) {
					System.out.println("⚠️ Skipping checkbox: " + e.getMessage());
				}
			}

			// Step 5: Click Next button
			pause(1000);
			WebElement nextBtn = longWait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//button[.//span[normalize-space()='Next']]")));
			jsUtil.jsScrollIntoView(nextBtn);
			jsUtil.scrollByPixels(-120);
			pause(500);
			jsUtil.jsClick(nextBtn);
			pause(2000);

		} catch (Exception e) {
			System.out.println("Error in acceptsTC: " + e.getMessage());
			throw new RuntimeException("Failed to accept Terms and Conditions: " + e.getMessage());
		}
	}

	// ==================== REVIEW & PAYMENT ====================

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
			return new WebDriverWait(driver, Duration.ofSeconds(5))
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
				new WebDriverWait(driver, Duration.ofSeconds(10))
						.until(ExpectedConditions.invisibilityOf(closeBtn));
			}
		} catch (Exception e) {
			System.out.println("Popup handling failed: " + e.getMessage());
		}
	}

	public boolean verifyPaymentPage(String payment) throws IOException {
		WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {
			longWait.until(d -> ((JavascriptExecutor) d)
					.executeScript("return document.readyState").equals("complete"));

			closeCallPopupIfPresent();

			boolean isPaymentLoaded = longWait.until(d -> {
				String currentUrl = d.getCurrentUrl().toLowerCase();
				boolean urlMatch = currentUrl.contains(payment.toLowerCase());
				boolean paymentTextVisible = d.findElements(
						By.xpath("//*[contains(text(),'Payment Options')]")).size() > 0;
				return urlMatch || paymentTextVisible;
			});

			if (isPaymentLoaded) {
				System.out.println("✅ Payment page loaded");
				return true;
			}

			return false;

		} catch (Exception e) {
			System.out.println("❌ Payment flow failed: " + e.getMessage());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("screenshots/payment_issue.png"));
			return false;
		}
	}

	public boolean verifyPaymentOption(String expectedText) {
		try {
			return new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[contains(text(),'" + expectedText + "')]")))
					.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verifyAmtVisible(String expectedText) {
		try {
			return new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[contains(text(),'" + expectedText + "')]")))
					.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}