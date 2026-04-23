package com.apollo247.testing.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.apollo247.testing.utilities.WebdriverUtility;

public class HealthInsurance_PolicyReview {
	public WebDriverWait wait;
	public WebDriver driver;
	public WebdriverUtility utility;
	
	public HealthInsurance_PolicyReview (WebDriver driver) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.driver = driver;
		this.utility = new WebdriverUtility();
		this.utility.initializeDriver(driver); // Pass the active driver to utility
	}
	
	//-------locator findings-----------
	
	@FindBy(xpath = "//span[normalize-space()='Proceed']")
	WebElement policyProceedButton;
	
	
	//--------getters----------
	
	//---Business Logic--------
	public void clickPolicyProceedButton() {
		//policyProceedButton.click();
		utility.waituntilPresenceOfElementLocated(10L, By.xpath("//span[normalize-space()='Proceed']")).click();
	}

}