package com.apollo247.testing.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.apollo247.testing.utilities.ExcelUtilities;

public class ManageFamilyPage {

    WebDriver driver;
    WebDriverWait wait;
    private final ExcelUtilities excel = new ExcelUtilities();

    public ManageFamilyPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ================= LOCATORS =================

    @FindBy(className = "ProfileNew_profileContainer__mUxKD")
    private WebElement profileIcon;

    @FindBy(xpath = "//span[contains(.,'Manage Family Members')]")
    private WebElement manageFamilyMembers;

    @FindBy(xpath = "//span[contains(.,'Add New Profile')]")
    private WebElement addNewProfile;

    @FindBy(css = "[placeholder='First Name']")
    private WebElement firstName;

    @FindBy(css = "[placeholder='Last name']")
    private WebElement lastName;

    @FindBy(css = "[placeholder='dd/mm/yyyy']")
    private WebElement dob;

    @FindBy(xpath = "//div[contains(@class,'AphSelect_select')]")
    private WebElement relationDropdown;

    @FindBy(xpath = "//span[.='Save']")
    private WebElement saveBtn;

    @FindBy(xpath = "//span[.='CONFIRM']")
    private WebElement confirmBtn;

    @FindBy(xpath = "//span[contains(text(),'Invalid first name')]")
    private WebElement firstNameError;

    // ================= BASIC ACTION HELPERS =================

    private void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    private void type(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
    }

    // ================= NAVIGATION =================

    public void openManageFamilyMembers() {
        WebElement profile = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.className("ProfileNew_profileContainer__mUxKD")
            )
        );
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView({block:'center'});", profile);
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", profile);

        WebElement manageFamilyEl = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(.,'Manage Family Members')]")
            )
        );
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", manageFamilyEl);
    }

    // ================= ADD FAMILY MEMBER =================

    public void clickAddNewProfile() {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(addNewProfile)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        jsClick(element);
    }

    public void enterFamilyMemberDetails(String fName, String lName, String dateOfBirth) {
        type(firstName, fName);
        type(lastName, lName);
        type(dob, dateOfBirth);
    }

    public void selectMaleAndBrother() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[placeholder='First Name']")
        ));

        WebElement male = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[contains(text(),'Male')]")
                )
        );
        male.click();

        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class,'AphSelect_select')]")
                )
        );
        dropdown.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[data-value='BROTHER']")
        ));

        WebElement brother = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("[data-value='BROTHER']")
        ));
        brother.click();
    }

    public void saveFamilyMember() {

        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));

        try {
            saveBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        }

        // Confirm dialog if present
        try {
            wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
        } catch (Exception e) {
            // silently handled
        }
    }

    // ================= SUCCESS / VALIDATION =================

    public boolean isFamilyMemberCreatedSuccessfully() {
        try {
            wait.until(ExpectedConditions.urlContains("my-account"));

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(.,'Add New Profile')]")
            ));

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // ================= VALIDATION TRIGGERS =================

    public void triggerFirstNameValidation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("[placeholder='First Name']")
        ));
        firstName.click();
        lastName.click();
    }

    public boolean isFirstNameErrorDisplayed() {
        try {
            WebElement error = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(),'Invalid first name')]")
                ));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ================= FULL FLOW =================

    public void addFamilyMember(String fName, String lName, String dobValue) {
        enterFamilyMemberDetails(fName, lName, dobValue);
        selectMaleAndBrother();
        saveFamilyMember();
    }

    // ================= EXCEL FLOW =================

    public void addFamilyMembersFromExcel() {
        int row = 1;

        while (true) {
            String fName;

            try {
                fName = excel.getExcelData("FamilyMembers", row, 0);
            } catch (Exception e) {
                // Row doesn't exist — end of data
                break;
            }

            if (fName == null || fName.trim().isEmpty()) {
                break;
            }

            String lName    = excel.getExcelData("FamilyMembers", row, 1);
            String dobValue = excel.getExcelData("FamilyMembers", row, 2);

            clickAddNewProfile();
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[placeholder='First Name']")
            ));

            addFamilyMember(fName, lName, dobValue);

            row++;
        }
    }
    // ================= POPUP HANDLING =================

    public void closePopup(SearchContext shadowRoot) {
        shadowRoot.findElement(By.cssSelector("#close")).click();
    }
}