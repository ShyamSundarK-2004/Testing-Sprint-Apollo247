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
        WebElement shadowHost = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("ct-web-popup-imageonly")));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        shadowRoot.findElement(By.cssSelector("#close")).click();
    }

    /** Click the search bar placeholder, type the medicine name, then click Add */
    public void searchAndAddMedicine(String medicineName) {
        click(searchTrigger);
        type(searchInput, medicineName);
        click(firstAddBtn);
    }

    /** Click the cart icon/link */
    public void clickCart() {
        click(cartLink);
    }

    /** Click the Apollo Products navigation link */
    public void clickApolloProducts() {
        click(apolloProductsLink);
    }
}
