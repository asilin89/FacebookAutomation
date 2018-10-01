package com.facebook.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.BasePage;

public class FacebookProfilePage extends BasePage{

	@FindBy(id = "findFriendsNav")
	private WebElement btnFindFriends;
	
	@FindBy(xpath = "//div[@data-click = 'home_icon']/a")
	private WebElement btnHome;
	
	public FacebookProfilePage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	public void goToHomePage()
	{
		waitForVisibilityOfElement(btnHome);
		btnHome.click();
	}

}
