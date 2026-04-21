package com.apollo247.testing.pages;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MyAppointmentPage {
	 WebDriver driver;
	    WebDriverWait wait;

	    public MyAppointmentPage (WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }
	@FindBy(css = "[title='My Appointments - View All']")
    private WebElement viewAll;

    @FindBy(xpath = "//div[.//*[contains(text(),'Sreedhara')]]//button[.//span[text()='Rebook']]")
    private WebElement rebook;
    
    @FindBy(xpath = "//span[text()='Continue']")
    private WebElement Conbtn;
     
    @FindBy(xpath = "//span[text()='Change']")
    private WebElement ClickChange;

    @FindBy(xpath ="//p[text()='indhira b']/../../..//div[@class=\"CheckoutPatientSelectionDialog_radioBtn__U8sBs\"]")
    private WebElement checkboxClick;
    
    @FindBy(xpath = "//span[text()='Proceed']")
    private WebElement proceed;
    
    @FindBy(xpath = "//span[contains(text(),'Appointment')]")
    private WebElement confirmBook;
  

	public WebElement getViewAll() {
		return viewAll;
	}

	public WebElement getRebook() {
		return rebook;
	}

	public WebElement getCheckboxClick() {
		return checkboxClick;
	}


	public WebElement getProceed() {
		return proceed;
	}

	public WebElement getClickChange() {
		return ClickChange;
	}


	public WebElement getConfirmBook() {
		return confirmBook;
	}

	public WebElement getConbtn() {
		return Conbtn;
	}

	public void Rebook() {
		wait.until(ExpectedConditions.elementToBeClickable(rebook)).click();
		wait.until(ExpectedConditions.elementToBeClickable(Conbtn)).click();
		
	}
	public void ChangePatient() {
		
		wait.until(ExpectedConditions.elementToBeClickable(ClickChange)).click();
		wait.until(ExpectedConditions.elementToBeClickable(checkboxClick)).click();
		wait.until(ExpectedConditions.elementToBeClickable(proceed)).click();
	}
	public boolean AppointmentVisible() {
	    try {
	        wait.until(ExpectedConditions.visibilityOf(confirmBook));
	        return confirmBook.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	
}
