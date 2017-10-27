package org.prosky.ghost.selenium.pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BlogPage extends Page {

	public BlogPage(WebDriver driver) throws IOException {
		super(driver);
	}

	public void goTo() {
		driver.get(this.baseUrl);
		waitUntilFindElement("blog.homePage");
	}

	public void goToPost(String title) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 250)");

		driver.findElement(By.xpath("//a[text()='" + title + "']")).click();
		waitUntilFindElement(By.xpath("//h1[text()='" + title + "']"));
	}

	public String getPostTitle() {
		return findElement("blog.postPage.title").getText();
	}

	public String getPostContent() {
		return findElement("blog.postPage.content").getText();
	}

	public boolean isPostDataCorrect(String expectedPostTitle, String expectedPostContent) {
		Assert.assertEquals(getPageTitle(), expectedPostTitle);
		Assert.assertEquals(getPostTitle(), expectedPostTitle);
		Assert.assertEquals(
				getPostContent().replaceAll("\\s+", " "),
				expectedPostContent.replaceAll("\\s+", " "));
		return true;
	}
}
