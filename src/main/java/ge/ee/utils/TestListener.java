package ge.ee.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result){
        String testName = result.getMethod().getMethodName(); // რეპორტინგისთვის
        ExtentReportManager.createTest(testName); // რეპორტისთვის
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        System.out.println("Test Success: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result){
        System.out.println("Test Failure: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result){
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context){
        System.out.println("Test Suite Started: " + context.getName());
    }

    public void onFinish(ITestContext context){
        System.out.println("Test Suite Finished: " + context.getName());
        ExtentReportManager.flushReports(); // დავასრულოთ რეპორტი
    }


}