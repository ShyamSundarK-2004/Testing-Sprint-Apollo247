package com.apollo247.testing.pages;

import java.time.Duration;
import java.util.List;
import java.util.Map;

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

    private ExcelUtilities excelUtilities = new ExcelUtilities();

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
        click(profileIcon);
        click(manageFamilyMembers);
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

        click(saveBtn);
        click(confirmBtn);

        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'successfully')]")
                )
        );

        System.out.println("Member created successfully");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[text()='OK']")
        )).click();
    }

    // ================= SUCCESS / VALIDATION =================

    public boolean isSuccessToastDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'successfully') or contains(text(),'created') or contains(text(),'added')]")
            )).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidationErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'required') or contains(text(),'invalid')]")
            )).isDisplayed();
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

        try {
            List<Map<String, String>> members =
                    excelUtilities.getExcelDataAsMap("FamilyMembers");

            for (Map<String, String> member : members) {

                String fName = member.get("firstName");
                String lName = member.get("lastName");
                String dob = member.get("dob");

                wait.until(ExpectedConditions.elementToBeClickable(addNewProfile));
                clickAddNewProfile();

                wait.until(ExpectedConditions.visibilityOf(firstName));

                addFamilyMember(fName, lName, dob);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to add family members from Excel: " + e.getMessage());
        }
    }

    // ================= POPUP HANDLING =================

    public void closePopup(SearchContext shadowRoot) {
        shadowRoot.findElement(By.cssSelector("#close")).click();
    }
}