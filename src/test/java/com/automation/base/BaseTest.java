package com.automation.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected Properties properties;

    @BeforeMethod
    public void setUp() throws IOException {

        // Load configuration
        properties = new Properties();

        FileInputStream file = new FileInputStream(
                "src/test/resources/config.properties"
        );

        properties.load(file);
        file.close();

        // Read browser from config
        String browser = properties.getProperty("browser");

        // Launch browser
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            throw new IllegalArgumentException(
                    "Browser not supported: " + browser
            );
        }

        driver.manage().window().maximize();

        // Open URL from config
        driver.get(properties.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
