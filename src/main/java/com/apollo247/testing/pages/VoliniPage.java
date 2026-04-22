package com.apollo247.testing.pages;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VoliniPage {

    WebDriver driver;
    WebDriverWait wait;

    private static final String VOLINI_URL = "https://www.apollopharmacy.in/shop-by-brand/volini";

    public VoliniPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(50));
     
    }

    // ================= LOCATORS =================

    @FindBy(css = "ct-web-popup-imageonly")
    private WebElement popupElement;

    // Filter By section locators
    @FindBy(xpath = "//button[contains(text(), 'Filter By')] | //div[contains(text(), 'Filter By')]")
    private WebElement filterByButton;
    
    // Health Condition category locator
    @FindBy(xpath = "//div[contains(text(), 'Health Condition')] | //span[contains(text(), 'Health Condition')]")
    private WebElement healthConditionCategory;
    
    // Inflammation checkbox/label locator - after Health Condition is expanded
    @FindBy(xpath = "//label[contains(., 'Inflammation')] | //span[text()='Inflammation']//ancestor::label | //input[@value='Inflammation']//following-sibling::label")
    private WebElement inflammationFilter;
    
    // Alternative locators for Inflammation with various selectors
    @FindBy(xpath = "//label[.//text()[contains(., 'Inflammation')]]")
    private WebElement inflammationLabel;
    
    @FindBy(xpath = "//span[text()='Inflammation']")
    private WebElement inflammationSpan;
    
    @FindBy(xpath = "//input[contains(@name, 'inflammation') or contains(@value, 'inflammation')]//ancestor::label")
    private WebElement inflammationInputLabel;

    @FindBy(xpath = "(//button[@aria-label='Add'])[1]")
    private WebElement firstAddBtn;

    // ================= BASIC ACTION HELPERS =================

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    private void jsScrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    // ================= ACTIONS =================

    /** Navigate directly to the Volini brand page */
    public void navigateTo() {
        driver.navigate().to(VOLINI_URL);
        wait.until(ExpectedConditions.urlContains("volini"));
    }

    /** Close popup if present on the page */
    private void closePopup() {
    	    try {
    	        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
    	        shortWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ct-web-popup-imageonly")));
    	        
    	        SearchContext shadowRoot = popupElement.getShadowRoot();
    	        WebElement closeBtn = shadowRoot.findElement(By.cssSelector("#close"));
    	        closeBtn.click();
    	        
    	        shortWait.until(ExpectedConditions.invisibilityOf(popupElement));
    	    } catch (Exception e) {
    	        System.out.println("Popup not found or already closed");
    	    }
    	}

    /** Scroll to and click the Inflammation filter - handles hierarchical filter structure */
    public void clickInflammationFilter() {
        try {
            // Close any popups that might be blocking
            closePopup();
            Thread.sleep(1000);
            
            // Wait for page to stabilize
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class]")));
            
            System.out.println("Attempting to find and click Inflammation filter...");
            
            // Try different strategies to find Inflammation
            WebElement inflammationElement = findInflammationElementByXPath();
            
            if (inflammationElement != null) {
                System.out.println("Found Inflammation element, scrolling into view and clicking...");
                jsScrollIntoView(inflammationElement);
                Thread.sleep(500);
                wait.until(ExpectedConditions.elementToBeClickable(inflammationElement));
                jsClick(inflammationElement);
                System.out.println("Inflammation filter clicked successfully");
                Thread.sleep(1500); // Wait for filter results to load
            } else {
                throw new RuntimeException("Could not find Inflammation filter element");
            }
            
        } catch (Exception e) {
            System.out.println("Error clicking Inflammation filter: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to click Inflammation filter", e);
        }
    }
    
    /** Helper method to find Inflammation element using direct XPath queries */
    private WebElement findInflammationElementByXPath() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        // List of XPath strategies to try
        String[] xpaths = {
            "//span[text()='Inflammation']",
            "//label[contains(text(), 'Inflammation')]",
            "//div[contains(text(), 'Inflammation')]",
            "//input[@value='Inflammation']",
            "//label[contains(., 'Inflammation')]",
            "//*[text()='Inflammation']",
            "//input[contains(@name, 'inflammation')]//following-sibling::label",
            "//span[contains(text(), 'Inflammation')]//ancestor::label"
        };
        
        for (int i = 0; i < xpaths.length; i++) {
            try {
                System.out.println("XPath Strategy " + (i + 1) + ": " + xpaths[i]);
                List<WebElement> elements = shortWait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpaths[i])));
                if (elements != null && !elements.isEmpty()) {
                    System.out.println("XPath Strategy " + (i + 1) + " SUCCESS! Found " + elements.size() + " element(s)");
                    return elements.get(0);
                }
            } catch (Exception e) {
                System.out.println("XPath Strategy " + (i + 1) + " failed");
            }
        }
        
        System.out.println("All XPath strategies failed");
        return null;
    }

    /** Add the first product shown after applying the filter */
    public void addFirstProduct() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//button[@aria-label='Add'])[1]")));
            jsScrollIntoView(firstAddBtn);
            wait.until(ExpectedConditions.visibilityOf(firstAddBtn));
            jsClick(firstAddBtn);
            System.out.println("Volini product added successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
