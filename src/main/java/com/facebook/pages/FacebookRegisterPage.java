package com.facebook.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.BasePage;
import utils.UtilConstants;

public class FacebookRegisterPage extends BasePage{
	
	@FindBy(xpath = "//a[text() = 'English (US)']")
	private WebElement switchToEnglishLink;
	
	@FindBy(xpath = "//ul[@class = 'uiList localeSelectorList _2pid _509- _4ki _6-h _6-j _6-i']/li[1]")
	private WebElement defaultLanguage;

	public FacebookRegisterPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	public void openPage()
	{
		driver.get(UtilConstants.FACEBOOK_SIGN_UP_URL);
	}
	
	public void swithToEnglish()
	{
		if(!defaultLanguage.getText().equals("English (US)"))
		{
			switchToEnglishLink.click();
		}
	}

}
