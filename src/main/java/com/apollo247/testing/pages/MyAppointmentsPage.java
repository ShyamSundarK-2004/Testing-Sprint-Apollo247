package com.apollo247.testing.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAppointmentsPage {

    public WebDriver driver;
    public WebDriverWait wait;

    public MyAppointmentsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(className = "ProfileNew_profileContainer__mUxKD")
    private WebElement profileIcon;

    @FindBy(xpath = "//span[contains(.,'My Appointments')]")
    private WebElement myAppointments;
    
    // Getter Methods
    public WebElement getProfileIcon() {
        return profileIcon;
    }

    public WebElement getMyAppointments() {
        return myAppointments;
    }
    public String getAppointmentsHeadingText() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
            .until(ExpectedConditions.urlContains("appointments"));

        return new WebDriverWait(driver, Duration.ofSeconds(30))
            .until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'My Appointments')]")
            )).getText();
    }

    // Navigate to My Appointments
    public void openMyAppointments() {
        driver.get("https://www.apollo247.com/appointments");

        new WebDriverWait(driver, Duration.ofSeconds(30))
            .until(ExpectedConditions.urlContains("appointments"));

        new WebDriverWait(driver, Duration.ofSeconds(30))
            .until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'My Appointments')]")
            ));
    }

    // Validate appointments page content
    public boolean isAppointmentsPageDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.urlContains("appointments"));
            return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'My Appointments')]")
                )).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPageLoadedAfterRefresh() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.urlContains("appointments"));
            return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'My Appointments')]")
                )).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    // Refresh page
    public void refreshPage() {
        driver.navigate().refresh();

        new WebDriverWait(driver, Duration.ofSeconds(30))
            .until(ExpectedConditions.urlContains("appointments"));

        new WebDriverWait(driver, Duration.ofSeconds(30))
            .until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'My Appointments')]")
            ));
    }

    // Shadow DOM popup close - reusable
    public void closePopup(SearchContext shadowRoot) {
        shadowRoot.findElement(By.cssSelector("#close")).click();
    }
}