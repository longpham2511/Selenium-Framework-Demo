package listeners_component;

import Extent_Report_NG.extent_report_ng;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends test_components.TestBase implements ITestListener {
    ExtentReports extent = extent_report_ng.getReport();
    ExtentTest test;

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //thread safe, no issue from running parallel mode

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
       test = extent.createTest(result.getMethod().getMethodName()); //methodname is the test name
        extentTest.set(test); //assign to unique thread id -> unique thread id assigned to test
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        extentTest.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        extentTest.get().fail(result.getThrowable()); //give out error message
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")  //get class -> go to that class -> get the driver
                    .get(result.getInstance());             //get this driver to put it into the driver in screenshot
        } catch (Exception e) {
            e.printStackTrace();
        }


        String filepath = null;
        try {
            filepath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {   //if there is no filepath, give out error
            throw new RuntimeException(e);
        }
        //Take screenshot and attach to report
        extentTest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extent.flush();
    }
}
