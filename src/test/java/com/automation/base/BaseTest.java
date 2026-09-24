package com.automation.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automation.utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;
    protected ConfigReader configReader;

    @BeforeMethod
    public void setUp() throws IOException {

        configReader = new ConfigReader();

        String browser = configReader.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--disable-features=PasswordLeakDetection");

            driver = new ChromeDriver(options);

        } else {

            throw new IllegalArgumentException(
                    "Browser not supported: " + browser
            );
        }

        driver.manage().window().maximize();

        driver.get(configReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
