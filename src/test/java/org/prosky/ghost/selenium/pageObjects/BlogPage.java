package org.prosky.ghost.selenium.pageObjects;

import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BlogPage extends Page {
	private final static Logger LOG = Logger.getLogger(BlogPage.class.getName());

	public BlogPage(WebDriver driver) throws IOException {
		super(driver);
	}

	public void goTo() {
		driver.get(this.baseUrl);
		waitUntilFindElement("blog.homePage");
	}

	public void goToPost(String title) throws InterruptedException {
		LOG.fine("title = " + title);
		// TODO: This scrolling could be done better
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 250)");

		// TODO:Prepere this func to handle title with ' and "
		// TODO: Maybe better to get lists of all posts and then get titles and compare
		driver.findElement(By.xpath("//a[text()='" + title + "']")).click();
		waitUntilFindElement(By.xpath("//h1[text()='" + title + "']"));
	}

	public boolean isPostPresent(String title) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 250)");
		try {
			driver.findElement(By.xpath("//a[text()=" + title + "']")).click();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
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
				getPostContent()
						.replaceAll("\\s+", " "),
				expectedPostContent
						.replaceAll("\\!\\[[^(]*\\([^)]*\\)", "")
						.replaceAll("<[^/>]*\\/>", "")
						.replaceAll("(---)", "")
						.replaceAll("[1-9]\\.", "")
						.replaceAll("\\[([^\\[]*)\\]\\([^\\)]*\\)", "$1")
						.replaceAll("\\!\\[([^\\[]*)\\]", "")
						.replaceAll("\\n>", "")
						.replaceAll("[`#*]", "")
						.replaceAll("\\s+", " "));
		return true;
	}
}
