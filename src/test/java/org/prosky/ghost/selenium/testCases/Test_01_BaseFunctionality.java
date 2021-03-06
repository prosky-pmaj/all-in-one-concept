package org.prosky.ghost.selenium.testCases;

import java.io.IOException;
import java.util.logging.Logger;

import org.prosky.ghost.selenium.TestEnviroment;
import org.prosky.ghost.selenium.pageObjects.AdminPanelPage;
import org.prosky.ghost.selenium.pageObjects.BlogPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_01_BaseFunctionality extends TestEnviroment {
	private static final Logger LOG = Logger.getLogger(Test_01_BaseFunctionality.class.getName());

	@Test(groups = "initialCheck")
	public void test_01_openBlogPage() throws IOException {
		LOG.info("Start");
		BlogPage blog = new BlogPage(getDriver());
		blog.goTo();
		assertEquals(getDriver().getTitle(), "Blog for Testing");
		LOG.info("enD");
	}

	@Test(groups = "initialCheck")
	public void test_02_openAdminPanelPage() throws IOException {
		LOG.info("Start");
		AdminPanelPage adminPanel = new AdminPanelPage(getDriver());
		adminPanel.goTo();
		assertTrue(adminPanel.isLogInRequired());
		LOG.info("enD");
	}

	@Test(groups = "initialCheck")
	public void test_03_logInToAdninPanel() throws IOException {
		LOG.info("Start");
		AdminPanelPage adminPanel = new AdminPanelPage(getDriver());
		adminPanel.goTo();
		adminPanel.logInAsAdmin();
		Assert.assertFalse(adminPanel.isLogInRequired());
		adminPanel.logOut();
		LOG.info("enD");
	}
}
