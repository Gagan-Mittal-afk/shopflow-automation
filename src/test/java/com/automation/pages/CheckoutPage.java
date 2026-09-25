package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        enterText(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        enterText(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {
        enterText(postalCodeField, postalCode);
    }

    public void clickContinue() {
        waitForClickable(continueButton);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                driver.findElement(continueButton)
        );
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
    }

    public void enterCustomerInformation(
            String firstName,
            String lastName,
            String postalCode) {

        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();
    }

    private void enterText(By locator, String value) {
        waitForVisible(locator);

        WebElement field = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                "const input = arguments[0];"
                        + "const value = arguments[1];"
                        + "Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value')"
                        + ".set.call(input, value);"
                        + "input.dispatchEvent(new Event('input', {bubbles: true}));"
                        + "input.dispatchEvent(new Event('change', {bubbles: true}));",
                field,
                value
        );

        wait.until(
                ExpectedConditions.attributeToBe(locator, "value", value)
        );
    }
}
