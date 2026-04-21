package com.apollo247.testing.pages;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class filterDocterPage{
	WebDriver driver;
    WebDriverWait wait;

    public filterDocterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
	@FindBy(linkText = "General Physician/ Internal Medicine")
	private WebElement generalPhysician;

	@FindBy(xpath = "[type=\"button\"]")
	private WebElement relevanceDropdown;

	@FindBy(xpath = "//span[text()='Price - low to high']")
	private WebElement lowToHigh;

	@FindBy(xpath = "//span[text()='6-10']")
	private WebElement experience;

	@FindBy(xpath = "//span[text()='English']")
	private WebElement language;

	public WebElement getGeneralPhysician() {
		return generalPhysician;
	}

	
	public WebElement getRelevanceDropdown() {
		return relevanceDropdown;
	}

	public WebElement getLowToHigh() {
		return lowToHigh;
	}

	public WebElement getExperience() {
		return experience;
	}

	public WebElement getLanguage() {
		return language;
	}
	public void Relevance() {

	    By dropdownBy = By.xpath("//button[.//p[contains(text(),'Relevance')]]");

	    WebElement dropdown = wait.until(
	            ExpectedConditions.elementToBeClickable(dropdownBy)
	    );

	    // Scroll
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);

	    Actions act = new Actions(driver);

	    act.moveToElement(dropdown)        
	       .pause(Duration.ofSeconds(1)) 
	       .click()                  
	       .build()
	       .perform();
	    // Wait for dropdown options visible
	    By optionBy = By.xpath("//li//span[contains(text(),'Price')]");

	    WebElement option = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(optionBy)
	    );

	    wait.until(ExpectedConditions.elementToBeClickable(option));

	    option.click();
	}
	public void ClickDocter(String Docter) {
		WebElement doctorBtn = wait.until(
			    ExpectedConditions.elementToBeClickable(
			        By.xpath("//h3[contains(text(),'" + Docter+ "')]/ancestor::div[contains(@class,'DoctorList')]//button")
			    )
			);
		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", doctorBtn);
	}
	public void ClickGeneral() {
		getGeneralPhysician().click();
	}

	
}
