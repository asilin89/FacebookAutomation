package com.facebook.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.BasePage;
import utils.UtilConstants;

public class FacebookLoginPage extends BasePage{

	@FindBy(id = "email")
	private WebElement emailField;
	
	@FindBy(id = "pass")
	private WebElement passwordField;
	
	@FindBy(id = "loginbutton")
	private WebElement btnLogin;
	
	public FacebookLoginPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	public void openPage()
	{
		driver.get(UtilConstants.FB_LOGIN_URL);
	}
	
	public void login(String email, String password)
	{
		try
		{
			waitForVisibilityOfElement(emailField);
			emailField.sendKeys(email);
			passwordField.sendKeys(password);
			btnLogin.click();
		}
		catch(Exception e)
		{
			System.err.println("Couldn't login to Facebook.");
		}
	}

}
