package com.automation.stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.automation.utils.ConfigReader;
import com.automation.utils.DriverFactory;
import com.automation.utils.ScreenshotUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

    @Before
    public void startBrowser() throws IOException {
        ConfigReader config = new ConfigReader();
        WebDriver driver = DriverFactory.create(config.getProperty("browser"));
        CucumberDriverContext.setDriver(driver);
        driver.manage().window().maximize();
        driver.get(config.getProperty("url"));
    }

    @After
    public void stopBrowser(Scenario scenario) {
        WebDriver driver = null;
        try {
            driver = CucumberDriverContext.getDriverOrNull();
            if (scenario.isFailed()) {
                ScreenshotUtil.captureScreenshot(driver, scenario.getName());
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
            CucumberDriverContext.clear();
        }
    }
}
