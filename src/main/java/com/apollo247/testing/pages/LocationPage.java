package com.apollo247.testing.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPage {
	 WebDriver driver;
	    WebDriverWait wait;

	    public LocationPage(WebDriver driver) {
	        this.driver = driver;
	        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    }

	    @FindBy(xpath = "//div[@role='button']/..//h3[text()='Delhi']")
	    private WebElement location;

	    @FindBy(linkText = "General Physician in Delhi")
	    private WebElement physician;

	    @FindBy(css = "[type='button']")
	    private WebElement relevanceBtn;

	    @FindBy(xpath = "//span[text()='Most Liked']")
	    private WebElement mostLiked;

	    @FindBy(xpath = "//span[text()='View Description']")
	    private WebElement viewDescription;

	    @FindBy(xpath = "//div[contains(@class,'about')]")
	    private WebElement description;

	 
	    public WebElement getLocation() {
			return location;
		}

		public WebElement getPhysician() {
			return physician;
		}

		public WebElement getRelevanceBtn() {
			return relevanceBtn;
		}

		public WebElement getMostLiked() {
			return mostLiked;
		}

		public WebElement getViewDescription() {
			return viewDescription;
		}

		

		public void selectLocation() {
	        wait.until(ExpectedConditions.elementToBeClickable(location)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(physician)).click();
	    }

	   
	    public void sortByMostLiked() {
	        wait.until(ExpectedConditions.elementToBeClickable(relevanceBtn)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(mostLiked)).click();
	    }

	    public String openDoctor(String doctorName) {
	        wait.until(ExpectedConditions.elementToBeClickable(viewDescription)).click();
	        WebElement doctor = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//td[contains(@class,'Header_alignLeft') and normalize-space(text())='" + doctorName + "']")
	            ));
	            String name = doctor.getText();
	            doctor.click();
	            return name;
	    }
	    public String getDescription() {
	        return wait.until(ExpectedConditions.visibilityOf(description)).getText();
	    }
}
