package com.apollo247.testing.pages;

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

	// search result section
	@FindBy(xpath = "//div[text() = 'Search Result']")
	private WebElement searchResultSection;

	// ====== Getters ======

	public WebElement getSearchResultSection() {
		return searchResultSection;
	}
	// ====== Business Logics ======

	public void clickOnAddToCartBtn(String testName) {
		utilities.waitUntilElementIsVisibility(40L, getSearchResultSection());
		utilities.waituntilPresenceOfElementLocated(25L, By.xpath("//p[contains(text(),'" + testName + "')]"));
		WebElement addToCartBtn = driver.findElement(By.xpath("//p[contains(text(),'CBC Test')]//following::button"));
		addToCartBtn.click();
	}

}
