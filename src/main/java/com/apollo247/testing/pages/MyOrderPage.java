package com.apollo247.testing.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.apollo247.testing.utilities.WebdriverUtility;

public class MyOrderPage {

	WebDriver driver;
	WebdriverUtility utilities = new WebdriverUtility();

	public MyOrderPage(WebDriver driver) {
		this.driver = driver;
		utilities.initializeDriver(driver);
	}

	// ====== Locators ======

	// user dropdown
	@FindBy(xpath = "//div[contains(@class,'AphSelect_select')]")
	private WebElement userDropdown;

	// user orders card
	@FindBy(xpath = "//div[contains(@class,'OrderCard_orderCardGroup')]")
	private WebElement orderCard;

	// ====== Getters ======

	public WebElement getUserDropDown() {
		return userDropdown;
	}

	public WebElement getOrderCard() {
		return orderCard;
	}
	// ====== Business Logic ======

	public String getCurrentUrl(String url) {
		utilities.waitUntilUrlContains(15L, url);
		return utilities.fetchApplicationURL();
	}

	public void clickOnUserDropdown() {
		getUserDropDown().click();
	}

	public String clickOnSpecificUser(String userName) {
		WebElement user = driver.findElement(
				By.xpath("//div[contains(text(),'" + userName + "')]//parent::li[contains(@class,'Menu')]"));
		user.click();
		return userName;
	}

	public boolean isSpecificUserOrderDisplayed(String userName) {
		utilities.waitUntilElementIsVisibility(20L, getOrderCard());
		List<WebElement> users = driver.findElements(By.xpath("//span[contains(text(),'Booked for')]//parent::div"));
		if (getOrderCard().isDisplayed()) {
			for (WebElement user : users) {
				String usr = user.getText();
				if (usr.contains(userName)) {
					return true;
				}
			}
		}
		return false;

	}
}
