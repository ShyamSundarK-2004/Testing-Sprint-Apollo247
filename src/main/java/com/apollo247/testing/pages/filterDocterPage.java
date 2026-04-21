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

	@FindBy(xpath = "//span[text()='6-10']")
	private WebElement experience;

	@FindBy(xpath = "//span[text()='English']")
	private WebElement language;
	 
	@FindBy(xpath ="//button[.//span[text()='Continue']]")
	private WebElement  Continuebutton;

	public WebElement getContinuebutton() {
		return Continuebutton;
	}

	public WebElement getGeneralPhysician() {
		return generalPhysician;
	}

	public WebElement getExperience() {
		return experience;
	}

	public WebElement getLanguage() {
		return language;
	}
	public void Relevance() {

	    By dropdownBy = By.xpath("//button[contains(.,'Relevance')]");

	    WebElement dropdown = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(dropdownBy)
	    );

	    wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
	    
	 // Scroll into view
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);

	    // Wait for dropdown option
	    By optionBy = By.xpath("//span[normalize-space()='Price - low to high']");

	    WebElement option = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(optionBy)
	    );

	    wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	}
	public void filter() {
		wait.until(ExpectedConditions.elementToBeClickable(experience)).click();
		wait.until(ExpectedConditions.elementToBeClickable(language)).click();
	}
	public void clickFirstDoctor() {

	    WebElement doctorBtn = wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[contains(@class,'DoctorCard')]//span[text()='Online Consult']")
	        )
	    );

	    doctorBtn.click();  // normal click podhum
	}
	public void Clickconbtn() {
		wait.until(ExpectedConditions.visibilityOf(Continuebutton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(Continuebutton)).click();
	}
	public void ClickGeneral() {
		getGeneralPhysician().click();
	}

	
}
