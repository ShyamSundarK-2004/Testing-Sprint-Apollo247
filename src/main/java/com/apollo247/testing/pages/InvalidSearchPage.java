package com.apollo247.testing.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

public class InvalidSearchPage {

    WebDriver driver;
    WebDriverWait wait;

    public InvalidSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ================= SEARCH INPUT =================
    By searchBox = By.cssSelector("[data-placeholder='Search Medicines']");
    By searchInput = By.xpath("//input[@placeholder='Search Medicines']");
    // ================= INVALID RESULT HEADER =================
    By noResultHeader = By.xpath("//h3[contains(text(),'search results')]");

    // ================= EMPTY STATE CHECK =================
    By emptyState = By.xpath(
            "//*[contains(text(),'No results') or " +
            "contains(text(),'No products') or " +
            "contains(text(),'0 results') or " +
            "contains(text(),'not found') or " +
            "contains(text(),'No medicines')]"
    );
    
    // Additional flexible locators for empty state
    By emptyStateAlt1 = By.xpath("//div[contains(@class, 'empty') or contains(@class, 'no-result')]");
    By emptyStateAlt2 = By.xpath("//h2[contains(text(), 'No')] | //h3[contains(text(), 'No')] | //p[contains(text(), 'No')]");
    By noProductsContainer = By.xpath("//div[contains(text(), 'products')] | //div[contains(text(), 'medicines')]");

    // ================= ACTION =================
    public void searchMedicine(String medicine) {

        WebElement box = wait.until(
            ExpectedConditions.elementToBeClickable(searchBox)
        );

        box.click();

        Actions act = new Actions(driver);

        act.sendKeys(medicine)
           .pause(Duration.ofSeconds(1))
           .sendKeys(Keys.ENTER)
           .perform();
    }
    // ================= VALIDATION =================

    // Best method for your case (your screenshot scenario)
    public boolean isNoResultMessageDisplayed() {
        List<WebElement> result = driver.findElements(noResultHeader);
        if (!result.isEmpty() && result.get(0).isDisplayed()) {
            return true;
        }
        
        // Try alternative locators
        List<WebElement> altResult = driver.findElements(emptyStateAlt2);
        if (!altResult.isEmpty()) {
            return true;
        }
        
        return false;
    }

    // Generic fallback (handles multiple UI variations)
    public boolean isEmptyStateDisplayed() {
        // Try primary empty state locator
        List<WebElement> empty = driver.findElements(emptyState);
        if (!empty.isEmpty()) {
            return true;
        }
        
        // Try alternative locators
        List<WebElement> alt1 = driver.findElements(emptyStateAlt1);
        if (!alt1.isEmpty()) {
            return true;
        }
        
        List<WebElement> alt2 = driver.findElements(emptyStateAlt2);
        if (!alt2.isEmpty()) {
            return true;
        }
        
        return false;
    }

    // Check if any error/no results message is visible
    public boolean isAnyEmptyMessageDisplayed() {
        return isNoResultMessageDisplayed() || isEmptyStateDisplayed();
    }

    // Optional: get result text
    public String getNoResultText() {
        try {
            return driver.findElement(noResultHeader).getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Debug method: Get all visible text on the page to help identify the actual no-result message
    public String getPageText() {
        try {
            return driver.findElement(By.tagName("body")).getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Debug method: Check if page contains any of the expected no-result keywords
    public boolean pageContainsNoResultKeywords() {
        String bodyText = getPageText().toLowerCase();
        return bodyText.contains("no results") || 
               bodyText.contains("no products") || 
               bodyText.contains("no medicines") ||
               bodyText.contains("not found") ||
               bodyText.contains("0 results");
    }
}