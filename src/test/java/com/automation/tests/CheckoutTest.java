package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.LoginPage;
import com.automation.pages.OrderConfirmationPage;
import com.automation.pages.ProductsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void verifyCheckoutAndOrderConfirmation() {

        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        // Open Products page
        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertEquals(
                productsPage.getProductsTitle(),
                "Products"
        );

        // Add Sauce Labs Backpack
        productsPage.addBackpackToCart();

        // Open Cart
        productsPage.clickCart();

        // Proceed to Checkout
        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartItemName(),
                "Sauce Labs Backpack"
        );

        cartPage.clickCheckout();

        // Enter customer information
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterCustomerInformation(
                "Gagan",
                "Mittal",
                "201301"
        );

        // Complete order
        OrderConfirmationPage confirmationPage =
                new OrderConfirmationPage(driver);

        confirmationPage.clickFinish();

        // Verify order confirmation
        Assert.assertEquals(
                confirmationPage.getConfirmationMessage(),
                "Thank you for your order!"
        );
    }
}
