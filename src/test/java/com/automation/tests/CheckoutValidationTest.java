package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutValidationTest extends BaseTest {

    @Test(groups = {"checkout", "regression"})
    public void shouldRequireFirstNameDuringCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(configReader.getProperty("username"), configReader.getProperty("password"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addBackpackToCart();
        productsPage.clickCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.submitWithoutCustomerInformation();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                "Error: First Name is required"
        );
    }
}
