package org.prosky.ghost.selenium.baseFramework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.By;

public class PropertiesParser {
    private static final Logger LOG = Logger.getLogger(PropertiesParser.class.getName());
    private InputStream input;
    private Properties propertyFile = new Properties();

    public PropertiesParser(String fileName) throws IOException {
	try {
	    LOG.info(getClass().getClassLoader().getResource(fileName).toString());
	    input = getClass().getClassLoader().getResourceAsStream(fileName);
	    propertyFile.load(input);
	} finally {
	    if (input != null) {
		input.close();
	    }
	}

    }

    public By getLocator(String locatorName) throws ArrayIndexOutOfBoundsException {
	String locatorProperty;
	String locatorType;
	String locatorValue;
	try {
	    locatorProperty = propertyFile.getProperty(locatorName);
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

    public Object[][] getTestData(String type1, String type2, int minExpectefNrOfTests) {
	int nrOfTests = propertyFile.size() / 2;

	Enumeration<?> e = propertyFile.propertyNames();
	while (e.hasMoreElements()) {
	    String key = (String) e.nextElement();
	    String value = propertyFile.getProperty(key);
	    System.out.println("Key : " + key + ", Value : " + value);
	}

	Object[][] testData = new Object[][] {
		{ "admin@test.org", "admin123" },
		{ "admin@prosky.org", "admin123" } };
	System.out.println(testData[0][0]);
	return testData;
    }
}
