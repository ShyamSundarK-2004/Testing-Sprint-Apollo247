package com.apollo247.testing.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class TakeScreenShotUtility {

	String timeStamp = new SimpleDateFormat("yyyy-MM-dd[hh-mm-ss]").format(new Date());

	// using webdriver to capture whole webpage
	public String takeScreenShot(WebDriver driver, String testname) throws IOException {
		String path = "./Screenshots/" + testname + " " + timeStamp + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm = new File(path);
		FileHandler.copy(temp, perm);
		return perm.getAbsolutePath();
	}

	// using webelement to capture particular element
	public String takeScreenShot(WebElement element, String testname) throws IOException {
		String path = "./Screenshots/" + testname + " " + timeStamp + ".png";
		File temp = element.getScreenshotAs(OutputType.FILE);
		File perm = new File(path);
		FileHandler.copy(temp, perm);
		return perm.getAbsolutePath();
	}

}