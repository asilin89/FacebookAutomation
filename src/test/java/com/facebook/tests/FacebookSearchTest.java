package com.facebook.tests;

import java.util.HashMap;

import org.testng.annotations.Test;

import data.CSVDataProvider;
import utils.FBUtilConstants;
import utils.extentreports.ExtentTestManager;

public class FacebookSearchTest extends FacebookLoginTest{
	
	@Test(dataProvider = "testData", dataProviderClass = CSVDataProvider.class, description="Search test in Facebook.")
	public void testSearch(HashMap<String,String>hashMap)
	{
		ExtentTestManager.getTest().setDescription("Search test in Facebook.");
		
		steps.searchForPeople(hashMap.get(FBUtilConstants.SEARCHED_NAME));
		steps.verifySearchResults(hashMap.get(FBUtilConstants.SEARCHED_NAME), 
								  hashMap.get(FBUtilConstants.EXPECTED_RESULT));
		
		//Added a comment here!
	}
}
