package com.qa.hubspot.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {

		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("element could not be created: " + locator);
		}
		return element;
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doSelectValueByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectValueByvalue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	public void doSelectValueByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

//create a list of dropdown options without using  a Select class

	public static void SelectValuesFromDropDown(WebDriver driver, By locator, String value) {
		List<WebElement> industryList = driver.findElements(locator);
		for (int i = 0; i < industryList.size(); i++) {
			String text = industryList.get(i).getText();
			if (text.equals(value)) {
				industryList.get(i).click();
				break;
			}
		}

	}

	// SendKeys and Click using Actions Class

	public void doActionsClick(By locator) {
		Actions ac = new Actions(driver);
		ac.click(getElement(locator)).perform();
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions ac = new Actions(driver);
		ac.sendKeys(getElement(locator), value).perform();
	}

	// select single, multiple or all values.
	public void SelectChoiceValues(By locator, String... value) {

		List<WebElement> choiceList = driver.findElements(locator);
		if (!value[0].equalsIgnoreCase("all")) {
			for (int i = 0; i < choiceList.size(); i++) {
				String text = choiceList.get(i).getText();

				for (int k = 0; k < value.length; k++) {
					choiceList = driver.findElements(locator);
					if (text.equals(value[k])) {
						choiceList.get(i).click();
						break;
					}
				}

			}
		}
		// select all values
		else {
			try {
				for (int all = 0; all < choiceList.size(); all++) {
					choiceList.get(all).click();
				}
			} catch (Exception e) {
			}
		}
	}

	public List<String> footerLinkList(WebDriver driver, By locator) {

		List<String> footerLinkListArray = new ArrayList<String>();
		List<WebElement> footerLinks = driver.findElements(locator);

		for (int i = 0; i < footerLinks.size(); i++) {

			String text = footerLinks.get(i).getText();
			footerLinkListArray.add(text);
		}
		return footerLinkListArray;
	}

	public List<String> headerLinkList(WebDriver driver, By locator) {

		List<String> headerLinkListArray = new ArrayList<String>();
		List<WebElement> headerLinks = driver.findElements(locator);

		for (int i = 0; i < headerLinks.size(); i++) {

			String text = headerLinks.get(i).getText();
			headerLinkListArray.add(text);
		}
		return headerLinkListArray;
	}

	public void switchToFrame() {
		WebElement framename = driver.findElement(By.xpath("//frame[@name='mainpanel']"));
		driver.switchTo().frame(framename);
	}

//****************************************wait util*****************************************************

	// get a page title method with an explicit wait by providing a partial page
	// title(WebDriver Wait)

	public String doGetPageTitleWithCondition(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	// get a page title method with an explicit wait by providing a complete page
	// title(WebDriver Wait)
	public String doGetPageTitleWithTitleIs(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	// wait for a webelement.
	public WebElement doWaitForElementPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void clickElementWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		element.click();
	}


	public String doWaitForPageCurrentUrl(int timeout, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlContains(urlValue));
		return driver.getCurrentUrl();
	}

	public WebElement doWaitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public Alert doWaitForAlertPresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public WebElement waitForElementWithFluentWaitCondition(WebDriver driver, By locator, int timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

}
