package com.apollo247.testing.pages;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NegativeScenario {
    
    WebDriver driver;
    WebDriverWait wait;
    public NegativeScenario(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    @FindBy(xpath = "//span[@class=\"icon-down-arrow\"]")
    private WebElement docterspecilist;
    
    @FindBy(xpath = "//input[@placeholder=\"Example: Dermatology\"]")
    private WebElement inputbtn;
    
    @FindBy(xpath = "//span[@class=\"QuickBook_dateImage__hbyKS icon-date\"]")
    private WebElement datebtn;
    
    @FindBy(xpath = "//input[@placeholder=\"Search location\"]")
    private WebElement locationclick;
    
    @FindBy(xpath = "//li[@role='option']//span[contains(text(),'Chennai')]")
    private WebElement Selectplace;
    
    @FindBy(xpath = "//span[text()='Submit']")
    private WebElement submitBtn;
    
    @FindBy(xpath = "//input[@placeholder=\"Search Doctors, Specialities, Conditions etc.\"]")
    private WebElement Search;
    
    @FindBy(xpath="//p[text()='Please select Area/ Pin-Code']")
    private WebElement verification;
    
	public WebElement getVerification() {
		return verification;
	}
	public WebElement getDocterspecilist() {
		return docterspecilist;
	}

	public WebElement getInputbtn() {
		return inputbtn;
	}

	public WebElement getDatebtn() {
		return datebtn;
	}

	public WebElement getLocationclick() {
		return locationclick;
	}

	public WebElement getSelectplace() {
		return Selectplace;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	
	public WebElement getSearchbox() {
		return Search;
	}
	public void clickSpecialdoc(String speciality) {
		 wait.until(ExpectedConditions.elementToBeClickable(docterspecilist)).click();
		    inputbtn.sendKeys(speciality);

		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//span[normalize-space()='" + speciality + "']")))
		        .click();
		    
	}
	public void SelectDate(String dateclick) {
		 // Select Date
	    wait.until(ExpectedConditions.elementToBeClickable(datebtn)).click();

	    wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[contains(@class,'react-calendar__tile')]//abbr[text()='" + dateclick + "']")))
	        .click();
	}
	public void location(String loc) {
		 By locationInput = By.xpath("//input[@placeholder='Search location']");
		    wait.until(ExpectedConditions.elementToBeClickable(locationInput)).click();

		    WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locationInput));
		    input.clear();
		    input.sendKeys(loc);

		    // Wait for dropdown suggestion
		    By option = By.xpath("//li[@role='option']//span[contains(text(),'" + loc + "')]");

		    WebElement city = wait.until(ExpectedConditions.elementToBeClickable(option));

		    // Click properly
		    try {
		        city.click();
		    } catch (Exception e) {
		        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", city);
		    }

		    //  IMPORTANT: Wait until value is updated
		    wait.until(ExpectedConditions.attributeContains(input, "value", loc));
	}
	public void SubmitSearch() {
		 wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
	}
	public String ErrorMsg() {
		    wait.until(ExpectedConditions.visibilityOf(verification));
		    return verification.getText();
		}
	
	public void searchSpeciality(String speciality) {

        wait.until(ExpectedConditions.elementToBeClickable(Search)).click();
        Search.clear();

        //  human typing simulation
        for(char c : speciality.toCharArray()) {
            Search.sendKeys(String.valueOf(c));
            try { Thread.sleep(150); } catch(Exception e) {}
        }

        //  dropdown select
        WebElement option = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class=\"Search_specialtyDetails__dNIkG\"]/..//p[normalize-space()='" + speciality + "']")));
        wait.until(ExpectedConditions.elementToBeClickable(option));
        option.click();
    }
   
	public String NoDoctorsMessage() {

	    WebElement msg = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//span[text()='Go To Homepage']/../..//h5")
	        )
	    );

	    return msg.getText();
	}
		
	
    


}