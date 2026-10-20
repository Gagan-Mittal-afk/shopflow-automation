package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {

    @Test(groups = {"login"})
    public void verifyInvalidLogin() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(configReader.getProperty("username"), "wrong_password");

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertFalse(errorMessage.isBlank(), "Invalid login must show a non-empty error");
        Assert.assertEquals(
                errorMessage,
                "Epic sadface: Username and password do not match any user in this service"
        );
    }
}
