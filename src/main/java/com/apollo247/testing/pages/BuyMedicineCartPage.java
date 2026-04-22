package com.apollo247.testing.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyMedicineCartPage {

    WebDriver driver;
    WebDriverWait wait;

    public BuyMedicineCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    // ================= LOCATORS =================

    // test1 – product name assertion (generic - updated to find any product in cart)
    @FindBy(xpath = "//div[contains(@class, 'cartitemname') or contains(@class, 'product-name')]")
    private WebElement doloProductName;

    // test4 – cart icon (aria-label)
    @FindBy(css = "a[aria-label='Cart Icon']")
    private WebElement cartIcon;

    // test4 – quantity dropdown for Wet Wipes
    @FindBy(xpath = "//h2[text()='Apollo Life Premium Citrus Refreshing Wet Wipes, 60 (2x30) Count']" +
                    "/../../../../..//div[contains(@class,'MedicineProductCard_optionHead')]")
    private WebElement qtyDropdown;

    // test4 – quantity option "3"
    @FindBy(xpath = "//p[contains(@class,'MedicineProductCard_ddQty') and normalize-space()='3']")
    private WebElement qty3;

    // test5 – cart icon (class-based)
    @FindBy(xpath = "//a[contains(@class,'cart')]")
    private WebElement cartIconAlt;

    // test5 – generic cart container (used to confirm page loaded)
    @FindBy(xpath = "//*[contains(text(),'Cart') or contains(@class,'cart')]")
    private WebElement cartContainer;

    // test5 – empty cart message
    @FindBy(xpath = "//*[contains(text(),'Your cart is empty') or contains(text(),'empty cart')]")
    private WebElement emptyCartMsg;

    // test5 – cart item count badge
    @FindBy(xpath = "//*[contains(@class,'cart-count') or contains(@id,'cart-count')]")
    private WebElement cartCount;

    // test5 – continue shopping button
    @FindBy(xpath = "//a[contains(text(),'Continue Shopping') or contains(@class,'continue')]")
    private WebElement continueShopping;

    // ================= BASIC ACTION HELPERS =================

    private void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    // ================= ACTIONS =================

    // ---- test1 ----

    /** Wait for and return the Dolo-650 product name text (test1) */
    public String getProductNameText() {
        return wait.until(ExpectedConditions.visibilityOf(doloProductName)).getText();
    }
    
    /** Get product name text by searching for specific product name in cart */
    public String getProductNameTextByProduct(String productName) {
        try {
            // First, check if cart is empty
            boolean isCartEmpty = false;
            try {
                driver.findElement(By.xpath("//*[contains(text(),'YOUR CART IS EMPTY') or contains(text(),'cart is empty')]"));
                isCartEmpty = true;
                System.out.println("Cart is empty! Product was not added successfully.");
                return "";
            } catch (Exception e) {
                // Cart is not empty, continue
            }
            
            // Wait for cart to load - look for any product-related elements
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//*[contains(@class, 'cart') or contains(@class, 'product') or contains(@class, 'item')]")
            ));
            
            // Try multiple locator strategies to find the product in cart
            By[] locators = {
                By.xpath("//div[contains(text(),'" + productName + "')]"),
                By.xpath("//span[contains(text(),'" + productName + "')]"),
                By.xpath("//h2[contains(text(),'" + productName + "')]"),
                By.xpath("//h1[contains(text(),'" + productName + "')]"),
                By.xpath("//p[contains(text(),'" + productName + "')]"),
                By.xpath("//a[contains(text(),'" + productName + "')]"),
                By.xpath("//*[@class='cartitemname' and contains(text(),'" + productName + "')]"),
                By.xpath("//div[@class='product-name' and contains(text(),'" + productName + "')]"),
                By.xpath("//*[contains(normalize-space(),'" + productName + "')]")
            };
            
            for (By locator : locators) {
                try {
                    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                    String text = element.getText().trim();
                    if (!text.isEmpty()) {
                        System.out.println("Found product: " + text);
                        return text;
                    }
                } catch (Exception e) {
                    // Try next locator
                    continue;
                }
            }
            
            // If nothing found, log all visible text to help debug
            System.out.println("DEBUG: Product '" + productName + "' not found. All visible text in cart:");
            java.util.List<WebElement> allElements = driver.findElements(By.xpath("//*"));
            for (WebElement elem : allElements) {
                try {
                    String text = elem.getText().trim();
                    if (!text.isEmpty() && text.length() < 150 && !text.contains("\n\n")) {
                        System.out.println("  - " + text);
                    }
                } catch (Exception e) {
                    // Skip elements that can't be accessed
                }
            }
            
        } catch (Exception e) {
            System.out.println("Exception finding product in cart: " + productName + " - " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    // ---- test4 ----

    /** Click Cart Icon via aria-label (test4) */
    public void clickCartIcon() {
        click(cartIcon);
    }

    /** Open the quantity dropdown and select 3 (test4) */
    public void changeQuantity(String quantity) {
        jsClick(qtyDropdown);
        WebElement qtyOption = driver.findElement(
            By.xpath("//p[normalize-space()='" + quantity + "']")
        );
        jsClick(qtyOption);
    }

    // ---- test5 ----

    /** Click Cart Icon via class (test5) */
    public void clickCartIconAlt() {
        jsClick(wait.until(ExpectedConditions.elementToBeClickable(cartIconAlt)));
    }

    /** Wait for the cart page/popup to be visible (test5) */
    public void waitForCartToLoad() {
        wait.until(ExpectedConditions.visibilityOf(cartContainer));
    }

    /** Return the empty cart message element (test5) */
    public WebElement getEmptyCartMessage() {
        return wait.until(ExpectedConditions.visibilityOf(emptyCartMsg));
    }

    /** Return the cart count badge element (test5) */
    public WebElement getCartCount() {
        return wait.until(ExpectedConditions.visibilityOf(cartCount));
    }

    /** Return the Continue Shopping button element (test5) */
    public WebElement getContinueShoppingButton() {
        return wait.until(ExpectedConditions.visibilityOf(continueShopping));
    }

	public void changeQuantityToThree() {
    jsClick(wait.until(ExpectedConditions.elementToBeClickable(qtyDropdown)));
    jsClick(wait.until(ExpectedConditions.elementToBeClickable(qty3)));
}
}
