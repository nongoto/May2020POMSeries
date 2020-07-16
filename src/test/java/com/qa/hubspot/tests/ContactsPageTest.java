package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest extends BaseTest{
	
	ContactsPage contactsPage;
	HomePage homePage;
	
	
	@BeforeClass
	public void contactsPageSetUp() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.goToContactPage();
	}
	
	@Test(priority = 1)
	public void verifyGetContactsPageTitleTest() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println("Contacts page title is :" + title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void verifyGetContactsPageHeaderTest() {
		String header = contactsPage.getContactsPageHeader();
		System.out.println("Contacts page header is: " + header);
		Assert.assertEquals(header, Constants.CONTACTS_PAGE_HEADER);
	}
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	@Test(priority = 3, dataProvider = "getContactsTestData")
	public void verifyCreateContact(String emailId, String firstName, String lastName, String jobTitle, String phoneNumber) {
		contactsPage.createContact(emailId, firstName, lastName, jobTitle, phoneNumber);				
	}
			
	

}
