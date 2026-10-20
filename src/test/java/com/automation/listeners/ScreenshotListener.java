package com.automation.listeners;

import com.automation.base.BaseTest;
import com.automation.utils.ScreenshotUtil;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest) {

            WebDriver driver = ((BaseTest) testClass).getDriver();

            if (driver != null) {
                ScreenshotUtil.captureScreenshot(
                        driver,
                        result.getMethod().getMethodName()
                );
            }
        }
    }
}