package listener;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import driver.DriverBuilder;
import pages.BaseTest;
import utils.extentreports.ExtentManager;
import utils.extentreports.ExtentTestManager;

public class TestListener extends BaseTest implements ITestListener{

	protected BaseTest currentTest = new BaseTest();
	
	private static String getTestMethodName(ITestResult iTestResult) 
	{
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
	
	@Override
	public void onTestFailure(ITestResult result) 
	{
		this.currentTest.log.error(getTestMethodName(result) + " failed");
		 
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = result.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
 
        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).
                getScreenshotAs(OutputType.BASE64);
 
        //Extentreports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed",
                ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot) + " " + result.getThrowable().getMessage());
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		 this.currentTest.log.warn(getTestMethodName(result) + " skipped");
	     ExtentTestManager.getTest().log(LogStatus.SKIP, "Skipped");
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		this.currentTest.log.info(getTestMethodName(result) + " start");
        ExtentTestManager.startTest(result.getMethod().getMethodName(),"");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		this.currentTest.log.info(getTestMethodName(result) + " succeed");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		this.currentTest.log.info("Starting..." + context.getName());
		context.setAttribute("WebDriver", this.driver);
		
	}

	@Override
	public void onFinish(ITestContext context) {
		this.currentTest.log.info("Finishing..." + context.getName());
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
		
	}
	
//	private void takeScreenshot(String result)
//	{
//        File screenCapture = ((TakesScreenshot)BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);
//        try 
//        {
//            //FileUtils.copyFile(screenCapture, new File(".//target/screenshots/screenshot.png"));
//        	FileUtils.copyFile(screenCapture, new File("C://testautomation/screenshots/" + result+ "_screenshot.png"));
//        } catch (IOException e)
//        {
//        	this.currentTest.log.info("Couldn't take a screenshot: " + e.getLocalizedMessage());
//        }
//    }
}
