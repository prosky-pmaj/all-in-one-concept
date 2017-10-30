package org.prosky.ghost.selenium;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.prosky.ghost.selenium.baseFramework.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class TestEnviroment extends Assert {
	private final static Logger LOG = Logger.getLogger(TestEnviroment.class.getName());
	private static List<DriverFactory> webDriverThreadPool = Collections
			.synchronizedList(new ArrayList<DriverFactory>());
	private static ThreadLocal<DriverFactory> driverFactory;

	protected static WebDriver getDriver() throws MalformedURLException {
		return driverFactory.get().getDriver();
	}

	protected static boolean isAlertPresent() throws MalformedURLException {
		try {
			getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	@BeforeSuite(alwaysRun = true)
	public static void instantiateDriverObject() {
		driverFactory = ThreadLocal.withInitial(() -> {
			DriverFactory driverFactory = new DriverFactory();
			webDriverThreadPool.add(driverFactory);
			return driverFactory;
		});
	}

	@AfterMethod(alwaysRun = true)
	public static void clearCookies() throws MalformedURLException {
		getDriver().manage().deleteAllCookies();
	}

	@AfterMethod(alwaysRun = true)
	public static void dismisAlertIfPresent() throws MalformedURLException {
		if (isAlertPresent()) {
			getDriver().switchTo().alert().dismiss();
			LOG.info("Alert was detected and dismissed");
		}
	}

	@AfterTest(alwaysRun = true)
	public static void deleteRemaningPosts() throws MalformedURLException {
		// TODO: Implement removing remaining posts created by tests
	}

	@AfterSuite(alwaysRun = true)
	public static void closeDriverObjects() {
		for (DriverFactory driverFactory : webDriverThreadPool) {
			driverFactory.quitDriver();
		}
	}
}
