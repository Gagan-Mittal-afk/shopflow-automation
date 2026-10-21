package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.pages.NavigationMenu;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test(groups = {"navigation", "regression"})
    public void shouldOpenMenuAndLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(configReader.getProperty("username"), configReader.getProperty("password"));

        NavigationMenu navigationMenu = new NavigationMenu(driver);
        navigationMenu.open();
        Assert.assertTrue(navigationMenu.isLogoutLinkDisplayed(), "Logout should be available in the menu");

        navigationMenu.logout();

        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Logout should return the user to the login page");
    }
}
