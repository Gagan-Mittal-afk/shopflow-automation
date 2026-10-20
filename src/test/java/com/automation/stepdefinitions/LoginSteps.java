package com.automation.stepdefinitions;

import java.io.IOException;

import org.testng.Assert;

import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;
import com.automation.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    @Given("the SauceDemo login page is open")
    public void theSauceDemoLoginPageIsOpen() {
        Assert.assertTrue(CucumberDriverContext.getDriver().getCurrentUrl().contains("saucedemo.com"));
    }

    @When("the user signs in with configured valid credentials")
    public void theUserSignsInWithConfiguredValidCredentials() throws IOException {
        ConfigReader config = new ConfigReader();
        LoginPage loginPage = new LoginPage(CucumberDriverContext.getDriver());
        loginPage.login(config.getProperty("username"), config.getProperty("password"));
    }

    @Then("the Products page is displayed")
    public void theProductsPageIsDisplayed() {
        ProductsPage productsPage = new ProductsPage(CucumberDriverContext.getDriver());
        Assert.assertEquals(productsPage.getProductsTitle(), "Products");
    }
}
