package org.prosky.ghost.selenium.testCases;

import java.io.IOException;
import java.util.logging.Logger;

import org.prosky.ghost.selenium.TestEnviroment;
import org.prosky.ghost.selenium.baseFramework.PropertiesParser;
import org.prosky.ghost.selenium.pageObjects.AdminPanelPage;
import org.prosky.ghost.selenium.pageObjects.BlogPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test_02_PostCreateEditDelete extends TestEnviroment {
	private final static Logger LOG = Logger
			.getLogger(Test_02_PostCreateEditDelete.class.getName());

	@DataProvider(name = "DataForPostCreation")
	public static Object[][] dataForPostCreation() throws IOException {
		PropertiesParser prop = new PropertiesParser("test-data-for-posts-creation.properties");
		return prop.getTestData("title", "content", 1);
	}

	/**
	 * The @DataProvider self-test is required because of an issue in TestNG cause
	 * that if an Exception occurs in @DataProvider related test cases are skipped
	 * instead of being failed.
	 */
	@Test
	public void test_00_dataProviderSelfTest() throws IOException {
		Test_02_PostCreateEditDelete.dataForPostCreation();
	}

	@Test(dependsOnGroups = { "initialCheck" }, ignoreMissingDependencies = true,
			dataProvider = "DataForPostCreation")
	public void test_01_createPost(String postTitle, String postContent) throws Exception {
		LOG.info("Start");
		AdminPanelPage adminPanel = new AdminPanelPage(getDriver());
		adminPanel.goTo();
		adminPanel.logInAsAdminIfRequired();

		adminPanel.clickNewPost();
		adminPanel.writePost(postTitle, postContent);
		adminPanel.publishPost();

		BlogPage blog = new BlogPage(getDriver());
		blog.goTo();
		blog.goToPost(postTitle);
		assertEquals(blog.getPageTitle(), postTitle);
		assertEquals(blog.getPostTitle(), postTitle);
		assertEquals(
				blog.getPostContent().replaceAll("\\s+", " "),
				postContent.replaceAll("\\s+", " "));
		LOG.info("enD");
	}

	// @Test(dependsOnMethods = { "test_01_createPost" })
	// public void test_02_editPost() throws Exception {
	// LOG.info("Start");
	// AdminPanelPage adminPanel = new AdminPanelPage(getDriver());
	// adminPanel.goTo();
	// adminPanel.logInAsAdminIfRequired();
	//
	// adminPanel.clickContent();
	// adminPanel.findPost(postTitle).click();
	// adminPanel.clickEditButton();
	//
	// String postTitleTextToAdd = " and later on edited a bit";
	// String postContentTextToAdd = "\n Editing posts is possible as well!";
	// postTitle = postTitle + postTitleTextToAdd;
	// postContent = postContent + postContentTextToAdd;
	// adminPanel.editPost(postTitleTextToAdd, postContentTextToAdd);
	// adminPanel.updatePost();
	//
	// BlogPage blog = new BlogPage(getDriver());
	// blog.goTo();
	// blog.goToPost(postTitle);
	// assertEquals(getDriver().getTitle(), postTitle);
	// LOG.info("enD");
	// }

	@Test(dependsOnMethods = { "test_02_editPost" }, ignoreMissingDependencies = true,
			invocationCount = 1, dataProvider = "DataForPostCreation")
	public void test_03_deletePost(String postTitle, String postContent) throws Exception {
		LOG.info("Start");
		AdminPanelPage adminPanel = new AdminPanelPage(getDriver());
		adminPanel.goTo();
		adminPanel.logInAsAdminIfRequired();

		adminPanel.clickContent();
		adminPanel.findPost(postTitle).click();
		adminPanel.clickEditButton();
		adminPanel.deletePost();
		LOG.info("enD");
	}
}
