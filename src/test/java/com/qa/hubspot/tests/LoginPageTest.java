package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.util.Constants;

public class LoginPageTest extends BaseTest{
	
	@Test(priority = 1)
	public void verifySignUpLinkTest() {
		Assert.assertEquals(loginPage.isSiginUpPageExist(), true);
	}

	@Test(priority = 2)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login page title is: " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 3)
	public void verifyLoginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	
}
