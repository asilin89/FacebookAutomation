package com.facebook.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.BasePage;

public class FacebookHomePage extends BasePage{

	//Navigation panel/menu
	@FindBy(id = "left_nav_section_nodes")
	private WebElement navigationSection;
	
	//Search bar
	@FindBy(xpath = "//input[@placeholder = 'Search']")
	private WebElement searchBar;
	
	//Search button
	@FindBy(xpath = "//button[@aria-label = 'Searc']")
	private WebElement btnSearch;
	
	public FacebookHomePage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	public void isNavigationPresented()
	{
		assertTrue(waitForVisibilityOfElement(navigationSection));
//		if(waitForVisibilityOfElement(navigationSection) == true)
//		{
//			return true;
//		}
//		else
//		{
//			//System.err.println("Navigation panel is not visible!");
//			this.currentTest.log.error("Navigation panel is not visible!");
//			assertTrue();
//		}
	}
	
	public void searchForPeople(String name)
	{
		waitForVisibilityOfElement(searchBar);
		typeOnElement(searchBar, name);
		btnSearch.click();
	}

}
