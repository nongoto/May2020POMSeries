package com.qa.hubspot.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomePageTest {

	static WebDriver driver;
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get("https://app.hubspot.com/login");

	By locatorname = By.cssSelector("span.account-name");
	By email = By.id("username");
	By password = By.id("password");
	By loginbtn = By.id("loginBtn");
	By accountNameLink = (By.xpath("//span[text()='7975611']//parent::td//preceding-sibling::td/div/div/span/span/span/a/span/span/span"));
	By accountimg = By.xpath("//img[@class ='nav-avatar']");
			

	
	driver.findElement(email).sendKeys("nongoto@gmail.com");
	driver.findElement(password).sendKeys("Hubsportpassword123");
	driver.findElement(loginbtn).click();
	driver.findElement(accountNameLink).click();
	driver.findElement(accountimg).click();
	
	
	
	boolean displayedname = doIsDisplayed(locatorname);
	System.out.println(displayedname);

	}
	
	public static boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public static WebElement getElement(By locator) {

		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("element could not be created: " + locator);
		}
		return element;

	}
}
