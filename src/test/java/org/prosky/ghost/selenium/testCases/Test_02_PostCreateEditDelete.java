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
		assertTrue(blog.isPostDataCorrect(postTitle, postContent));

		LOG.info("enD");
	}

	@Test(dependsOnMethods = { "test_01_createPost" }, ignoreMissingDependencies = true,
			dataProvider = "getPostsTestData", dataProviderClass = DataProviderSource.class)
	public void test_02_editPost(String postTitle, String postContent,
			String postTitleAfterEdit, String postContentAfterEdit) throws Exception {
		LOG.info("Start");
		BlogPage blog = new BlogPage(getDriver());
		AdminPanelPage adminPanel = new AdminPanelPage(getDriver());
		adminPanel.goTo();
		adminPanel.logInAsAdminIfRequired();

		adminPanel.clickContent();
		adminPanel.findPost(postTitle).click();
		adminPanel.clickEditButton();

		adminPanel.goTo();
		adminPanel.clickContent();
		adminPanel.findPost(postTitle).click();
		adminPanel.clickEditButton();
		adminPanel.reWritePost(postTitleAfterEdit, postContentAfterEdit);
		adminPanel.updatePost();

		blog.goTo();
		blog.goToPost(postTitleAfterEdit);
		assertTrue(blog.isPostDataCorrect(postTitleAfterEdit, postContentAfterEdit));
		LOG.info("enD");
	}

	@Test(dependsOnMethods = { "test_02_editPost" }, ignoreMissingDependencies = true,
			dataProvider = "getPostsTestData", dataProviderClass = DataProviderSource.class)
	public void test_03_deletePost(String postTitleAfterEdit) throws Exception {
		LOG.info("Start");
		BlogPage blog = new BlogPage(getDriver());
		AdminPanelPage adminPanel = new AdminPanelPage(getDriver());
		adminPanel.goTo();
		adminPanel.logInAsAdminIfRequired();

		adminPanel.clickContent();
		adminPanel.findPost(postTitleAfterEdit).click();
		adminPanel.clickEditButton();
		adminPanel.deletePost();

		blog.goTo();
		assertFalse(blog.isPostPresent(postTitleAfterEdit));
		LOG.info("enD");
	}
	// TODO: Multiple editing one post by adding text and by rewriting.
	// String postTitleTextToAdd = " and later on edited a bit";
	// String postContentTextToAdd = "\n Editing posts is possible as well!";
	// String postTitleWithAddedText = postTitle + postTitleTextToAdd;
	// String postContentWithAddedText = postContent + postContentTextToAdd;
	// adminPanel.editPost(postTitleTextToAdd, postContentTextToAdd);
	// adminPanel.updatePost();
	// blog.goTo();
	// blog.goToPost(postTitle);
	// assertTrue(blog.isPostDataCorrect(postTitleWithAddedText,
	// postContentWithAddedText));
	// TODO: Create draft post and remove draft post
	// TODO: Posts with pictures
}
