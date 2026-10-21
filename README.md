# ShopFlow Automation

ShopFlow Automation is a Java 17 Selenium framework that validates SauceDemo's login, product, cart, checkout, and order-confirmation journeys. It is structured with the Page Object Model and runs its complete TestNG and Cucumber suite with one Maven command.

## Application under test

[SauceDemo](https://www.saucedemo.com/)

## Technologies

- Java 17
- Selenium WebDriver 4.35.0 (Selenium Manager supplies the driver)
- TestNG 7.11.0
- Cucumber 7.20.1 / Gherkin
- REST Assured 5.5.5
- ExtentReports 5.1.2
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
- Google Chrome (default) or Mozilla Firefox

No browser-driver download or local driver path is required; Selenium Manager resolves the browser driver.

## Run the complete suite

```bash
mvn clean test
```

Run the same complete suite in Firefox:

```bash
mvn clean test -Dbrowser=firefox
```

`testng.xml` is the central batch entry point. It includes every TestNG regression class, the Cucumber TestNG runner, and the REST Assured tests. Browser, base URL, and standard SauceDemo credentials are maintained in `src/test/resources/config.properties`.

## Reports and screenshots

- TestNG/Surefire reports: `target/surefire-reports/` (including `index.html`, `emailable-report.html`, and `testng-results.xml`)
- Cucumber HTML report: `target/cucumber-reports/cucumber.html`
- Extent HTML report: `target/extent-reports/shopflow-test-report.html`
- Failure screenshots: `screenshots/`

Screenshots have collision-resistant names and are captured safely only when a driver exists. Failed Selenium screenshots are attached to the Extent report when available. The report records test status, duration, failures, and TestNG groups. Generated reports, screenshots, IDE settings, and Selenium metadata are excluded by `.gitignore`.

## Parallel execution

The independent Selenium regression classes run in parallel by TestNG (`parallel="classes"`, three threads). Each `BaseTest` instance creates and quits its own WebDriver, so browser sessions are not shared. Cucumber and REST Assured stay sequential to keep scenario state and calls to the public simulated API stable.

## API Testing & Postman

The API practice material is deliberately separate from the UI framework: see [api/API_PRACTICE.md](api/API_PRACTICE.md) and [postman/ShopFlow-API-Practice.postman_collection.json](postman/ShopFlow-API-Practice.postman_collection.json). It uses [JSONPlaceholder](https://jsonplaceholder.typicode.com/), a public fake REST API. Its write operations are simulated: POST, PUT, and DELETE responses do not change persistent production data.

API testing checks client-to-backend communication without the browser UI. REST generally exposes JSON resources over HTTP; SOAP uses structured XML messages and contract-based web-service conventions. CRUD means Create, Read, Update, and Delete, commonly represented by POST, GET, PUT, and DELETE.

The collection demonstrates collection GET, a single-resource GET with a path parameter (`/posts/{{postId}}`), a filtered GET with a query parameter (`?userId={{userId}}`), JSON request payloads for POST/PUT, request headers, response-body checks, response-header checks, and a negative 404 request. Headers carry request or response metadata; a payload is the JSON body sent with a request.

HTTP status families are `1xx` informational, `2xx` success, `3xx` redirection, `4xx` client error, and `5xx` server error. The examples expect 200, 201, and 404 responses.

To use the collection, open Postman, select **Import**, and choose `postman/ShopFlow-API-Practice.postman_collection.json`. It defines `{{baseUrl}}`, `{{postId}}`, and `{{userId}}` variables; edit their current values at collection level if needed. Run a request individually or use Collection Runner to execute all verification scripts.

## REST Assured API Automation

The REST Assured TestNG suite automates the same JSONPlaceholder scenarios as the Postman collection from Java. REST Assured uses BDD-style `given()`, `when()`, `then()`, and `and()` calls: `given()` prepares the request, `when()` invokes an HTTP method, and `then()` verifies the response.

Shared configuration is in `com.automation.api.base.ApiConfig` as reusable `RequestSpecification` and `ResponseSpecification` builders; they define the base URI, common `Accept` header, and JSON response-header expectation. The tests demonstrate path/query parameters, request and failure logging, status/body/header assertions with Hamcrest, and JSONPath extraction. The `Post` POJO demonstrates serialization (a Java object sent as JSON) and deserialization (a JSON response mapped back to a Java object).

Run all UI, Cucumber, and REST Assured tests together with:

```bash
mvn clean test
```

JSONPlaceholder supports the JSON payload examples used here. It does not provide a safe authentication endpoint, so Basic Auth and OAuth are not simulated or supplied with invented credentials. In REST Assured, Basic Auth is normally configured with `auth().basic(user, password)` and OAuth with `auth().oauth2(token)` using securely supplied credentials or tokens. XML payload support is likewise not demonstrated because this public practice API returns JSON only.

## Example automation flow

`LoginPage → ProductsPage → CartPage → CheckoutPage → OrderConfirmationPage`
