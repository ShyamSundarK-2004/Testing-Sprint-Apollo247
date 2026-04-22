package com.apollo247.testing.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyMedicinePage {

    WebDriver driver;
    WebDriverWait wait;

    public BuyMedicinePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    // ================= LOCATORS =================

    // Shadow DOM popup cannot use @FindBy — handled inside closePopup() with By
    @FindBy(css = "[data-placeholder='Search Medicines']")
    private WebElement searchTrigger;

    @FindBy(css = "input[placeholder*='Search medicines']")
    private WebElement searchInput;

    @FindBy(xpath = "(//span[text()='Add'])[1]")
    private WebElement firstAddBtn;

    @FindBy(css = "a[class*='cart']")
    private WebElement cartLink;

    @FindBy(linkText = "Apollo Products")
    private WebElement apolloProductsLink;

    // ================= BASIC ACTION HELPERS =================

    private void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    private void type(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
    }

    // ================= ACTIONS =================

    /** Switch driver focus to any newly opened child window */
    public void switchToNewWindow(String parentHandle) {
        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parentHandle)) {
                driver.switchTo().window(win);
                break;
            }
        }
    }

    /** Close the shadow-DOM popup on the Buy Medicines page */
    public void closePopup() {
    	    try {
    	        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

    	        WebElement shadowHost = shortWait.until(
    	            ExpectedConditions.presenceOfElementLocated(
    	                By.cssSelector("ct-web-popup-imageonly")));

    	        SearchContext shadowRoot = shadowHost.getShadowRoot();

    	        WebElement closeBtn =
    	            shadowRoot.findElement(By.cssSelector("#close"));

    	        closeBtn.click();
    	        
    	        // Wait for popup to be invisible
    	        shortWait.until(
    	            ExpectedConditions.invisibilityOfElementLocated(
    	                By.cssSelector("ct-web-popup-imageonly")));
    	        
    	        System.out.println("Popup closed successfully");

    	    } catch (Exception e) {
    	        System.out.println("Popup not displayed or already closed");
    	    }
    	}
    

    /** Click the search bar placeholder, type the medicine name, then click Add */
    public void searchAndAddMedicine(String medicineName) {
        click(searchTrigger);
        type(searchInput, medicineName);
        
        // Wait for search results to appear (wait for dropdown/results container)
        try {
            Thread.sleep(1000); // Wait for search results to load
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Try to find and click the Add button for this specific medicine
        try {
            // First try: Look for the medicine name in results and click its Add button
            By medicineLocator = By.xpath(
                "//div[contains(text(),'" + medicineName + "')]" +
                "/ancestor::*[contains(@class,'product') or contains(@class,'item')]" +
                "//span[text()='Add']"
            );
            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(medicineLocator));
            addBtn.click();
            System.out.println("Product added: " + medicineName);
        } catch (Exception e) {
            // Fallback: Click the first Add button if specific medicine not found
            System.out.println("Could not find specific Add button for " + medicineName + ", using first Add button");
            click(firstAddBtn);
        }
        
        // Wait for product to be added (look for toast notification or wait)
        try {
            Thread.sleep(1000); // Give time for product to be added
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** Click the cart icon/link */
    public void clickCart() {
        click(cartLink);
        // Wait for cart to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** Click the Apollo Products navigation link */
    public void clickApolloProducts() {
        closePopup();
        try {
            Thread.sleep(500); // Wait for popup to close
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        click(apolloProductsLink);
    }
}
