package com.apollo247.testing.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationsPage {

    WebDriver driver;
    WebDriverWait wait;

    public NotificationsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ================= LOCATORS =================

    @FindBy(className = "ProfileNew_profileContainer__mUxKD")
    private WebElement profileIcon;

    @FindBy(xpath = "//span[contains(.,'Manage Family Members')]")
    private WebElement manageFamilyMembers;

    @FindBy(xpath =
        "//span[.='Push Notifications']/..//div[@class='SettingsCard_toggleButton__m9vLV']")
    private WebElement pushNotificationsToggle;

    @FindBy(xpath =
        "//span[.='SMS Notifications']/..//div[@class='SettingsCard_toggleButton__m9vLV']")
    private WebElement smsNotificationsToggle;

    // ================= HELPERS =================

    private void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    // ================= NAVIGATION =================

    public void openManageFamilyMembers() {
        click(profileIcon);
        click(manageFamilyMembers);
    }

    public void openNotificationPreferences() {

        WebElement notifLink = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//span[.='Notification Preferences']")
                )
        );

        jsClick(notifLink);
    }

    // ================= ACTIONS =================

    public void enablePushNotifications() {
        jsClick(pushNotificationsToggle);
    }

    public void enableSmsNotifications() {
        jsClick(smsNotificationsToggle);
    }

    public void enableNotificationType(String type) {

        if (type.equalsIgnoreCase("Push Notifications")) {
            jsClick(pushNotificationsToggle);
            System.out.println("Enabled: " + type);
        } else if (type.equalsIgnoreCase("SMS Notifications")) {
            jsClick(smsNotificationsToggle);
            System.out.println("Enabled: " + type);
        }
    }

    // ================= VALIDATIONS =================

    public boolean isNotificationPageDisplayed() {

        String src = driver.getPageSource();

        return src.contains("Push Notifications")
                || src.contains("SMS Notifications")
                || src.contains("Notification Preferences");
    }

    public boolean isPushToggleVisible() {
        return pushNotificationsToggle.isDisplayed();
    }

    public boolean isSmsToggleVisible() {
        return smsNotificationsToggle.isDisplayed();
    }

    public boolean areAllTogglesVisible() {
        return pushNotificationsToggle.isDisplayed()
                && smsNotificationsToggle.isDisplayed();
    }

    public boolean isPushNotificationEnabled() {

        String cls = pushNotificationsToggle.getAttribute("class");

        return cls != null && (
                cls.contains("active")
                        || cls.contains("checked")
                        || cls.contains("enabled")
                        || cls.contains("toggleOn")
        );
    }

    public boolean isSmsNotificationEnabled() {

        String cls = smsNotificationsToggle.getAttribute("class");

        return cls != null && (
                cls.contains("active")
                        || cls.contains("checked")
                        || cls.contains("enabled")
                        || cls.contains("toggleOn")
        );
    }
}