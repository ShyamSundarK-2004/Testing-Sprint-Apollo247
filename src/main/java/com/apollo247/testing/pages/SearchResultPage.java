package com.apollo247.testing.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.apollo247.testing.utilities.WebdriverUtility;

public class SearchResultPage {

	WebDriver driver;
	WebdriverUtility utilities = new WebdriverUtility();

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		utilities.initializeDriver(driver);
	}

	// ====== Locators ======

	// search result message
	@FindBy(xpath = "//li[contains(@class , 'SearchResult_noResultsFound')]")
	private WebElement resultNotFoundMsg;

	// search result section
	@FindBy(xpath = "//ul[contains(@class,'SearchResult_testCardContainer')]")
	private WebElement searchResultSection;

	// ====== Getters ======

	public WebElement getResultNotFoundMsg() {
		return resultNotFoundMsg;
	}

	public WebElement getSearchResultSection() {
		return searchResultSection;
	}
	// ====== Business Logics ======

	public boolean isErrorMessageDisplayed() {
		try {
			utilities.waitUntilElementIsVisibility(45L, getResultNotFoundMsg());
			return getResultNotFoundMsg().isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isResultDisplayed(String name) {
		utilities.waitUntilElementIsVisibility(40L, getSearchResultSection());
		List<WebElement> testNames = driver.findElements(By.xpath("//p[contains(@class,'RX')]"));
		for (WebElement element : testNames) {
			String testName = element.getText();
			if (testName.contains(name)) {
				return true;
			}
		}
		return false;
	}

	public void clickOnLabTest(String testName) {
		utilities.waituntilPresenceOfElementLocated(35L,
				By.xpath("//p[contains(normalize-space(),'" + testName + "')]"));
		WebElement labtestCard = driver.findElement(By.xpath("//p[contains(normalize-space(),'" + testName + "')]"));
		labtestCard.click();
	}

}
