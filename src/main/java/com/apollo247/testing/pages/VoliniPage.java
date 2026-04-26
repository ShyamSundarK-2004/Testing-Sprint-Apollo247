package com.apollo247.testing.pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VoliniPage {

    WebDriver driver;
    WebDriverWait wait;

    public VoliniPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    // ================= POPUP =================
    private void closePopup() {
        try {
            WebElement shadowHost = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector("ct-web-popup-imageonly")));

            SearchContext shadowRoot = shadowHost.getShadowRoot();
            WebElement closeBtn = shadowRoot.findElement(By.cssSelector("#close"));
            closeBtn.click();

            System.out.println("Popup closed successfully");

        } catch (Exception e) {
            System.out.println("Popup not found or already closed");
        }
    }

    // ================= DIRECT PAGE OPEN =================
    public void openVoliniPage() {
        driver.get("https://www.apollopharmacy.in/shop-by-brand/volini");
        wait.until(ExpectedConditions.urlContains("volini"));
    }

    // ================= NAVIGATION =================
    public void navigateToVoliniViaShopByBrand() {

        try {
            closePopup();

            driver.navigate().to("https://www.apollopharmacy.in/shop-by-brand/volini");

            wait.until(ExpectedConditions.urlContains("volini"));

            System.out.println("Navigated to Volini page successfully");

        } catch (Exception e) {
            throw new RuntimeException("Navigation to Volini via Shop By Brand failed: " + e.getMessage());
        }
    }

    // ================= FILTER =================
    public void clickInflammationFilter() {

        try {
            closePopup();

            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("window.scrollBy(0,400);");
            Thread.sleep(1500);

            // Click + symbol for Health Condition
            WebElement plusBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("div[class*='ux']:nth-of-type(3) > label[class*='vx']")
                    )
            );

            js.executeScript("arguments[0].click();", plusBtn);
            Thread.sleep(1500);

            // Click Inflammation checkbox
            WebElement inflammation = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("div[class*='RN']:nth-of-type(3) > label")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView(true);", inflammation);
            Thread.sleep(1000);

            js.executeScript("arguments[0].click();", inflammation);

            System.out.println("Inflammation filter applied successfully");

        } catch (Exception e) {
            throw new RuntimeException("Volini filter failed: " + e.getMessage());
        }
    }

    // ================= ADD PRODUCT =================
    public void addFirstProduct() {

        try {

            WebElement addBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("(//button[@aria-label='Add'])[1]")
                    )
            );

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", addBtn);

            Thread.sleep(1000);

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", addBtn);

            System.out.println("Volini product added successfully");

        } catch (Exception e) {
            throw new RuntimeException("Add product failed: " + e.getMessage());
        }
    }
}