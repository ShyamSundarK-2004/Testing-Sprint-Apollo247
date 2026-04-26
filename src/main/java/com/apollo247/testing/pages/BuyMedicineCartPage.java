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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Cart Icon
    @FindBy(css = "a[aria-label='Cart Icon']")
    private WebElement cartIcon;

    // Wet Wipes quantity dropdown
    @FindBy(xpath = "//h2[contains(text(),'Apollo Life Premium Citrus Refreshing Wet Wipes')]/ancestor::div[contains(@class,'MedicineProductCard')]//div[contains(@class,'MedicineProductCard_optionHead')]")
    private WebElement qtyDropdown;

    // Quantity 3 option
    @FindBy(xpath = "//p[normalize-space()='3']")
    private WebElement qty3;

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public void changeQuantityToThree() {
        jsClick(wait.until(ExpectedConditions.elementToBeClickable(qtyDropdown)));
        jsClick(wait.until(ExpectedConditions.elementToBeClickable(qty3)));
    }

    public String getSelectedQuantity() {
        return wait.until(
            ExpectedConditions.visibilityOf(qtyDropdown)).getText().trim();
    }

    public String getProductNameTextByProduct(String productName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + productName + "')]"))).getText().trim();
    }

    public WebElement getEmptyCartMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Your cart is empty') or contains(text(),'empty cart')]")));
    }

    public WebElement getCartCount() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(@class,'cart-count') or contains(@id,'cart-count')]")));
    }

    public WebElement getContinueShoppingButton() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Continue Shopping') or contains(@class,'continue')]")
            )
        );
    }
}