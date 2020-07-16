package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {
	private WebDriver driver;
	ElementUtil elementUtil;

	By header = By.cssSelector("h1.private-header__heading.private-header__heading--solo");
	By accountName = By.cssSelector("span.account-name");
	By accountimg = By.xpath("//img[@class ='nav-avatar']");
	By contactPrimaryMenuLink = By.xpath("(//a[@id='nav-primary-contacts-branch'])[position()=1]");
	By contactSecondaryyMenuLink = By.xpath("(//a[@id='nav-secondary-contacts'])[position()=1]");
	By accountOwnerName = By.cssSelector("div.user-info-name");

	// constractor of the page
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getHomePageTitle() {
		return elementUtil.doGetPageTitleWithTitleIs(10, Constants.HOME_PAGE_TITLE);
		// return driver.getTitle();
	}

	public String getHomePageHeader() {
		if (elementUtil.doIsDisplayed(header))
			return elementUtil.doGetText(header);
		return null;
	}

	public String getAccountOwner() {
		elementUtil.doWaitForElementPresent(accountimg, 10).click();
	//elementUtil.doClick(accountimg);
	String owner = elementUtil.doGetText(accountOwnerName);
	return owner;			
	}
	
	public ContactsPage goToContactPage() {
		clickOnContactsMenuLink();
		return new ContactsPage(driver);
	}
	
	private void clickOnContactsMenuLink() {
		elementUtil.doWaitForElementPresent(contactPrimaryMenuLink, 10);
		elementUtil.doClick(contactPrimaryMenuLink);
		elementUtil.clickElementWhenReady(contactSecondaryyMenuLink, 5);
	}

}
