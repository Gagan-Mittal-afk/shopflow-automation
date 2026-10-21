package com.automation.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.automation.base.BaseTest;
import com.automation.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Path;

/** TestNG listener that records result details in the Extent Spark HTML report. */
public class ExtentReportListener implements ITestListener, IExecutionListener {

    private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentReportManager.getExtentReports().createTest(
                result.getTestClass().getName() + "." + result.getMethod().getMethodName()
        );

        String[] groups = result.getMethod().getGroups();
        if (groups.length > 0) {
            test.assignCategory(groups);
        } else {
            test.assignCategory("ungrouped");
        }
        CURRENT_TEST.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getCurrentTest(result).pass("Passed in " + durationInMilliseconds(result) + " ms");
        CURRENT_TEST.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = getCurrentTest(result);
        test.fail(result.getThrowable());
        test.info("Failed after " + durationInMilliseconds(result) + " ms");
        attachFailureScreenshot(result, test);
        CURRENT_TEST.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = getCurrentTest(result);
        test.skip(result.getThrowable());
        test.info("Skipped after " + durationInMilliseconds(result) + " ms");
        CURRENT_TEST.remove();
    }

    @Override
    public void onFinish(ITestContext context) {
        // The report is flushed after the entire TestNG execution, not per test context.
    }

    @Override
    public void onExecutionFinish() {
        ExtentReportManager.flush();
    }

    private ExtentTest getCurrentTest(ITestResult result) {
        ExtentTest test = CURRENT_TEST.get();
        if (test == null) {
            onTestStart(result);
            test = CURRENT_TEST.get();
        }
        return test;
    }

    private void attachFailureScreenshot(ITestResult result, ExtentTest test) {
        Object screenshotAttribute = result.getAttribute("screenshotPath");
        if (screenshotAttribute == null && result.getInstance() instanceof BaseTest baseTest) {
            WebDriver driver = baseTest.getDriver();
            if (driver != null) {
                screenshotAttribute = ScreenshotUtil.captureScreenshot(
                        driver, result.getMethod().getMethodName()
                );
                if (screenshotAttribute != null) {
                    result.setAttribute("screenshotPath", screenshotAttribute);
                }
            }
        }

        if (!(screenshotAttribute instanceof String screenshotPath)) {
            return;
        }

        test.addScreenCaptureFromPath(Path.of(screenshotPath).toAbsolutePath().toString());
    }

    private long durationInMilliseconds(ITestResult result) {
        return result.getEndMillis() - result.getStartMillis();
    }
}
