package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.util.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the driver on the basis of given browser
	 * name Project Path =
	 * C:\Users\Nick\Documents\Selenium\Workspaces\NaveenJavaTraining\UdemySeleniumtraining\May2020POMSeries>
	 * when running from command line: (Project Path) mvn clean install
	 * -Dtest=com.qa.hubspot.tests.LoginPageTest -Denv="qa"
	 * 
	 * @param browsername
	 * @return driver
	 */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser name is: " + browserName);
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// Ddriver = new ChromeDriver(optionsManager.getChomeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChomeOptions()));
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}

		else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			// driver = new FirefoxDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println(browserName + " is not found, please pass the corrent browser......");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize properties from config.properties file
	 * 
	 * @return prop
	 */
	public Properties init_prop() {
		prop = new Properties();
		String path = null;
		String env = null;
		try {
			env = System.getProperty("env");

			if (env == null) {
				path = "./src/main/java/com/qa/hubspot/config/config.properties";
			} else {
				switch (env) {
				case "qa":
					path = "./src/main/java/com/qa/hubspot/config/qa.config.properties";
					break;
				case "dev":
					path = "./src/main/java/com/qa/hubspot/config/dev.config.properties";
					break;
				case "prod":
					path = "./src/main/java/com/qa/hubspot/config/prod.config.properties";
					break;
				case "stage":
					path = "./src/main/java/com/qa/hubspot/config/stage.config.properties";
					break;
				default:
					System.out.println("Please pass a correct evironment value....");
					break;
				}
			}
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	// take screenshot method
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

}
