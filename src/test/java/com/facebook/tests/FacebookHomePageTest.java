package com.facebook.tests;

import org.testng.annotations.Test;

import utils.extentreports.ExtentTestManager;

public class FacebookHomePageTest extends FacebookLoginTest{
	
	@Test(description="Test that FB user can navigate to the Home Page")
	public void testHomePage()
	{
		//Small change
		ExtentTestManager.getTest().setDescription("Test that FB user can navigate to the Home Page");
		steps.navigateToHomePage();
	}

}
