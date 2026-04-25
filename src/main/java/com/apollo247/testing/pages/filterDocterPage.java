package com.apollo247.testing.pages;

import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.Nullable;
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
	 
	@FindBy(xpath ="//button/..//span[text()='View Profile']")
	private WebElement  viewProfile;
	
	@FindBy(xpath="//span[text()='Yes']")
	private WebElement recommend;
	
	

	@FindBy(xpath="//span[text()='Write Review']")
	private WebElement review;

	@FindBy(xpath="//button[text()='Doctor Behaviour']")
	private WebElement behaviour;
	
	@FindBy(css="[placeholder=\"Type here...\"]")
	private WebElement writereview;
	
	@FindBy(xpath="//span[text()='Submit']")
	private WebElement Submitbtn;
	 
	@FindBy(xpath="//h4[text()='Thank you for your feedback !']")
	private WebElement Thankyou;

	public WebElement getThankyou() {
		return Thankyou;
	}

	public WebElement getSubmitbtn() {
		return Submitbtn;
	}

	public WebElement getReview() {
		return review;
	}

	public WebElement getBehaviour() {
		return behaviour;
	}

	public WebElement getWritereview() {
		return writereview;
	}

	public WebElement getRecommend() {
		return recommend;
	}

	public WebElement getViewProfile() {
		return viewProfile;
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
	public void view() {
		wait.until(ExpectedConditions.elementToBeClickable(viewProfile)).click();
		
	}
	public void ClickGeneral() {
		WebElement general = wait.until(
		        ExpectedConditions.elementToBeClickable(generalPhysician ) );
		    general.click();
		
	}
	
	public void writeReviewFlow(String reviewText) {

	    // Click Recommend = Yes
	    wait.until(ExpectedConditions.elementToBeClickable(recommend)).click();

	    // Click Write Review
	    wait.until(ExpectedConditions.elementToBeClickable(review)).click();

	    // Select behaviour
	    wait.until(ExpectedConditions.elementToBeClickable(behaviour)).click();

	    // Enter review text
	    wait.until(ExpectedConditions.visibilityOf(writereview)).sendKeys(reviewText);

	    // Click Submit
	    wait.until(ExpectedConditions.elementToBeClickable(Submitbtn)).click();
	}
	public String ThankYouMessage() {
	    return wait.until(ExpectedConditions.visibilityOf(Thankyou)).getText();
	}
	

	
}
