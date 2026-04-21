package com.apollo247.testing.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApolloProductsPage {

    WebDriver driver;
    WebDriverWait wait;

    public ApolloProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(50));

    }

    // ================= LOCATORS =================

    @FindBy(xpath = "//a[@href='/shop-by-category/apollo-personal-care']")
    private WebElement personalCareLink;

    @FindBy(xpath = "//a[text()='Skin Care']")
    private WebElement skinCareLink;

    @FindBy(xpath = "(//div[contains(@class,'ProductCard')])[1]//button[@aria-label='Add']")
    private WebElement firstAddBtn;

    // ================= BASIC ACTION HELPERS =================

    private void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    // ================= ACTIONS =================

    /** Click Personal Care under Apollo Products via JS */
    public void clickPersonalCare() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("//a[@href='/shop-by-category/apollo-personal-care']")));
        jsClick(personalCareLink);
    }

    /** Click the Skin Care sub-category */
    public void clickSkinCare() {
        click(skinCareLink);
    }

    /** JS-click the first Add button on the product listing */
    public void addFirstProduct() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath(
                        "(//div[contains(@class,'ProductCard')])[1]//button[@aria-label='Add']")));
        wait.until(ExpectedConditions.elementToBeClickable(firstAddBtn));
        jsClick(firstAddBtn);
    }

    /** Return the Add button after clicking, so the caller can assert its text */
    public WebElement getAddButton() {
        return wait.until(ExpectedConditions.visibilityOf(firstAddBtn));
    }
}
