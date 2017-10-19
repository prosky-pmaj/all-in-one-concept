package org.prosky.ghost.selenium.pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.prosky.ghost.selenium.baseFramework.UserInterfaceMapParser;

public class Page extends UserInterfaceMapParser {
	private final String defaultSiteUrl = "http://localhost:2368/";
	protected final String baseUrl = System.getProperty("site.url", defaultSiteUrl);
	protected WebDriver driver;
	protected WebDriverWait wait;

	public Page(WebDriver driver) throws IOException {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	public void waitUntilFindElement(By by) {
		wait.until(ExpectedConditions.numberOfElementsToBe(by, 1));
	}

	public void waitUntilFindElement(By by, int timeOut) {
		WebDriverWait newWait = new WebDriverWait(driver, timeOut);
		newWait.until(ExpectedConditions.numberOfElementsToBe(by, 1));
	}

	public void waitUntilFindElement(String locatorName) {
		try {
			waitUntilFindElement(getLocator(locatorName));

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void waitUntilFindElement(String locatorName, int timeOut) {
		waitUntilFindElement(getLocator(locatorName), timeOut);
	}

	public WebElement findElement(String locatorName) {
		return driver.findElement(getLocator(locatorName));
	}
}
