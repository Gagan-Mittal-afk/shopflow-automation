package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage extends BasePage {

    private By finishButton = By.id("finish");
    private By confirmationMessage = By.className("complete-header");

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinish() {
        waitForClickable(finishButton);
        driver.findElement(finishButton).click();
    }

    public String getConfirmationMessage() {
        waitForVisible(confirmationMessage);
        return driver.findElement(confirmationMessage).getText();
    }
}