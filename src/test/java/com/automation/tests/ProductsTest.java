package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    @Test
    public void verifyProductsPage() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertEquals(
                productsPage.getProductsTitle(),
                "Products"
        );
    }
}
