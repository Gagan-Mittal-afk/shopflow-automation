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

        WebDriver driver = ((BaseTest) testClass).getDriver();

        ScreenshotUtil.captureScreenshot(
                driver,
                result.getMethod().getMethodName()
        );
    }
}