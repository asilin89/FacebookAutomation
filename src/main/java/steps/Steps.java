package steps;

import org.openqa.selenium.WebDriver;

import com.facebook.pages.FacebookHomePage;
import com.facebook.pages.FacebookLoginPage;
import com.facebook.pages.FacebookProfilePage;
import com.facebook.pages.FacebookRegisterPage;
import com.facebook.pages.FacebookSearchResultsPage;

import utils.FBUtilConstants;
import utils.UtilConstants;

public class Steps {

	private WebDriver driver;
	
	public Steps(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void openPageAndSwitchToEnglish()
	{
		FacebookRegisterPage registerPage = new FacebookRegisterPage(driver);
		registerPage.openPage();
		registerPage.swithToEnglish();
	}
	
	public void clickOnTermsAndVerifyPageTitle(String title)
	{
		FacebookRegisterPage registerPage = new FacebookRegisterPage(driver);
		registerPage.clickOnTermsOfUseAndVerifyPageTitle(title);
	}
	
	public void login()
	{
		FacebookLoginPage loginPage = new FacebookLoginPage(driver);
		loginPage.openPage();
		loginPage.login(UtilConstants.FB_EMAIL, UtilConstants.FB_PASSWORD);
	}
	
	public void navigateToHomePage()
	{
		FacebookProfilePage profilePage = new FacebookProfilePage(driver);
		profilePage.goToHomePage();
		
		FacebookHomePage homePage = new FacebookHomePage(driver);
		homePage.isNavigationPresented();
	}
	
	public void searchForPeople(String name)
	{
		FacebookHomePage homePage = new FacebookHomePage(driver);
		homePage.searchForPeople(name);	
	}
	
	public void verifySearchResults(String name, String result)
	{
		FacebookSearchResultsPage searchResultsPage = new FacebookSearchResultsPage(driver);
		searchResultsPage.verifySearchResults(name, result);
	}
}
