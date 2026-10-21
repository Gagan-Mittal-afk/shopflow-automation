package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartWorkflowTest extends BaseTest {

    @Test(groups = {"cart", "regression"}, priority = 1)
    public void shouldRemoveItemFromCart() {
        CartPage cartPage = openCartWithBackpack();

        Assert.assertEquals(cartPage.getCartItemCount(), 1);
        cartPage.removeBackpack();

        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty after removing its only item");
    }

    @Test(groups = {"cart", "regression"}, priority = 2)
    public void shouldContinueShoppingFromCart() {
        CartPage cartPage = openCartWithBackpack();

        cartPage.continueShopping();

        Assert.assertEquals(new ProductsPage(driver).getProductsTitle(), "Products");
    }

    private CartPage openCartWithBackpack() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(configReader.getProperty("username"), configReader.getProperty("password"));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addBackpackToCart();
        productsPage.clickCart();
        return new CartPage(driver);
    }
}
