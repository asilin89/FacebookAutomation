package com.facebook.tests;

import org.testng.annotations.BeforeMethod;

import pages.BaseTest;

public class FacebookLoginTest extends BaseTest{

	@BeforeMethod(alwaysRun = true)
	public void login()
	{
		//login to Facebook app.
		steps.login();
	}
}
