package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage {

    private By productsTitle = By.className("title");
    private By backpackAddButton = By.id("add-to-cart-sauce-labs-backpack");
    private By backpackRemoveButton = By.id("remove-sauce-labs-backpack");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");
    private By sortDropdown = By.className("product_sort_container");
    private By inventoryItems = By.className("inventory_item");
    private By inventoryPrices = By.className("inventory_item_price");
    private By backpackLink = By.xpath("//a[@id='item_4_title_link']");

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

    public boolean isInventoryDisplayed() {
        waitForVisible(inventoryItems);
        return !driver.findElements(inventoryItems).isEmpty();
    }

    public int getInventoryItemCount() {
        waitForVisible(inventoryItems);
        return driver.findElements(inventoryItems).size();
    }

    public void sortByVisibleText(String sortingOption) {
        waitForVisible(sortDropdown);
        new Select(driver.findElement(sortDropdown)).selectByVisibleText(sortingOption);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(inventoryItems, 0));
    }

    public List<Double> getDisplayedPrices() {
        waitForVisible(inventoryPrices);
        return driver.findElements(inventoryPrices).stream()
                .map(WebElement::getText)
                .map(price -> price.replace("$", ""))
                .map(Double::parseDouble)
                .toList();
    }

    public void openBackpackDetails() {
        waitForClickable(backpackLink);
        driver.findElement(backpackLink).click();
        wait.until(ExpectedConditions.urlContains("inventory-item.html"));
    }

    public int getCartItemCount() {
        return driver.findElements(cartBadge).isEmpty()
                ? 0
                : Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public boolean isBackpackRemoveButtonDisplayed() {
        waitForVisible(backpackRemoveButton);
        return driver.findElement(backpackRemoveButton).isDisplayed();
    }

    public void removeBackpack() {
        waitForClickable(backpackRemoveButton);
        driver.findElement(backpackRemoveButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(backpackRemoveButton));
    }
}
