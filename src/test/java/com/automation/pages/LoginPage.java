package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        waitForVisible(usernameField);
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        waitForVisible(passwordField);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        waitForClickable(loginButton);
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        waitForVisible(errorMessage);
        return driver.findElement(errorMessage).getText();
    }
}
