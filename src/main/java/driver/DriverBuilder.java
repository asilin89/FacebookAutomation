package driver;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import utils.UtilConstants;

public class DriverBuilder {

	private static WebDriver driver;
	private static final Logger logger = LogManager.getRootLogger();
	
	static
	{
		initWebDriver();
	}
	
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	private static void initWebDriver()
	{
		System.setProperty(UtilConstants.CHROME_PROPERTY, UtilConstants.CHROME_PATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	}
}
