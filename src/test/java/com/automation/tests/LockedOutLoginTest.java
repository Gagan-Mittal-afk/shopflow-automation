package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LockedOutLoginTest extends BaseTest {

    @Test(groups = {"login", "regression"}, priority = 2)
    public void shouldShowLockedOutMessageForLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out."
        );
    }
}
