package com.facebook.pages;

import static org.testng.Assert.assertEquals;

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
	
	@FindBy(id = "terms-link")
	private WebElement termsLink;

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
	
	public void clickOnTermsOfUseAndVerifyPageTitle(String title)
	{
		waitForVisibilityOfElement(termsLink);
		// Store the current window handle
		String winHandleBefore = this.currentTest.getDriver().getWindowHandle();
		termsLink.click();

		// Switch to new window opened
		for(String winHandle : this.currentTest.getDriver().getWindowHandles())
		{
			this.currentTest.getDriver().switchTo().window(winHandle);
		}

		// Perform the actions on new window
		assertEquals(this.currentTest.getDriver().getTitle(), title);

		// Close the new window, if that window no more required
		this.currentTest.getDriver().close();

		// Switch back to original browser (first window)
		this.currentTest.getDriver().switchTo().window(winHandleBefore);

		// Continue with original browser (first window)
	}
}
