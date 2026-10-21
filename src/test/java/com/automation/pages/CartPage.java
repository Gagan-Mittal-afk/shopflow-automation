package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private By cartItem = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");
    private By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    private By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

   public String getCartItemName() {
    waitForVisible(cartItem);
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

    public int getCartItemCount() {
        return driver.findElements(cartItem).size();
    }

    public void removeBackpack() {
        waitForClickable(removeBackpackButton);
        driver.findElement(removeBackpackButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(removeBackpackButton));
    }

    public boolean isCartEmpty() {
        return driver.findElements(cartItem).isEmpty();
    }

    public void continueShopping() {
        waitForClickable(continueShoppingButton);
        driver.findElement(continueShoppingButton).click();
        wait.until(ExpectedConditions.urlContains("inventory.html"));
    }
}
