package org.prosky.ghost.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.By.ByXPath;

public class AdminPanelLocators {
	protected By adminPanelLoginPage = new ById("login");
	protected By adminPanelMainPage = new ByClassName("gh-nav-main-editor");
	protected By adminPanelLoginPage_Or_MainPage =
			new ByXPath("//form[@id='login'] | //span[text()='Content']");

	protected By loginEmailInput = new ByName("identification");
	protected By loginPasswordInput = new ByName("password");
	protected By loginSinginButton = new ByXPath("//button[text()='Sign in']");

	public class Menu {
		protected By newPost = new ByClassName("gh-nav-main-editor");
		protected By content = new ByClassName("gh-nav-main-content");
	}

	protected Menu menu = new Menu();

}
