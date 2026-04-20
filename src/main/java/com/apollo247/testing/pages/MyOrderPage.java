package com.apollo247.testing.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyOrderPage {

	// ====== Locators ======

	// user dropdown
	@FindBy(xpath = "//div[contains(@class,'AphSelect_select')]")
	private WebElement userDropdown;

	// ====== Getters ======

	public WebElement getUserDropDown() {
		return userDropdown;
	}

	//

}
