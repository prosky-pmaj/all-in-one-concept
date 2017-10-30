package org.prosky.ghost.selenium.baseFramework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;

public class PropertiesParser {
	private static final Logger LOG = Logger.getLogger(PropertiesParser.class.getName());
	private Properties propertyFile = new Properties();

	public PropertiesParser(String fileName) throws IOException {
		InputStream input = null;
		try {
			LOG.info(PropertiesParser.class.getClassLoader().getResource(fileName).toString());
			input = PropertiesParser.class.getClassLoader().getResourceAsStream(fileName);
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

	/**
	 * Read data from property file. Test data format in property file <arg>.[0-9]+
	 * (e.g. title.1 or post.title.afterEdit.12)
	 * 
	 * @param minExpectedNrOfTests
	 *            the minimum number of test runs that data should be provided for
	 *            (this ensure that if data file gets corrupted no TCs will miss)
	 * @param args
	 *            any number of parameters <arg> refers to data format <arg>.[0-9]+
	 * @return testData[][]
	 * @throws IOException
	 */
	public Object[][] getTestData(int minExpectedNrOfTests, String... args) throws IOException {

		int nrOfTests = getNumberOfTestsThatTestDataShouldBeProvidedFor(args);
		if (minExpectedNrOfTests != 0) {
			if (nrOfTests < minExpectedNrOfTests) {
				throw new IOException("TestData file problem: to little TCs to be prepare");
			}
		}

		Object[][] testData = new Object[nrOfTests][args.length];
		Enumeration<?> e = propertyFile.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			int column = 0;
			for (String arg : args) {
				if (key.matches(arg + "[.][0-9]+")) {
					int row = Integer.parseInt(key.replaceAll(".*[.]", ""));
					String value = propertyFile.getProperty(key);
					testData[row][column] = value;
					LOG.fine("Key : " + key + ", Value : " + value.replaceAll("[\n].*", "..."));
					break;
				}
				column++;
			}
		}
		return testData;
	}

	private int getNumberOfTestsThatTestDataShouldBeProvidedFor(String... args) throws IOException {
		int[] nrOfParameter = new int[args.length];
		int i = 0;
		for (String arg : args) {
			nrOfParameter[i] = 0;
			Enumeration<?> e = propertyFile.propertyNames();
			while (e.hasMoreElements()) {
				if (((String) e.nextElement()).matches(arg + "[.][0-9]+")) {
					nrOfParameter[i]++;
				}
			}
			if (i != 0) {
				Assert.assertEquals(nrOfParameter[i], nrOfParameter[i - 1],
						"TestData file problem: wrong number of parameters");
			}
			i++;
		}
		return nrOfParameter[0];
	}
}
