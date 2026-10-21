package com.automation.base;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.utils.ConfigReader;
import com.automation.utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;
    protected ConfigReader configReader;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) throws IOException {

        configReader = new ConfigReader();

        String configuredBrowser = System.getProperty("browser", browser);
        driver = DriverFactory.create(configuredBrowser);

        driver.manage().window().maximize();
        driver.get(configReader.getProperty("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
