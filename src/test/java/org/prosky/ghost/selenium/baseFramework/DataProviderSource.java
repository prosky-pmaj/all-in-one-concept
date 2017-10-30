package org.prosky.ghost.selenium.baseFramework;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.testng.annotations.DataProvider;

public class DataProviderSource {
	private final static Logger LOG = Logger.getLogger(DataProviderSource.class.getName());

	@DataProvider
	public Object[][] getPostsTestData(Method method) {
		String testName = method.getName();
		LOG.fine("Data providet for TC: " + testName);
		String fileName = "posts-test-data.properties";
		if ("test_01_createPost".equals(testName)) {
			return getTestDataSafely(fileName, 1, "title", "content");
		} else if ("test_02_editPost".equals(testName)) {
			return getTestDataSafely(fileName, 1,
					"title", "content", "title.after.edit", "content.after.edit");
		} else if ("test_03_deletePost".equals(testName)) {
			return getTestDataSafely(fileName, 1, "title.after.edit");
		} else {
			return new Object[][] { {} };
		}
	}

	private Object[][] getTestDataSafely(String fileName, int minExpectedNrOfTests,
			String... args) {
		try {
			PropertiesParser prop = new PropertiesParser(fileName);
			return prop.getTestData(minExpectedNrOfTests, args);
		} catch (Throwable e) {
			/*
			 * This workaround is required because of an issue in TestNG. If an Exception
			 * occurs in @DataProvider related test cases are skipped instead of being
			 * failed. To return empty array makes tests fail.
			 */
			LOG.severe("\n\n**************\nReading test data fail!\nThis is the "
					+ "reason why TCs depended on this data fail as well.\n"
					+ "The most probably reasons why test data reading fail:\n"
					+ " * data file doesn't exist (or is not in java.class.path)\n"
					+ " * nrOfTests < minExpectedNrOfTests\n"
					+ " * wrong number of parameters\n\n"
					+ "See printed StackTrace for more details.\n**************\n");
			e.printStackTrace();
			return new Object[][] { {} };
		}
	}
}
