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

        // JS click profile icon
        WebElement profile = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.className("ProfileNew_profileContainer__mUxKD")
            )
        );
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView({block:'center'});", profile);
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", profile);

        // JS click Need Help from dropdown
        WebElement needHelp = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Need Help']")
            )
        );
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", needHelp);
    }
    public void clickMedicinesCategory() {
        jsClick(medicinesCategory);
    }

    public boolean areHelpCategoriesVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='Medicines']")
            ));

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='Lab Tests']")
            ));

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='Doctor Appointments']")
            ));

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMedicinesPageLoaded() {
        try {
            wait.until(ExpectedConditions.or(

                ExpectedConditions.urlContains("apollopharmacy"),

                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Medicines')]")
                )

            ));
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}