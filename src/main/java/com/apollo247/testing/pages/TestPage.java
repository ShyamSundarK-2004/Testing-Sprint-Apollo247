package com.apollo247.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.apollo247.testing.utilities.JavaScriptUtilities;
import com.apollo247.testing.utilities.WebdriverUtility;

public class TestPage {

	WebDriver driver;
	WebdriverUtility utilities = new WebdriverUtility();
	JavaScriptUtilities jsUtil;

	public TestPage(WebDriver driver) {
		this.driver = driver;
		utilities.initializeDriver(driver);
		jsUtil = new JavaScriptUtilities(driver);
	}

	// ====== Locators ======

	// click on add to cart button in test page
	@FindBy(xpath = "//button[contains(@class, 'DetailsPageItemCard_addToCartCTA')]")
	private WebElement addToCartBtn;

	// click on proceed to cart button in test page
	@FindBy(xpath = "//button[contains(@class, 'DetailsPageItemCard_addToCartCTA')]")
	private WebElement proceedToCartBtn;

	// cart button
	@FindBy(css = "[alt='cart-icon']")
	private WebElement cartBtn;

	// test name from cart
	@FindBy(xpath = "//p[contains(@class,'GoToCartBannerNew_itemName')]")
	private WebElement cartTestName;

	// test price
	@FindBy(xpath = "//div[contains(@class,'DetailsPageItemCard_priceDetails')]")
	private WebElement testPrice;

	// need advice floating banner close
	@FindBy(css = "[alt='close']")
	private WebElement needAdviceBanner;

	// ====== Getters ======

	public WebElement getAddToCartBtn() {
		return addToCartBtn;
	}

	public WebElement getProceedToCartBtn() {
		return proceedToCartBtn;
	}

	public WebElement getCartbtn() {
		return cartBtn;
	}

	public WebElement getCartTestName() {
		return cartTestName;
	}

	public WebElement getTestPrice() {
		return testPrice;
	}

	public WebElement getNeedAdviceBanner() {
		return needAdviceBanner;
	}

	// ====== Business Logics ======

	public void clickOnAddToCart() {
		jsUtil.jsScrollIntoView(getTestPrice());
		getNeedAdviceBanner().click();
		getAddToCartBtn().click();
	}

	public void clickOnProceedToCart() {
		getProceedToCartBtn().click();
	}

	public void clickOnCartBtn() {
		getCartbtn().click();
	}

	public String cartTestName() {
		return getCartTestName().getText();
	}

}
