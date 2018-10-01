package com.facebook.pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.BasePage;

public class FacebookSearchResultsPage extends BasePage{

	//Search results
	@FindBy(xpath = "//a[@class = '_32mo']")
	private List<WebElement> searchResults;
	
	//Results filter
	@FindBy(id = "js_1hs")
	private WebElement resultsFilterPanel;
	
	//No results message
	@FindBy(xpath = "//div[@class = '_43zq _50f3 _5kx5']")
	private WebElement noResultsMessage;
	
	public FacebookSearchResultsPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	public void verifySearchResults(String name, String result)
	{
		waitForVisibilityOfElement(resultsFilterPanel);
		if(result.equalsIgnoreCase("TRUE"))
		{
			for(WebElement we: searchResults)
			{
				assertTrue(we.getText().contains(name));
			}
		}
		else
		{
			assertTrue(noResultsMessage.isDisplayed());
		}
	}
}
