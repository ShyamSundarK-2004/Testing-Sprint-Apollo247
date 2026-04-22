package com.apollo247.testing.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {

    public WebDriver driver;
    public WebDriverWait wait;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ================= LOCATORS =================

    @FindBy(xpath = "//span[contains(.,'Logout')]")
    private WebElement logoutBtn;

    @FindBy(xpath = "//span[text()='Login']")
    private WebElement loginBtn;

    private By profileIconLocator =
            By.className("ProfileNew_profileContainer__mUxKD");

    // ================= BUSINESS METHODS =================

    public void clickProfileIcon() {

        WebElement profile = wait.until(
                ExpectedConditions.elementToBeClickable(profileIconLocator)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", profile);
    }

    public void clickLogout() {

        WebElement logout = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//span[contains(.,'Logout')]")
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", logout);
    }

    public boolean isLogoutSuccessful() {

        return wait.until(
                ExpectedConditions.visibilityOf(loginBtn)
        ).isDisplayed();
    }
}