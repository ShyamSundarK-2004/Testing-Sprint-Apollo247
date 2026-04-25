package com.apollo247.testing.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;

public class NotificationsPage {

    WebDriver driver;
    WebDriverWait wait;

    public NotificationsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ================= NAVIGATION =================

    public void openManageFamilyMembers() {
        WebElement profile = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.className("ProfileNew_profileContainer__mUxKD")
            )
        );
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView({block:'center'});", profile);
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", profile);

        WebElement manageFamilyEl = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(.,'Manage Family Members')]")
            )
        );
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", manageFamilyEl);
    }

    public void openNotificationPreferences() {
        WebElement notifLink = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Notification Preferences')]")
            )
        );
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", notifLink);
    }

    // ================= ACTIONS =================

    public void enablePushNotifications() {
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//span[.='Push Notifications']/..//input[@type='checkbox']")
        ));
        if (!checkbox.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }
    }

    public void enableSmsNotifications() {
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//span[.='SMS Notifications']/..//input[@type='checkbox']")
        ));
        if (!checkbox.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }
    }

    public void enableNotificationType(String type) {
        String label = type.equalsIgnoreCase("Push Notifications")
            ? "Push Notifications"
            : "SMS Notifications";
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//span[.='" + label + "']/..//input[@type='checkbox']")
        ));
        if (!checkbox.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }
    }

    public void enableMultipleNotifications(DataTable table) {
        List<String> list = table.asList();
        for (int i = 1; i < list.size(); i++) {
            enableNotificationType(list.get(i));
        }
    }

    // ================= VALIDATIONS =================

    public boolean isNotificationPageDisplayed() {
        String src = driver.getPageSource();
        return src.contains("Push Notifications")
            || src.contains("SMS Notifications")
            || src.contains("Notification Preferences");
    }

    public boolean isPushNotificationEnabled() {
        try {
            WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[.='Push Notifications']/..//input[@type='checkbox']")
            ));
            return checkbox.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSmsNotificationEnabled() {
        try {
            WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[.='SMS Notifications']/..//input[@type='checkbox']")
            ));
            return checkbox.isSelected();
        } catch (Exception e) {
            return false;
        }
    }
}