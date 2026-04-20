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



public class MyAppointmentsPage {

    public WebDriver driver;
    public WebDriverWait wait;

    // Constructor
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

    // Business Logic

    // Navigate to My Appointments
    public void openMyAppointments() {

        // Step 1: Click profile icon (FAST but safe)
        WebElement profile = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.className("ProfileNew_profileContainer__mUxKD")
                )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", profile);

        // Step 2: WAIT for panel to actually OPEN (this was missing ❗)
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(.,'My Appointments')]")
        ));

        // Step 3: Click My Appointments (fresh element)
        WebElement myAppointments = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//span[contains(.,'My Appointments')]")
                )
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myAppointments);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Step 1: Click profile icon safely
        wait.until(ExpectedConditions.elementToBeClickable(profileIcon)).click();

        // Step 2: Wait for panel/menu to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[contains(.,'My Appointments')]")
        ));

        // Step 3: Re-locate element (IMPORTANT for stale fix)
        WebElement myAppointmentsFresh = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(.,'My Appointments')]")
            )
        );

        // Step 4: Click using JS (bypass interception)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myAppointmentsFresh);
    }

    // Validate appointments page content
    public boolean isAppointmentsPageDisplayed() {
        String pageText = driver.getPageSource();
        return pageText.contains("Appointments") || pageText.contains("No Appointments");
    }

    // Validate page after refresh
    public boolean isPageLoadedAfterRefresh() {
        return driver.findElement(
                By.xpath("//*[contains(text(),'Appointments') or contains(text(),'No Appointments')]")
        ).isDisplayed();
    }

    // Refresh page
    public void refreshPage() {
        driver.navigate().refresh();

        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'Appointments') or contains(text(),'No Appointments')]")
            ));
    }

    // Shadow DOM popup close - reusable
    public void closePopup(SearchContext shadowRoot) {
        shadowRoot.findElement(By.cssSelector("#close")).click();
    }
}