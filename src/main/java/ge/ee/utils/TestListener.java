package ge.ee.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    Utils utils;


    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName(); // რეპორტი
        ExtentReportManager.createTest(testName); // რეპორტი
        ExtentReportManager.getTest().info("Test Started: " + testName); // ლოგირება

        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().pass("Test Passed"); // წარმატებული
        System.out.println("Test Passed: " + result.getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        ExtentReportManager.getTest().fail("Test Failed: " + result.getThrowable()); // დავაფეილოთ
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().skip("Test Skipped: " + result.getThrowable()); // დავსკიპოთ
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
        ExtentReportManager.flushReports(); // რეპორტი დავხუროთ.
    }
}