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

    public Object[][] getTestData(String firstParameterName, String secondParameterName,
	    int minExpectedNrOfTests) throws IOException {

	int nrOfTests = propertyFile.size() / 2;
	int amountOfFirstParameters = 0;
	int amountOfSecondParameters = 0;
	String firstParameterRegExp = firstParameterName + "[.][0-9]+";
	String secondParameterRegExp = secondParameterName + "[.][0-9]+";

	if (minExpectedNrOfTests != 0) {
	    if (nrOfTests < minExpectedNrOfTests) {
		throw new IOException("TestData file problem: to little TCs to be prepare");
	    }
	}

	Object[][] testData = new Object[nrOfTests][2];
	Enumeration<?> e = propertyFile.propertyNames();
	while (e.hasMoreElements()) {
	    String key = (String) e.nextElement();
	    if (key.matches(firstParameterRegExp)) {
		String value = propertyFile.getProperty(key);
		testData[Integer.parseInt(key.replaceAll(".*[.]", ""))][0] = value;
		LOG.fine("[First  Parameter] Key : " + key + ", Value : " + value);
		amountOfFirstParameters++;
	    } else if (key.matches(secondParameterRegExp)) {
		String value = propertyFile.getProperty(key);
		testData[Integer.parseInt(key.replaceAll(".*[.]", ""))][1] = value;
		LOG.fine("[Second Parameter] Key : " + key + ", Value : " + value);
		amountOfSecondParameters++;
	    } else {
		throw new IOException("TestData file problem: parameter patern doesn't match");
	    }
	}

	if (amountOfFirstParameters != amountOfSecondParameters |
		nrOfTests != amountOfFirstParameters) {
	    throw new IOException("TestData file problem: wrong number of parameters");
	}
	System.out.println("Test data:");
	for (int i = 0; i < nrOfTests; i++) {
	    LOG.info("[" + i + "][0]: " + testData[i][0]);
	    LOG.info("[" + i + "][1]: " + testData[i][1]);
	}
	return testData;
    }
}
