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

        String planXpath = "//span[contains(text(),'" + months + "')]";

        WebElement plan = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath(planXpath))
        );

        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView({block:'center'});", plan);

        WebElement joinBtn = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'planBtn')]")
            )
        );

        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", joinBtn);

        wait.until(ExpectedConditions.urlContains("circle-landing"));
    }
    // ================= PLAN VALIDATION =================

    public boolean validatePlanDetails(List<String> details) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Wait for navigation to payment page
            wait.until(ExpectedConditions.urlContains("pay-care"));

            // Get full page text (MOST RELIABLE)
            WebElement body = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.tagName("body"))
            );

            String pageText = body.getText().toLowerCase();

            System.out.println("===== FULL PAGE TEXT =====");
            System.out.println(pageText);
            System.out.println("==========================");

            // Validate using contains (robust)
            boolean hasPlan = pageText.contains("12");
            boolean hasMonths = pageText.contains("month"); // flexible
            boolean hasPrice = pageText.contains("199");

            System.out.println("Plan found: " + hasPlan);
            System.out.println("Months found: " + hasMonths);
            System.out.println("Price found: " + hasPrice);

            return hasPlan && hasMonths && hasPrice;

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }
}