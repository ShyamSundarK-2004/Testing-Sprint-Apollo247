package com.apollo247.testing.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HealthtoolPage {
	WebDriver driver;
    WebDriverWait wait;

    public HealthtoolPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    
    @FindBy(css="[title=\"Health Articles For You - View All\"]")
	private WebElement viewall;
    
    	@FindBy(linkText="Health Tools")
    	private WebElement heartHealthToolCard;

    @FindBy(xpath = "//h4[text()='Body Mass Index']/..//span[text()='CALCULATE']")
    private WebElement bmiCalculateBtn;


    @FindBy(xpath = "//span[text()='I am a female']")
    private WebElement femaleOption;

    @FindBy(xpath = "//div[@class='styles_centerContainer__s_u6d']")
    private WebElement navigateBtn;

    @FindBy(xpath = "//input[@type='number']")
    private WebElement heightInput;

    @FindBy(xpath = "//span[@class='styles_icon-ic_arrow_right__7KoAg']")
    private WebElement nextArrowBtn;

    @FindBy(css = "[type='number']")
    private WebElement weightInput;

    @FindBy(xpath = "//span[text()='CALCULATE']")
    private WebElement calculateBtn;

    @FindBy(xpath = "//p[contains(@class,'resultSubTitle')]")
    private WebElement bmiResultText;
    
    @FindBy(xpath="//div[contains(@class,'resultContainer')]//h2")
    private WebElement bmivalue;
	public WebElement getHeartHealthToolCard() {
		return heartHealthToolCard;
	}
	public WebElement getBmiCalculateBtn() {
		return bmiCalculateBtn;
	}

	public WebElement getFemaleOption() {
		return femaleOption;
	}

	public WebElement getNavigateBtn() {
		return navigateBtn;
	}

	public WebElement getHeightInput() {
		return heightInput;
	}

	public WebElement getNextArrowBtn() {
		return nextArrowBtn;
	}

	public WebElement getWeightInput() {
		return weightInput;
	}

	public WebElement getCalculateBtn() {
		return calculateBtn;
	}

	public WebElement getBmiResultText() {
		return bmiResultText;
	}

	public void clickHealthToolCard() {
		wait.until(ExpectedConditions.elementToBeClickable(viewall)).click();
	    heartHealthToolCard.click();
	    String parent = driver.getWindowHandle();

	    for (String win : driver.getWindowHandles()) {
	        if (!win.equals(parent)) {
	            driver.switchTo().window(win);
	            break;
	        }
	    }
	}

	

	public void clickBMICalculate() {
		wait.until(ExpectedConditions.elementToBeClickable(bmiCalculateBtn));

	    System.out.println("Displayed: " + bmiCalculateBtn.isDisplayed());
	    System.out.println("Enabled: " + bmiCalculateBtn.isEnabled());

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", bmiCalculateBtn);
	}

	public void Female() {
		wait.until(ExpectedConditions.elementToBeClickable(femaleOption)).click();
	}

	public void clickNavigate() {
		wait.until(ExpectedConditions.elementToBeClickable(navigateBtn)).click();
	}

	public void Height(String height) {
		   wait.until(ExpectedConditions.visibilityOf(heightInput));
		    heightInput.clear();
		    heightInput.sendKeys(height);
	}

	public void clickNextArrow() {
		wait.until(ExpectedConditions.elementToBeClickable(nextArrowBtn)).click();
	}

	public void Weight(String weight) {
		wait.until(ExpectedConditions.visibilityOf(weightInput));
	    weightInput.clear();
	    weightInput.sendKeys(weight);
	}

	public void clickCalculate() {
		wait.until(ExpectedConditions.elementToBeClickable(calculateBtn)).click();

	    // Wait for result using better locator
	    wait.until(ExpectedConditions.visibilityOf(bmivalue));
	}

	public String getActualCategory() {
	    wait.until(ExpectedConditions.visibilityOf(bmiResultText));

	    String text = bmiResultText.getText(); 
	    System.out.println("Raw Text: " + text);

	    // "Your BMI is Underweight" → "Underweight"
	    return text.replace("Your BMI is", "").trim();
	}



}
