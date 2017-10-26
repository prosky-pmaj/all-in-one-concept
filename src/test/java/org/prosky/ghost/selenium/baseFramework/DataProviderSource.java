package org.prosky.ghost.selenium.baseFramework;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.testng.annotations.DataProvider;

public class DataProviderSource {
	private final static Logger LOG = Logger.getLogger(DataProviderSource.class.getName());

	public DataProviderSource() {
		LOG.info("DataProviderSource iniciated");
	}

	@DataProvider
	public Object[][] getPostsTestData(Method method) {
		try {
			String testName = method.getName();
			LOG.info(testName);
			PropertiesParser prop = new PropertiesParser("posts-test-data.properties");
			return prop.getTestData("title", "content", 1);
		} catch (Throwable e) {
			/*
			 * This workaround is required because of an issue in TestNG. If an Exception
			 * occurs in @DataProvider related test cases are skipped instead of being
			 * failed. To return empty array makes tests fail instead.
			 */
			LOG.severe("Generating test data fail due to an exception. It is the most "
					+ "probably the reason why TCs depended on this data fail. "
					+ "See printed StackTrace and exaption msg for more info.");
			e.printStackTrace();
			return new Object[][] { {} };
		}
	}

	// @DataProvider(name = "scenarioData")
	// public static Object[][] getScenarioData(Method method) {
	// String testCase = method.getName();
	// if ("scenario1".equals(testCase)) {
	// return new Object[][] { { "Scenario1 data" } };
	// } else if ("scenario2".equals(testCase)) {
	// return new Object[][] { { "Scenario2 data" } };
	// } else {
	// return new Object[][] { { "Common scenario data" } };
	// }
	// }
	//
	// @DataProvider(name = "TestType")
	// public static Object[][] getTestTypeData(ITestContext context) {
	// String testName = context.getName();
	// if ("IntegrationLevel".equals(testName)) {
	// return new Object[][] { { "Integration test data" } };
	// } else if ("AcceptanceLevel".equals(testName)) {
	// return new Object[][] { { "Acceptance test data" } };
	// } else {
	// return new Object[][] { { "Common test data" } };
	// }
	// }
}
