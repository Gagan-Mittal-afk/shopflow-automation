package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;
import com.automation.pages.OrderConfirmationPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void verifySuccessfulLoginAndAddProduct() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Verify Products page
        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertEquals(
                productsPage.getProductsTitle(),
                "Products"
        );

        // Add Sauce Labs Backpack
        productsPage.addBackpackToCart();

        // Open cart
        productsPage.clickCart();

        // Verify cart item
        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartItemName(),
                "Sauce Labs Backpack"
        );

        // Proceed to checkout
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