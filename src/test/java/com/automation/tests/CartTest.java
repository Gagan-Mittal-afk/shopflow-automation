package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void verifyProductInCart() {

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

        // Verify product in Cart
        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartItemName(),
                "Sauce Labs Backpack"
        );
    }
}
