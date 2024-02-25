package Extent_Report_NG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extent_report_ng {
    public static ExtentReports getReport(){
        String path = System.getProperty("user.dir")+"//reports//index.html"; //create a HTML file
        ExtentSparkReporter reporter = new ExtentSparkReporter(path); //create a report to store results

        reporter.config().setReportName("Demo Report");     //config the report
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports(); //main class obj for configuration
        extent.attachReporter(reporter); //attach the report - create some configs for the main report
        extent.setSystemInfo("Long","QA Test");
        return extent;
    }
}
