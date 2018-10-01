package pages;

import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import driver.DriverBuilder;
import steps.Steps;
import utils.UtilConstants;

public class BaseTest {
	
	protected static WebDriver driver;
	protected Steps steps;
	public static final Logger log = LogManager.getLogger(Logger.class.getName());
	
	public Logger getLogger()
	{
		return this.log;
	}
	
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	@SuppressWarnings("deprecation")
	@Parameters({"browser", "project"})
	@BeforeMethod(alwaysRun = true)
	public void initDriver(String browser, String project)
	{
		log.info("Starting initDriver method...");
		if(browser.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			System.setProperty(UtilConstants.CHROME_PROPERTY, UtilConstants.CHROME_PATH);
			driver = new ChromeDriver(options);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{	
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("permissions.default.desktop-notification", 1);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			System.setProperty(UtilConstants.FIREFOX_PROPERTY, UtilConstants.FIREFOX_PATH);
			driver = new FirefoxDriver(capabilities);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		steps = new Steps(driver);
	}
	
	public void takeScreenshot(String result) throws IOException
	{
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(screenshot, new File("C:\\TestAutomation\\" + result + "_screenshot.png"));
		FileUtils.copyFile(screenshot, new File("c:\\tmp\\screenshot.png"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		try
		{
			if(driver != null)
			{
				driver.manage().deleteAllCookies();
			}
		}
		catch(Exception e)
		{
			log.info("Couldn't delete all the cookies.");
		}
		finally
		{
			driver.quit();
		}
	}
}
