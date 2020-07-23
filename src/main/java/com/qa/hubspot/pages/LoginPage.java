package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage extends BasePage {

	private WebDriver driver;
	ElementUtil elementUtil;

	// By locators ...... O

	By email = By.id("username");
	By password = By.id("password");
	By loginbtn = By.id("loginBtn");
	By signuplink = By.linkText("Sign up");
	By accountNameLink = (By.xpath("//span[text()='7975611']//parent::td//preceding-sibling::td/div/div/span/span/span/a/span/span/span"));

	// Constructor of the page class
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// Page actions
	public String getLoginPageTitle() {
		//return driver.getTitle();
		return elementUtil.doGetPageTitleWithTitleIs(10, Constants.LOGIN_PAGE_TITLE);
	}

	public boolean isSiginUpPageExist() {
		//return driver.findElement(signuplink).isDisplayed();
		return elementUtil.doIsDisplayed(signuplink);
	}
	
//Login with a correct username and password
	public HomePage doLogin(String username, String pwd) {
		elementUtil.doWaitForElementPresent(email, 10);
		elementUtil.doSendKeys(email, username);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginbtn);
		elementUtil.doClick(accountNameLink);
		return new HomePage(driver);
	}
}
