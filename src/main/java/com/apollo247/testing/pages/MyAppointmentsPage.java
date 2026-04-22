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

    // Navigate to My Appointments
    public void openMyAppointments() {

        // Step 1: Re-locate profile icon fresh via By (avoids stale @FindBy proxy)
        // and click via JS to avoid interception issues
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement profile = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.className("ProfileNew_profileContainer__mUxKD")
                    )
                );
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", profile);
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 3) throw e;
            }
        }

        // Step 2: Wait for panel to open — My Appointments span must be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[contains(.,'My Appointments')]")
        ));

        // Step 3: Re-locate My Appointments fresh via By and click via JS
        WebElement myAppointmentsFresh = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(.,'My Appointments')]")
            )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myAppointmentsFresh);

        // Step 4: Wait for appointments content to load
        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//*[contains(text(),'Appointments') or contains(text(),'No Appointments')]")
        ));
    }

    // Validate appointments page content
    public boolean isAppointmentsPageDisplayed() {
        try {
            String pageText = driver.getPageSource();
            return pageText.contains("Appointments") || pageText.contains("No Appointments");
        } catch (Exception e) {
            return false;
        }
    }

    // Validate page after refresh
    public boolean isPageLoadedAfterRefresh() {
        try {
            WebElement el = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Appointments') or contains(text(),'No Appointments')]")
                )
            );
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Refresh page
    public void refreshPage() {
        driver.navigate().refresh();
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'Appointments') or contains(text(),'No Appointments')]")
            ));
    }

    // Shadow DOM popup close - reusable
    public void closePopup(SearchContext shadowRoot) {
        shadowRoot.findElement(By.cssSelector("#close")).click();
    }
}