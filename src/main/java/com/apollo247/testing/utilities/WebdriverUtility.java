package com.apollo247.testing.utilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverUtility {

	protected WebDriver driver;
	WebDriverWait wait;

	public void initializeDriver(WebDriver driver) {
		this.driver = driver;

	}
	// webdriver utlitiy

	// Manage
	// maximize
	public void configMaximizeBrowser() {
		driver.manage().window().maximize();
	}

	// minimize
	public void configMinimizeBrowser() {
		driver.manage().window().minimize();
	}

	// fullscreen
	public void configFullScreenBrowser() {
		driver.manage().window().fullscreen();
	}

	// get size
	public Dimension fetchBrowserSize() {
		return driver.manage().window().getSize();
	}

	// set size
	public void setBrowserSize(int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	// get position
	public Point fetchBrowserPosition() {
		return driver.manage().window().getPosition();
	}

	// set position
	public void setBrowserPosition(int width, int height) {
		driver.manage().window().setPosition(new Point(width, height));
	}

	// Navigate
	// navigate to
	public void navigateToApplication(String fullURL) {
		driver.navigate().to(fullURL);
	}

	// forward
	public void navigateForward() {
		driver.navigate().forward();
	}

	// back
	public void navigateBack() {
		driver.navigate().back();
	}

	// refresh
	public void RefreshCurrentPage() {
		driver.navigate().refresh();
	}

	// Get
	public void enterURL(String URL) {
		driver.get(URL);
	}

	// get title
	public String fetchApplicationTitle() {
		return driver.getTitle();
	}

	// get url
	public String fetchApplicationURL() {
		return driver.getCurrentUrl();
	}

	// close
	public void closeBrowserTab() {
		driver.close();
	}

	// quit
	public void quitBroswerWindow() {
		driver.quit();
	}

	// timeouts
	public void waitForElements(long timeInSeconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
	}

	// explicit wait for element to be clickable
	public WebElement waitUntilElementIsCLickable(long timeInSeconds, WebElement object) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(object));
	}

	// explicit wait for element to be visible
	public void waitUntilElementIsVisibility(long timeInSeconds, WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// explicit wait for shadow dom is present or not
	public WebElement waituntilPresenceOfElementLocated(long timeInSeconds, By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitUntilInvisibilityOfElementLocated(long timeInSeconds, By element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	public void waitUntilUrlContains(long timeInSeconds, String url) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.urlContains(url));
	}

	public WebElement waitUntilVisibilityOfElementLocated(long timeInSeconds, By element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public void waitUntilTextToBePresentInElement(long timeInSeconds, WebElement element, String text) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until((ExpectedConditions.textToBePresentInElement(element, text)));
	}

	// popups
	// alert accept
	public void handleAcceptAlert() {
		driver.switchTo().alert().accept();
	}

	// alert dismiss
	public void handleDismissAlert() {
		driver.switchTo().alert().dismiss();
	}

	// type message alert
	public void typeMessageAlert(String message) {
		driver.switchTo().alert().sendKeys(message);
	}

	// print javascript message
	public void displayPopupMessage() {
		System.out.println(driver.switchTo().alert().getText());
	}

	// switch to window by title
	public void switchToWindowByTitle(String childApplicationTitle) {
		String parent = driver.getWindowHandle();
		Set<String> tabs = driver.getWindowHandles();
		tabs.remove(parent);
		for (String tab : tabs) {
			driver.switchTo().window(tab);
			String title = fetchApplicationTitle();
			if (title.contains(childApplicationTitle)) {
				break;
			}
		}
	}

	// switch to window by url
	public void switchToWindowByURL(String childApplicationURL) {
		String parent = driver.getWindowHandle();
		Set<String> tabs = driver.getWindowHandles();
		tabs.remove(parent);
		for (String tab : tabs) {
			driver.switchTo().window(tab);
			String title = fetchApplicationURL();
			if (title.contains(childApplicationURL)) {
				break;
			}
		}
	}

	// iframe
	// switching to iframe by index
	public void switchToFrameByIndex(int index) {
		driver.switchTo().frame(index);
	}

	// switching to iframe by idorname
	public void switchToFrameByIdorName(int IdorName) {
		driver.switchTo().frame(IdorName);
	}

	// switching to iframe by webelement
	public void switchToFrameByIndex(WebElement element) {
		driver.switchTo().frame(element);
	}

}