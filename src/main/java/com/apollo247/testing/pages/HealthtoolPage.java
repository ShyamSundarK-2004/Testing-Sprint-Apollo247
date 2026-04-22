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

    @FindBy(xpath = "//*[contains(text(),'Your BMI is') or " +
                    "contains(text(),'BMI') or " +
                    "contains(text(),'bmi')]")
    WebElement bmiResultText;

    @FindBy(xpath = "//*[contains(text(),'Underweight') or " +
                    "contains(text(),'Normal') or " +
                    "contains(text(),'Overweight') or " +
                    "contains(text(),'Obese')]")
    WebElement bmiCategoryText;

    @FindBy(xpath = "//*[contains(text(),'Underweight')]")
    WebElement underweightLabel;

    @FindBy(xpath = "//*[contains(text(),'Overweight')]")
    WebElement overweightLabel;

    @FindBy(xpath = "//*[contains(text(),'Normal')]")
    WebElement normalLabel;

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

	public WebElement getBmiCategoryText() {
		return bmiCategoryText;
	}

	public WebElement getUnderweightLabel() {
		return underweightLabel;
	}

	public WebElement getOverweightLabel() {
		return overweightLabel;
	}

	public WebElement getNormalLabel() {
		return normalLabel;
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
	    femaleOption.click();
	}

	public void clickNavigate() {
	    navigateBtn.click();
	}

	public void Height(String height) {
	    heightInput.sendKeys(height);
	}

	public void clickNextArrow() {
	    nextArrowBtn.click();
	}

	public void Weight(String weight) {
	    weightInput.sendKeys(weight);
	}

	public void clickCalculate() {
	    calculateBtn.click();
	}

	public boolean isBMIResultDisplayed() {
	    return bmiResultText.isDisplayed();
	}

	public String getBMICategory() {
	    return bmiCategoryText.getText();
	}
	


}
