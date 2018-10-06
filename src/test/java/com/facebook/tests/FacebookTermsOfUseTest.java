package com.facebook.tests;

import java.util.HashMap;

import org.testng.annotations.Test;

import data.CSVDataProvider;
import utils.FBUtilConstants;
import utils.extentreports.ExtentTestManager;

public class FacebookTermsOfUseTest extends FaceBookSignUpTest{
	
	@Test(dataProvider = "testData", dataProviderClass = CSVDataProvider.class, description = "Terms Of Use verification in Facebook.")
	public void testTerms(HashMap<String,String>hashMap)
	{
		ExtentTestManager.getTest().setDescription("Terms Of Use verification in Facebook.");
		steps.clickOnTermsAndVerifyPageTitle(hashMap.get(FBUtilConstants.TERMS_PAGE_TITLE));
	}
}
