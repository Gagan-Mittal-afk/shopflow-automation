package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        waitForVisible(firstNameField);
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        waitForVisible(lastNameField);
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        waitForVisible(postalCodeField);
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        waitForClickable(continueButton);
        driver.findElement(continueButton).click();
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
}
