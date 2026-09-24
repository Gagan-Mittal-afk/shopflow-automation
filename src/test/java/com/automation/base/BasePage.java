package com.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    protected void waitForClickable(org.openqa.selenium.By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForVisible(org.openqa.selenium.By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
