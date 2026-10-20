package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    @DataProvider(name = "users")
    public Object[][] users() {
        return new Object[][] {
            {"standard_user", "secret_sauce"},
            {"problem_user", "secret_sauce"}
        };
    }

    @Test(
        groups = {"smoke"},
        priority = 2,
        dataProvider = "users"
    )
    public void verifyProductsPage(String username, String password) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);

        ProductsPage productsPage = new ProductsPage(driver);

        String title = productsPage.getProductsTitle();

        Assert.assertEquals(title, "Products");
        Assert.assertTrue(title.contains("Products"));
        Assert.assertNotNull(title);
    }
}
