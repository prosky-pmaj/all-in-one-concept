package org.prosky.ghost.selenium.baseFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.log4testng.Logger;

public class UserInterfaceMapParser {
	private static final Logger log = Logger.getLogger(UserInterfaceMapParser.class);
	private FileInputStream input;
	private Properties propertyFile = new Properties();

	public UserInterfaceMapParser(String fileName) throws IOException {
		try {
			Path path = Paths.get(fileName);
			log.info("Reading config from file: " + path.toAbsolutePath().toString());
			input = new FileInputStream(path.toAbsolutePath().toString());
			propertyFile.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
	}

	public UserInterfaceMapParser() throws IOException {
		try {
			Path path = Paths.get("user-interface-map.properties");
			log.info("Reading config from file: " + path.toAbsolutePath().toString());
			input = new FileInputStream(path.toAbsolutePath().toString());
			propertyFile.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
	}

	public By getLocator(String locatorName) throws ArrayIndexOutOfBoundsException {
		String locatorProperty;
		String locatorType;
		String locatorValue;
		try {
			locatorProperty = propertyFile.getProperty(locatorName);
			// log.info("Locator " + locatorName + " = " + locatorProperty.toString());
			locatorType = locatorProperty.split(":")[0];
			locatorValue = locatorProperty.split(":")[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			throw e;
		}

		By locator = null;
		switch (locatorType) {
		case "ById":
			locator = By.id(locatorValue);
			break;
		case "ByName":
			locator = By.name(locatorValue);
			break;
		case "ByClassName":
			locator = By.className(locatorValue);
			break;
		case "ByCssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "ByLinkText":
			locator = By.linkText(locatorValue);
			break;
		case "ByPartialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "ByTagName":
			locator = By.tagName(locatorValue);
			break;
		case "ByXPath":
			locator = By.xpath(locatorValue);
			break;
		}
		return locator;
	}
}