package AutomationPractice.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import AutomationPractice.resources.ExtentReporterDetails;

public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extReport =  ExtentReporterDetails.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//Thread Safe
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extReport.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //creates unique thread id for each test 
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Case is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extReport.flush();
	}
	
	
	
}