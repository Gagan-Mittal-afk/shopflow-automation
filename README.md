# ShopFlow Automation

ShopFlow Automation is a Java 17 Selenium framework that validates SauceDemo's login, product, cart, checkout, and order-confirmation journeys. It is structured with the Page Object Model and runs its complete TestNG and Cucumber suite with one Maven command.

## Application under test

[SauceDemo](https://www.saucedemo.com/)

## Technologies

- Java 17
- Selenium WebDriver 4.35.0 (Selenium Manager supplies the driver)
- TestNG 7.11.0
- Cucumber 7.20.1 / Gherkin
- Maven and Maven Surefire

## Framework architecture

```
src/test
├── java/com/automation
│   ├── base/             # shared page and TestNG test setup
│   ├── pages/            # reusable SauceDemo page objects
│   ├── tests/            # TestNG regression tests
│   ├── stepdefinitions/  # Cucumber hooks and steps
│   ├── runners/          # Cucumber TestNG runner
│   ├── listeners/        # failure screenshot listener
│   └── utils/            # configuration, driver, and screenshot helpers
└── resources
    ├── config.properties
    └── features/login.feature
```

## Automated coverage

- Successful end-to-end purchase: login, backpack, cart, customer information, and confirmation
- Invalid-login validation
- Data-driven product-page access for `standard_user` and `problem_user`
- Cart validation and checkout confirmation
- A Gherkin login scenario with `Given`, `When`, and `Then`

The TestNG tests demonstrate groups, priorities, parameters, data providers, meaningful assertions, and a safe dependency: inventory validation runs only after valid-user product checks pass. Each test still creates its own browser session, so no browser state is shared.

## Prerequisites

- JDK 17
- Maven 3.9+ available on `PATH`
- Google Chrome

No ChromeDriver download or local driver path is required.

## Run the complete suite

```bash
mvn clean test
```

`testng.xml` is the central batch entry point. It includes every TestNG regression class and the Cucumber TestNG runner. Browser, base URL, and standard SauceDemo credentials are maintained in `src/test/resources/config.properties`.

## Reports and screenshots

- TestNG/Surefire reports: `target/surefire-reports/` (including `index.html`, `emailable-report.html`, and `testng-results.xml`)
- Cucumber HTML report: `target/cucumber-reports/cucumber.html`
- Failure screenshots: `screenshots/`

Screenshots have collision-resistant names and are captured safely only when a driver exists. Generated reports, screenshots, IDE settings, and Selenium metadata are excluded by `.gitignore`.

## Example automation flow

`LoginPage → ProductsPage → CartPage → CheckoutPage → OrderConfirmationPage`
