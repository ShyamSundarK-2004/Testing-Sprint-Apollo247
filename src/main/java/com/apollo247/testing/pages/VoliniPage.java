package com.apollo247.testing.pages;


import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
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

    @FindBy(xpath = "//label[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'inflammation')]")
    private WebElement inflammationFilter;

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

    /** Scroll to and click the Inflammation filter */
    public void clickInflammationFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(inflammationFilter));
        jsScrollIntoView(inflammationFilter);
        jsClick(inflammationFilter);
    }

    /** Add the first product shown after applying the filter */
    public void addFirstProduct() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    org.openqa.selenium.By.xpath("(//button[@aria-label='Add'])[1]")));
            jsScrollIntoView(firstAddBtn);
            wait.until(ExpectedConditions.visibilityOf(firstAddBtn));
            jsClick(firstAddBtn);
            System.out.println("Volini product added successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
