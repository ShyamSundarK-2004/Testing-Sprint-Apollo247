package com.apollo247.testing.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchDoctorPage {
	 WebDriver driver;
	    WebDriverWait wait;

	    public SearchDoctorPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }
	
    
    @FindBy(xpath = "//span[@class=\"icon-down-arrow\"]")
    private WebElement Speciality;
    
    @FindBy(xpath = "//input[@placeholder=\"Example: Dermatology\"]")
    private WebElement SpecialistInput;
    
    @FindBy(xpath = "//span[@class=\"QuickBook_dateImage__hbyKS icon-date\"]")
    private WebElement dateIcon;
    
    
    @FindBy(xpath = "//input[@placeholder=\"Search location\"]")
    private WebElement location;
    
    @FindBy(xpath = "//li[@role='option']//span[contains(text(),'Chennai')]")
    private WebElement SelectLocation;
    
    @FindBy(xpath = "//span[text()='Submit']")
    private WebElement submitBtn;
    
//    @FindBy(xpath = "//div[@class=\"slots_date__Dy0W_ \"]/../../..//p[text()='20']")
//    private WebElement dateSlot;
//    
//    @FindBy(css = "[class=\"slots_slot__YYaL_ slots_selected__xaSp_\"]")
//    private WebElement TimeSlot;
    
    @FindBy(xpath = "//span[text()='Schedule Appointment']")
    private WebElement schedulebtn;

    @FindBy(xpath = "//span[text()='Change']")
    private WebElement change;

    @FindBy(xpath = "//span[text()='Add Patient']")
    private WebElement addPatientBtn;

    @FindBy(css = "[placeholder='First Name']")
    private WebElement firstName;

    @FindBy(css = "[placeholder='Last name']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@placeholder='DD / MM / YYYY']")
    private WebElement dob;

    @FindBy(xpath = "//button[contains(text(),'Select relation')]")
    private WebElement relationDropdown;

    @FindBy(xpath = "//button[text()='Male']")
    private WebElement maleButton;

    @FindBy(css = "[placeholder='Enter Email']")
    private WebElement email;

    @FindBy(css = "input[type='checkbox']")
    private WebElement Checkbox;
    

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement Save;
	public WebElement getSpeciality() {
		return Speciality;
	}


	public WebElement getSpecialistInput() {
		return SpecialistInput;
	}


	public WebElement getDateIcon() {
		return dateIcon;
	}



	public WebElement getLocation() {
		return location;
	}


	public WebElement getSelectLocation() {
		return SelectLocation;
	}



	public WebElement getSubmitBtn() {
		return submitBtn;
	}


//	public WebElement getDateSlot() {
//		return dateSlot;
//	}
//
//
//
//	public WebElement getTimeSlot() {
//		return TimeSlot;
//	}


	public WebElement getscheduleBtn() {
		return schedulebtn;
	}



	public WebElement getChange() {
		return change;
	}


	public WebElement getAddPatientBtn() {
		return addPatientBtn;
	}



	public WebElement getFirstName() {
		return firstName;
	}


	public WebElement getLastName() {
		return lastName;
	}


	public WebElement getDob() {
		return dob;
	}


	public WebElement getRelationDropdown() {
		return relationDropdown;
	}

	public WebElement getMaleButton() {
		return maleButton;
	}


	public WebElement getEmail() {
		return email;
	}



	public WebElement getCheckbox() {
		return Checkbox;
	}


	public WebElement getSave() {
		return Save;
	}
	public void SearchDoctor(String SpecialityName, String LocationName, String date) {

	    
	    wait.until(ExpectedConditions.elementToBeClickable(Speciality)).click();
	    SpecialistInput.sendKeys(SpecialityName);

	    wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//span[normalize-space()='" + SpecialityName + "']")))
	        .click();

	    // Select Date
	    wait.until(ExpectedConditions.elementToBeClickable(dateIcon)).click();

	    wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[contains(@class,'react-calendar__tile')]//abbr[text()='" + date + "']")))
	        .click();

	    driver.findElement(By.tagName("body")).click();

	    // Select Location
	    wait.until(ExpectedConditions.elementToBeClickable(location)).click();
	    location.clear();
	    location.sendKeys(LocationName);

	    // Wait for dropdown
	    By locationOption = By.xpath("//li[@role='option']//span[normalize-space()='" + LocationName + "']");

	    WebElement city = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(locationOption)
	    );

	    wait.until(ExpectedConditions.elementToBeClickable(city));

	    // Normal click first
	    try {
	        city.click();
	    } catch (Exception e) {
	        // fallback JS click
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", city);
	    }

	    wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
	}
	public void selectFirstDoctor() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    WebElement firstCard = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'QuickBook_slotWrapperCard')]")
	        )
	    );

	    String doctorName = firstCard.findElement(By.xpath(".//h3 | .//p")).getText();
	    System.out.println("Selecting Doctor: " + doctorName);

	    firstCard.findElement(By.xpath(".//a")).click();
	}
   

    public void SelectSlot() {
//        wait.until(ExpectedConditions.elementToBeClickable(dateSlot)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(TimeSlot)).click();
    	      wait.until(ExpectedConditions.elementToBeClickable(schedulebtn)).click();
    }

    public void AddPatient(String fName, String lName, String mail,String year, String month, String day) {
        change.click();
        addPatientBtn.click();

        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        selectDOB(year, month, day);

        relationDropdown.sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER);
        maleButton.click();

        email.sendKeys(mail);
        Checkbox.click();
        Save.click();
    }
    public void selectDOB(String year, String month, String day) {

        wait.until(ExpectedConditions.elementToBeClickable(dob)).click();

        // Click header → go to Year view
        WebElement header = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(@class,'react-calendar__navigation__label')]")
        ));
        header.click(); // month → year
        header.click(); // year → decade

        //  Navigate to correct decade dynamically
        while (true) {

            WebElement decadeRange = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(@class,'react-calendar__navigation__label')]")
            ));

            String text = decadeRange.getText(); // e.g. "2021 – 2030"

            int start = Integer.parseInt(text.split("–")[0].trim());
            int end = Integer.parseInt(text.split("–")[1].trim());
            int target = Integer.parseInt(year);

            if (target >= start && target <= end) {
                break; // correct decade reached
            } else if (target < start) {
                driver.findElement(By.xpath("//button[contains(@class,'prev-button')]")).click();
            } else {
                driver.findElement(By.xpath("//button[contains(@class,'next-button')]")).click();
            }
        }

        // ✅ Select Year
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button//abbr[text()='" + year + "']")
        )).click();

        // ✅ Select Month
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button//abbr[contains(text(),'" + month + "')]")
        )).click();

        // ✅ Select Day
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[not(contains(@class,'neighboringMonth')) and text()='" + day + "']")
        )).click();
    }
    }
    
