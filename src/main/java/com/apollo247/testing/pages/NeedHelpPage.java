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

public class NeedHelpPage {

    WebDriver driver;
    WebDriverWait wait;

    public NeedHelpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ================= LOCATORS =================

    @FindBy(className = "ProfileNew_profileContainer__mUxKD")
    private WebElement profileIcon;

    @FindBy(xpath = "//span[text()='Need Help']")
    private WebElement needHelpOption;

    @FindBy(xpath = "//div[text()='Medicines']")
    private WebElement medicinesCategory;

    // ================= HELPERS =================

    private void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    // ================= BUSINESS LOGIC =================

    public void openNeedHelp() {

        // Close popup if present
        try {
            WebElement popup = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector("ct-web-popup-imageonly")
                    )
            );

            SearchContext shadow = popup.getShadowRoot();
            shadow.findElement(By.id("close")).click();

        } catch (Exception ignored) {}

        click(profileIcon);
        click(needHelpOption);
    }

    public void clickMedicinesCategory() {
        jsClick(medicinesCategory);
    }

    public boolean areHelpCategoriesVisible() {

        String pageText = driver.getPageSource();

        return pageText.contains("Medicines")
                || pageText.contains("Doctor Appointments")
                || pageText.contains("Lab Tests")
                || pageText.contains("Unsubscribe")
                || pageText.contains("OneApollo Membership")
                || pageText.contains("Circle Membership")
                || pageText.contains("Account & Health Records")
                || pageText.contains("Apollo SBI Card Select")
                || pageText.contains("Insurance Related Query");
    }

    public boolean isMedicinesPageLoaded() {

        wait.until(ExpectedConditions.urlContains("apollopharmacy"));

        return driver.getCurrentUrl().contains("apollopharmacy")
                || driver.getPageSource().contains("Medicines");
    }
}