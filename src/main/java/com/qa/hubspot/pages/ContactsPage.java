package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class ContactsPage extends BasePage {

	private WebDriver driver;
	ElementUtil elementUtil;

	By contactsPageHeader = By.xpath("//i18n-string[@data-key='genericTypes.capitalized.CONTACT']");
	By createContactPrimary = By.xpath("//span[text()='Create contact']");
	By contactEmail = By.xpath("//input[@name = 'textInput']");
	By contactFirstName = By.xpath("//label[text() = 'First name']");
	By contactLastName = By.xpath("//label[text() = 'Last name']");
	By contactPhoneNumber = By.xpath("//label[text() = 'Phone number']");
	By contactJobTitle = By.xpath("//input[@data-field = 'jobtitle']");
	By createContactSecondary = By.xpath("//button[@data-selenium-test='base-dialog-confirm-btn']");
	By contactsBackLink = By.xpath("(//i18n-string[@data-key='profileHeader.backButton.CONTACT'])[position()=1]");

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getContactsPageTitle() {
		return elementUtil.doGetPageTitleWithTitleIs(10, Constants.CONTACTS_PAGE_TITLE);

	}

	public String getContactsPageHeader() {
		elementUtil.doWaitForElementPresent(contactsPageHeader, 10);
		return elementUtil.doGetText(contactsPageHeader);
	}

	public void createContact(String email, String firstName, String lastName, String jobTitle, String phoneNum) {
		elementUtil.clickElementWhenReady(createContactPrimary, 10);
		
		elementUtil.doSendKeys(contactEmail, email);
		elementUtil.doSendKeys(contactFirstName, firstName);
		elementUtil.doSendKeys(contactLastName, lastName);
		elementUtil.doSendKeys(contactJobTitle, jobTitle);
		elementUtil.doSendKeys(contactPhoneNumber, phoneNum);
		
		elementUtil.doClick(createContactSecondary);
		
		elementUtil.clickElementWhenReady(contactsBackLink, 10);

	}

}
