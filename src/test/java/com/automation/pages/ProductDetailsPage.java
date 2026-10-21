package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** Represents an individual SauceDemo inventory-item details view. */
public class ProductDetailsPage extends BasePage {

    private final By productName = By.className("inventory_details_name");
    private final By productPrice = By.className("inventory_details_price");
    private final By addToCartButton = By.cssSelector("button[id^='add-to-cart']");
    private final By backToProductsButton = By.id("back-to-products");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        waitForVisible(productName);
        return driver.findElement(productName).getText();
    }

    public String getProductPrice() {
        waitForVisible(productPrice);
        return driver.findElement(productPrice).getText();
    }

    public void addToCart() {
        waitForClickable(addToCartButton);
        driver.findElement(addToCartButton).click();
    }

    public void returnToProducts() {
        waitForClickable(backToProductsButton);
        driver.findElement(backToProductsButton).click();
        wait.until(ExpectedConditions.urlContains("inventory.html"));
    }
}
