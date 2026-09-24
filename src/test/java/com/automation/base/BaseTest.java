package com.automation.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected Properties properties;

    @BeforeMethod
    public void setUp() throws IOException {

        properties = new Properties();

        FileInputStream file = new FileInputStream(
                "src/test/resources/config.properties"
        );

        properties.load(file);
        file.close();

        String browser = properties.getProperty("browser");

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

        driver.get(properties.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    // Allows the TestNG listener to access the WebDriver
    public WebDriver getDriver() {
        return driver;
    }
}
