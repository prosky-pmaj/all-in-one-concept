package org.prosky.ghost.selenium.testCases;

import java.util.logging.Logger;

import org.prosky.ghost.selenium.TestEnviroment;
import org.prosky.ghost.selenium.baseFramework.DataProviderSource;
import org.prosky.ghost.selenium.pageObjects.AdminPanelPage;
import org.prosky.ghost.selenium.pageObjects.BlogPage;
import org.testng.annotations.Test;

public class Test_02_PostCreateEditDelete extends TestEnviroment {
	private final static Logger LOG = Logger
			.getLogger(Test_02_PostCreateEditDelete.class.getName());

	@Test(dependsOnGroups = { "initialCheck" }, ignoreMissingDependencies = true,
			dataProvider = "getPostsTestData", dataProviderClass = DataProviderSource.class)
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

	@Test(dependsOnMethods = { "test_02_editPost" },
			ignoreMissingDependencies = true,
			dataProvider = "getPostsTestData", dataProviderClass = DataProviderSource.class)
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
