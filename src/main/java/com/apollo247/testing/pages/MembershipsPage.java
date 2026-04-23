package com.apollo247.testing.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MembershipsPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public MembershipsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ================= LOCATORS =================

    @FindBy(xpath = "//span[contains(.,'My Memberships')]")
    private WebElement myMemberships;

    @FindBy(xpath = "//span[.='Activate Corporate Membership']")
    private WebElement activateCorporateMembershipBtn;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(xpath = "//span[.='Get Otp']")
    private WebElement getOtpBtn;

    @FindBy(xpath = "//*[contains(text(),'corporate benefits')]")
    private WebElement corporateBenefitsError;

    @FindBy(xpath = "//span[text()=' Ok, Got it!']")
    private WebElement okGotItBtn;

    @FindBy(xpath = "//span[text()='BUY NOW']")
    private WebElement buyNowBtn;

    @FindBy(xpath = "//*[contains(text(),'12 Months')]")
    private WebElement twelvemonthsPlan;

    @FindBy(xpath = "//button[contains(@class,'CircleLanding_planBtn__f3JcF')]")
    private WebElement joinNowBtn;

    // ================= BUSINESS LOGIC =================

    public void openMyMemberships() {
        WebElement profileIcon = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.className("ProfileNew_profileContainer__mUxKD")
            )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", profileIcon);

        WebElement membershipsLink = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(.,'My Memberships')]")
            )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", membershipsLink);
    }

    public void clickActivateCorporateMembership() {
        wait.until(ExpectedConditions.elementToBeClickable(activateCorporateMembershipBtn)).click();
    }

    public void enterCorporateEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void clickGetOtp() {
        wait.until(ExpectedConditions.elementToBeClickable(getOtpBtn)).click();
    }

    public boolean isCorporateErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(corporateBenefitsError));
            return corporateBenefitsError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCorporateErrorTextCorrect() {
        try {
            String text = wait.until(ExpectedConditions.visibilityOf(corporateBenefitsError)).getText();
            return text.contains("There are no corporate benefits");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOkGotIt() {
        wait.until(ExpectedConditions.elementToBeClickable(okGotItBtn)).click();
    }

    public void clickBuyNow() {
        wait.until(ExpectedConditions.elementToBeClickable(buyNowBtn)).click();
    }

    public void scrollToAndClickJoinNow(Integer months) {
        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//*[contains(text(),'" + months + " Months')]")
        ));

        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView({block:'center'});", twelvemonthsPlan);

        WebElement joinBtn = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(@class,'CircleLanding_planBtn__f3JcF')]")
            )
        );
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", joinBtn);
    }

    // ================= PLAN VALIDATION =================

    public boolean validatePlanDetails(List<String> details) {
        try {
            // Skip header row if present
            List<String> actualDetails = details.get(0).equals("planDetail") 
                ? details.subList(1, details.size()) 
                : details;

            for (String detail : actualDetails) {
                WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'" + detail + "')]")
                    )
                );
                if (!element.isDisplayed()) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}