package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private By productsTitle = By.className("title");
    private By backpackAddButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductsTitle() {
        waitForVisible(productsTitle);
        return driver.findElement(productsTitle).getText();
    }

    public void addBackpackToCart() {
        waitForClickable(backpackAddButton);
        driver.findElement(backpackAddButton).click();
    }

    public void clickCart() {
        waitForClickable(cartIcon);
        driver.findElement(cartIcon).click();
    }
}
