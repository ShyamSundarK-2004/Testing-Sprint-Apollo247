package com.apollo247.testing.pages;

import java.time.Duration;

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

    // ================= GETTERS =================

    public WebElement getActivateCorporateMembershipBtn() {
        return activateCorporateMembershipBtn;
    }

    

	public WebElement getMyMemberships() {
		return myMemberships;
	}

	public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getGetOtpBtn() {
        return getOtpBtn;
    }

    public WebElement getCorporateBenefitsError() {
        return corporateBenefitsError;
    }

    public WebElement getOkGotItBtn() {
        return okGotItBtn;
    }

    public WebElement getBuyNowBtn() {
        return buyNowBtn;
    }

    public WebElement getTwelvemonthsPlan() {
        return twelvemonthsPlan;
    }

    public WebElement getJoinNowBtn() {
        return joinNowBtn;
    }

    // ================= BUSINESS LOGIC =================

    // Navigate to My Memberships from profile dropdown
    public void openMyMemberships() {
        WebElement profileIcon = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.className("ProfileNew_profileContainer__mUxKD")
            )
        );
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", profileIcon
        );

        WebElement membershipsLink = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(.,'My Memberships')]")
            )
        );
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", membershipsLink
        );
    }

    public void clickActivateCorporateMembership() {
        wait.until(ExpectedConditions.elementToBeClickable(
            activateCorporateMembershipBtn)
        ).click();
    }

    public void enterCorporateEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void clickGetOtp() {
        wait.until(ExpectedConditions.elementToBeClickable(getOtpBtn)).click();
    }

    // Returns true if corporate error message is visible
    public boolean isCorporateErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(corporateBenefitsError));
            return corporateBenefitsError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Returns the error text after visibility confirmed
    public String getCorporateErrorText() {
        return corporateBenefitsError.getText();
    }

    public void clickOkGotIt() {
        wait.until(ExpectedConditions.elementToBeClickable(okGotItBtn)).click();
    }

    public void clickBuyNow() {
        wait.until(ExpectedConditions.elementToBeClickable(buyNowBtn)).click();
    }

    // Accepts months integer — no WebDriverWait param needed from steps
    public void scrollToAndClickJoinNow(Integer months) {
        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//*[contains(text(),'" + months + " Months')]")
        ));
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center'});", twelvemonthsPlan
        );
        joinNowBtn.click();
    }

    // Checks page source for a given string — used by DataTable step
    public boolean isPlanDetailVisible(String detail) {
        return driver.getPageSource().contains(detail);
    }

    public boolean isPlanVisible() {
        String pageText = driver.getPageSource();
        return pageText.contains("12 months") || pageText.contains("12 Months");
    }

    public boolean isPriceVisible() {
        return driver.getPageSource().contains("199");
    }
}