package Extent_Report;

import Extent_Report_NG.extent_report_ng;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.extension.incubator.trace.ExtendedSpanBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;



public class extent_report_demo1 extends extent_report_ng {
    //individual test case that outputs a report
    ExtentReports extent;
    @BeforeTest
    public void config() {
        //ExtentReport and ExtendSparkReporter
        String path = System.getProperty("user.dir")+"\\reports\\index.html"; //create a HTML file
        ExtentSparkReporter reporter = new ExtentSparkReporter(path); //create a report to store results

        reporter.config().setReportName("Demo Report");     //config the report
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports(); //main class obj for configuration
        extent.attachReporter(reporter); //attach the report - create some configs for the main report
        extent.setSystemInfo("Long","QA Test");
    }

    @Test
    public void demo1() {
        ExtentTest test = extent.createTest("demotest1"); //test start
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://rahulshettyacademy.com/client/");
        System.out.println(driver.getTitle());
        driver.close();
        test.fail("do not match");   //report to the extent report - test may pass, but the report will have it as a failed test
       extent.flush();  //test finishes

    }
}
