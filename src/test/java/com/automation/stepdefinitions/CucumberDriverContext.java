package com.automation.stepdefinitions;

import org.openqa.selenium.WebDriver;

public final class CucumberDriverContext {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private CucumberDriverContext() {
    }

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Cucumber WebDriver has not been initialized");
        }
        return driver;
    }

    public static WebDriver getDriverOrNull() {
        return DRIVER.get();
    }

    public static void clear() {
        DRIVER.remove();
    }
}
