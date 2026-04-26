package com.apollo247.testing.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
    	        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));

    	        try {
    	            WebElement shadowHost = shortWait.until(
    	                ExpectedConditions.presenceOfElementLocated(
    	                    By.cssSelector("ct-web-popup-imageonly")));

    	            SearchContext shadowRoot = shadowHost.getShadowRoot();
    	            WebElement closeBtn = shadowRoot.findElement(By.cssSelector("#close"));
    	            closeBtn.click();
    	            
    	            shortWait.until(
    	                ExpectedConditions.invisibilityOfElementLocated(
    	                    By.cssSelector("ct-web-popup-imageonly")));
    	            
    	            System.out.println("Popup closed successfully");
    	        } catch (Exception shadowDomError) {
    	            // Fallback: Try alternative popup close methods
    	            try {
    	                WebElement closeBtnAlt = shortWait.until(
    	                    ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'close')]")));
    	                closeBtnAlt.click();
    	                System.out.println("Popup closed via alternative method");
    	            } catch (Exception altError) {
    	                System.out.println("Popup not found - continuing (may not be displayed)");
    	            }
    	        }

    	    } catch (Exception e) {
    	        System.out.println("Popup handling skipped: " + e.getMessage());
    	    }
    	}
    

    /** Click the search bar placeholder, type the medicine name, then click Add */
    public void searchAndAddMedicine(String medicineName) {

        try {

            By triggerLocator =
                    By.cssSelector("[data-placeholder='Search Medicines']");

            By inputLocator =
                    By.cssSelector("input[placeholder*='Search medicines']");

            WebElement trigger = wait.until(
                    ExpectedConditions.elementToBeClickable(triggerLocator));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", trigger);

            WebElement input = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(inputLocator));

            input.click();
            input.sendKeys(Keys.CONTROL + "a");
            input.sendKeys(Keys.DELETE);
            input.sendKeys(medicineName);

            Thread.sleep(2000);

            List<WebElement> addButtons = driver.findElements(
                    By.xpath("//span[text()='Add'] | //button[contains(text(),'Add')]"));

            if (addButtons.size() > 0) {

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", addButtons.get(0));

                System.out.println("Product added: " + medicineName);

            } else {
                System.out.println("No product found: " + medicineName);
            }

            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("Error in searchAndAddMedicine: " + e.getMessage());
        }
    }
       
    /** Click the cart icon/link */
    public void clickCart() {
        try {
            click(cartLink);
            // Wait for cart to load
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } catch (Exception e) {
            System.out.println("Error clicking cart: " + e.getMessage());
        }
    }

    /** Click the Apollo Products navigation link */
    public void clickApolloProducts() {
        try {
            closePopup();
            try {
                Thread.sleep(500); // Wait for popup to close
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            click(apolloProductsLink);
        } catch (Exception e) {
            System.out.println("Error clicking Apollo Products: " + e.getMessage());
        }
    }
}
