package org.prosky.ghost.selenium.testCases;

import java.util.logging.Logger;

import org.prosky.ghost.selenium.TestEnviroment;
import org.prosky.ghost.selenium.pageObjects.AdminPanelPage;
import org.prosky.ghost.selenium.pageObjects.BlogPage;
import org.testng.annotations.Test;

public class Test_02_PostCreateEditDelete extends TestEnviroment {
	private final static Logger LOG =
			Logger.getLogger(Test_02_PostCreateEditDelete.class.getName());
	public String postTitle = "The title of automaticly created post";
	public String postContent =
			"Say big thanks to Selenium and to Java! Because of them this is possible!";

	@Test(dependsOnGroups = { "initialCheck" }, ignoreMissingDependencies = true)
	public void test_01_createPost() throws Exception {
		// TODO: Add method for login entering and exiting
		LOG.info("Start");
		AdminPanelPage adminPanel = new AdminPanelPage(getDriver());
		adminPanel.goTo();
		adminPanel.logInAsAdminIfRequired();

		adminPanel.clickNewPost();
		adminPanel.writePost(postTitle, postContent);
		adminPanel.publishPost();

		// TODO: Add better check for post correctness
		BlogPage blog = new BlogPage(getDriver());
		blog.goTo();
		blog.goToPost(postTitle);
		assertEquals(getDriver().getTitle(), postTitle);
		LOG.info("enD");
	}

	@Test(dependsOnMethods = { "test_01_createPost" })
	public void test_02_editPost() throws Exception {
		LOG.info("Start");
		AdminPanelPage adminPanel = new AdminPanelPage(getDriver());
		adminPanel.goTo();
		adminPanel.logInAsAdminIfRequired();

		adminPanel.clickContent();
		adminPanel.findPost(postTitle).click();
		adminPanel.clickEditButton();

		String postTitleTextToAdd = " and later on edited a bit";
		String postContentTextToAdd = "\n Editing posts is possible as well!";
		postTitle = postTitle + postTitleTextToAdd;
		postContent = postContent + postContentTextToAdd;
		adminPanel.editPost(postTitleTextToAdd, postContentTextToAdd);
		adminPanel.updatePost();

		BlogPage blog = new BlogPage(getDriver());
		blog.goTo();
		blog.goToPost(postTitle);
		assertEquals(getDriver().getTitle(), postTitle);
		LOG.info("enD");
	}

	@Test(
			dependsOnMethods = { "test_02_editPost" },
			ignoreMissingDependencies = true,
			invocationCount = 1)
	public void test_03_deletePost() throws Exception {
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
