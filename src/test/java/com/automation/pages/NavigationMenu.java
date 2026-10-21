package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** SauceDemo's application navigation menu. */
public class NavigationMenu extends BasePage {

    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public NavigationMenu(WebDriver driver) {
        super(driver);
    }

    public void open() {
        waitForClickable(menuButton);
        driver.findElement(menuButton).click();
        waitForVisible(logoutLink);
    }

    public boolean isLogoutLinkDisplayed() {
        waitForVisible(logoutLink);
        return driver.findElement(logoutLink).isDisplayed();
    }

    public void logout() {
        waitForClickable(logoutLink);
        driver.findElement(logoutLink).click();
    }
}
