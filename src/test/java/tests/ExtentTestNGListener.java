package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ReportManager;

import java.util.concurrent.ConcurrentHashMap;

import org.testng.*;
import utils.ExtentTestManager;


public class ExtentTestNGListener implements ITestListener, ISuiteListener {
    private static final ConcurrentHashMap<Long, ExtentTest> threadIdToTest = new ConcurrentHashMap<>();
    private ExtentReports extentReports;

    @Override
    public void onStart(ISuite suite) {
        extentReports = ReportManager.getReport();
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        ExtentTest test = extentReports.createTest(className + "::" + testName);
        threadIdToTest.put(Thread.currentThread().threadId(), test);
        ExtentTestManager.set(test);
        test.info("Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = getTest();
        if (result.getThrowable() != null) {
            test.fail(result.getThrowable());
        } else {
            test.fail("Test failed");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest().skip("Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // no-op
    }

    @Override
    public void onStart(ITestContext context) {
        // no-op
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentTestManager.clear();
    }

    private ExtentTest getTest() {
        return threadIdToTest.get(Thread.currentThread().threadId());
    }
}
