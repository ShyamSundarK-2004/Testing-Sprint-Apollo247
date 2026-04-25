package com.apollo247.testing.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchDoctorPage {
    WebDriver driver;
    WebDriverWait wait;

    public SearchDoctorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this); // ✅ CRITICAL FIX
    }

    @FindBy(xpath = "//input[@placeholder=\"Search Doctors, Specialities, Conditions etc.\"]")
    private WebElement Searchbox;
    
    @FindBy(xpath = " //input[@id=\"phone-number\"]")
    private WebElement PhoneNo;
   
    public WebElement getPhoneNo() {
		return PhoneNo;
	}

	public WebElement getSearchbox() {
		return Searchbox;
	}

	public void searchSpeciality(String speciality) {

        wait.until(ExpectedConditions.elementToBeClickable(Searchbox)).click();
        Searchbox.clear();

        // 🔥 human typing simulation
        for(char c : speciality.toCharArray()) {
            Searchbox.sendKeys(String.valueOf(c));
            try { Thread.sleep(150); } catch(Exception e) {}
        }

        //  dropdown select
        WebElement option = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class=\"Search_specialtyDetails__dNIkG\"]/..//p[normalize-space()='" + speciality + "']")));
        wait.until(ExpectedConditions.elementToBeClickable(option));
        option.click();
    }
   
    public void selectDoctorByName() {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    	     List<WebElement> doctors = wait.until(
    	         ExpectedConditions.visibilityOfAllElementsLocatedBy(
    	        By.xpath("//a[contains(@class,'DoctorCard_doctorCard')]")
    	       )
          	);

    	     doctors.get(0).click();   
    }

    public void SelectSlot() {

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(.,'Schedule') or contains(.,'Continue') or contains(.,'Proceed')]")
        ));

        System.out.println("Button text: " + btn.getText());
        btn.click();
    }
    public void PhoneNumber(String phone) {
    	     WebElement PhoneField=wait.until(ExpectedConditions.elementToBeClickable(PhoneNo));
    	  //  Clear existing number properly
    	     PhoneField.click();
    	     PhoneField.sendKeys(Keys.CONTROL + "a"); // select all
    	     PhoneField.sendKeys(Keys.BACK_SPACE);    // delete

    	     // new number enter
    	     PhoneField.sendKeys(phone);

    	     System.out.println("Entered phone number: " + phone);
    	      
    	  
    }
    
    public String getAmountText() {

        WebElement amount = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'₹')]")
            )
        );

        return amount.getText();
    }
    public String getLast10DigitPhone() {

        String phoneText = PhoneNo.getAttribute("value");

        String digitsOnly = phoneText.replaceAll("[^0-9]", "");

        if (digitsOnly.length() < 10) {
            throw new RuntimeException("Invalid phone number: " + phoneText);
        }

        return digitsOnly.substring(digitsOnly.length() - 10);
    }
}
