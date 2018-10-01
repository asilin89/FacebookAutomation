package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	
	protected WebDriver driver;
	protected BaseTest currentTest = new BaseTest();
	
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public boolean waitForVisibilityOfElement(WebElement element)
	{
		try
		{
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
			return true;
		}
		catch(Exception e)
		{
			this.currentTest.log.error(element + " is not visible. " + e.getMessage());
			return false;
		}
	}
	
	public void typeOnElement(WebElement element, String text)
	{
		try
		{
			element.sendKeys(text);
		}
		catch(Exception e)
		{
			this.currentTest.log.error("Couldn't type on specified element. " + e.getMessage() + ": " + this.getClass());
		}
	}

}
