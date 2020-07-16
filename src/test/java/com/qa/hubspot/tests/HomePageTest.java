package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.util.Constants;

public class HomePageTest extends BaseTest {

	HomePage homePage;

	@BeforeClass
	public void homePageSetUp() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void verifyHomePageTitleTest() {
		String title = homePage.getHomePageTitle();
		System.out.println("Home page title is " + title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}

	@Test
	public void verifyHomePageHeaderTest() {
		String header = homePage.getHomePageHeader();
		System.out.println("Home page header is " + header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}

	@Test
	public void verifygetAccountOwnerTest() {
		String displayedOwner = homePage.getAccountOwner();
		System.out.println("Home page owner is: " + displayedOwner);
		Assert.assertEquals(displayedOwner, "Nicodemus Ongoto");
	}

}
