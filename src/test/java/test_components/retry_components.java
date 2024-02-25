package test_components;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retry_components implements IRetryAnalyzer {
    int count = 0;
    int max_try = 1;
    @Override
    public boolean retry(ITestResult iTestResult) {
    if (count<max_try){
        count ++;
        return true;   //if the condition is true it will do the test case 1 more time
    }

        return false;
    }
}
