
	package com.apollo247.testing.pages;

	import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.openqa.selenium.support.ui.ExpectedConditions;

	public class NegativeScenario{

	    WebDriver driver;
	    WebDriverWait wait;

	    public NegativeScenario(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    // Locators
	    By searchBox = By.id("search");  // update locator
	    By searchBtn = By.id("searchBtn");
	    By validationMsg = By.xpath("//span[contains(text(),'required')]");
	    By noDoctorsMsg = By.xpath("//div[contains(text(),'No doctors found')]");

	    // Actions
	    public void enterSearchText(String text) {
	        driver.findElement(searchBox).sendKeys(text);
	    }

	    public void clickSearchButton() {
	        driver.findElement(searchBtn).click();
	    }

	    public String getValidationMessage() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(validationMsg)).getText();
	    }

	    public String getNoDoctorsMessage() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(noDoctorsMsg)).getText();
	    }
	

}

