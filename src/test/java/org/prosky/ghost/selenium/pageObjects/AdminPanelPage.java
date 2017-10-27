package org.prosky.ghost.selenium.pageObjects;

import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPanelPage extends Page {
	private final static Logger LOG = Logger.getLogger(AdminPanelPage.class.getName());

	public AdminPanelPage(WebDriver driver) throws IOException {
		super(driver);
	}

	public void goTo() {
		LOG.fine("goTo");
		driver.get(baseUrl + "ghost/");
		waitUntilFindElement("adminPanel.loginPage_or_mainPage");
	}

	public boolean isLogInRequired() {
		LOG.fine("isLogInRequired");
		waitUntilFindElement("adminPanel.loginPage_or_mainPage");
		try {
			findElement("adminPanel.loginPage");
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public void logIn(String email, String pass) {
		LOG.fine("logIn");
		findElement("adminPanel.loginPage.emailInput").clear();
		findElement("adminPanel.loginPage.emailInput").sendKeys(email);
		findElement("adminPanel.loginPage.passwordInput").clear();
		findElement("adminPanel.loginPage.passwordInput").sendKeys(pass);
		findElement("adminPanel.loginPage.singinButton").click();
		waitUntilFindElement("adminPanel.mainPage");
	}

	public void logInAsAdmin() throws IOException {
		LOG.fine("logInAsAdmin");
		logIn(System.getProperty("users.admin.email"), System.getProperty("users.admin.password"));
	}

	public void logInAsAdminIfRequired() throws IOException {
		LOG.fine("logInAsAdminIfRequired");
		if (isLogInRequired()) {
			logInAsAdmin();
		}
	}

	public void clickNewPost() {
		findElement("adminPanel.menu.newPost").click();
		waitUntilFindElement(By.id("entry-title"));
	}

	public void clickContent() {
		findElement("adminPanel.menu.content").click();
		waitUntilFindElement(By.xpath("//span[text()='Content']"));
	}

	public void writePost(String title, String content) {
		findElement("adminPanel.postPage.entryTitle").sendKeys(title);
		findElement("adminPanel.postPage.entryContent").sendKeys(content);
	}

	public void reWritePost(String title, String content) {
		findElement("adminPanel.postPage.entryTitle").clear();
		findElement("adminPanel.postPage.entryTitle").sendKeys(title);
		findElement("adminPanel.postPage.entryContent").clear();
		findElement("adminPanel.postPage.entryContent").sendKeys(content);
	}

	public void editPost(String title, String content) {
		writePost(title, content);
	}

	public void publishPost() throws InterruptedException {
		waitUntilFindElement("adminPanel.postPage.saveDraftButton");
		findElement("adminPanel.postPage.arrowButton").click();
		findElement("adminPanel.postPage.dropDown.publishItem").click();
		findElement("adminPanel.postPage.publishNowButton").click();
		waitUntilFindElement("adminPanel.postPage.updatePostButton");
	}

	public void updatePost() throws InterruptedException {
		waitUntilFindElement("adminPanel.postPage.updatePostButton");
		findElement("adminPanel.postPage.updatePostButton").click();
		waitUntilFindElement("adminPanel.postPage.updatePostButton");
	}

	public void deletePost() throws InterruptedException {
		waitUntilFindElement("adminPanel.postPage.updatePostButton");
		findElement("adminPanel.postPage.arrowButton").click();
		findElement("adminPanel.postPage.dropDown.deleteItem").click();
		waitUntilFindElement("adminPanel.postPage.deletePopUp.deleteButton");
		findElement("adminPanel.postPage.deletePopUp.deleteButton").click();
		waitUntilFindElement(By.xpath("//span[text()='Content']"));
	}

	public WebElement findPost(String title) {
		return driver.findElement(By.xpath("//h3[text()='" + title + "']"));
	}

	public void clickEditButton() {
		findElement("adminPanel.contentPage.editButton").click();
		waitUntilFindElement("adminPanel.postPage.entryTitle");
	}

	public void logOut() {
		findElement("adminPanel.menu.userMenu.arrowButton").click();
		findElement("adminPanel.menu.userMenu.dropDown.singOutItem").click();
		waitUntilFindElement("adminPanel.loginPage");
	}

}
