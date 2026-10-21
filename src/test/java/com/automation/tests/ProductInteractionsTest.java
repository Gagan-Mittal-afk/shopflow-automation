package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductDetailsPage;
import com.automation.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductInteractionsTest extends BaseTest {

    @Test(groups = {"products", "regression"}, priority = 1)
    public void shouldSortProductsByPriceLowToHigh() {
        ProductsPage productsPage = loginAsStandardUser();

        productsPage.sortByVisibleText("Price (low to high)");
        List<Double> displayedPrices = productsPage.getDisplayedPrices();
        List<Double> sortedPrices = new ArrayList<>(displayedPrices);
        Collections.sort(sortedPrices);

        Assert.assertEquals(displayedPrices, sortedPrices, "Products should be sorted by ascending price");
        Assert.assertEquals(productsPage.getInventoryItemCount(), 6, "SauceDemo should list six inventory items");
    }

    @Test(groups = {"products", "smoke"}, priority = 2)
    public void shouldShowBackpackProductDetailsAndAddItToCart() {
        ProductsPage productsPage = loginAsStandardUser();

        productsPage.openBackpackDetails();
        ProductDetailsPage detailsPage = new ProductDetailsPage(driver);

        Assert.assertEquals(detailsPage.getProductName(), "Sauce Labs Backpack");
        Assert.assertEquals(detailsPage.getProductPrice(), "$29.99");

        detailsPage.addToCart();
        detailsPage.returnToProducts();

        Assert.assertEquals(new ProductsPage(driver).getCartItemCount(), 1);
    }

    @Test(groups = {"products", "regression"}, priority = 3)
    public void shouldRemoveBackpackFromProductsPage() {
        ProductsPage productsPage = loginAsStandardUser();

        productsPage.addBackpackToCart();
        Assert.assertTrue(productsPage.isBackpackRemoveButtonDisplayed());
        Assert.assertEquals(productsPage.getCartItemCount(), 1);

        productsPage.removeBackpack();

        Assert.assertEquals(productsPage.getCartItemCount(), 0, "Removing the item should clear the cart badge");
    }

    private ProductsPage loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(configReader.getProperty("username"), configReader.getProperty("password"));
        return new ProductsPage(driver);
    }
}
