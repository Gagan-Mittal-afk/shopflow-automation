package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private By cartItem = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartItemName() {
        return driver.findElement(cartItem).getText();
    }

    public void clickCheckout() {
        waitForClickable(checkoutButton);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                driver.findElement(checkoutButton)
        );
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
    }
}
