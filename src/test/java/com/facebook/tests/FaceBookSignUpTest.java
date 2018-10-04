package com.facebook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.facebook.pages.FacebookRegisterPage;

import pages.BaseTest;

public class FaceBookSignUpTest extends BaseTest{
	
	@BeforeMethod(alwaysRun = true)
	public void run()
	{
		//Hello
		steps.openPageAndSwitchToEnglish();
	}
}
