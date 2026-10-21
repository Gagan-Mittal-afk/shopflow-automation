# ShopFlow Automation

ShopFlow Automation is a Java 17 Selenium automation framework that validates SauceDemo's login, product, cart, checkout, and order-confirmation journeys. It is structured with the Page Object Model and runs its complete TestNG, Cucumber, and REST Assured suite with one Maven command.

## Application under test

SauceDemo: https://www.saucedemo.com/

## Technologies

- Java 17
- Selenium WebDriver 4.35.0 (Selenium Manager supplies the driver)
- TestNG 7.11.0
- Cucumber 7.20.1 / Gherkin
- REST Assured 5.5.5
- ExtentReports 5.1.2
- Maven and Maven Surefire
- Hamcrest
- JSONPath
- Postman
- JSONPlaceholder REST API

## Framework architecture

shopflow-automation/
├── .gitignore
├── pom.xml
├── README.md
├── se-metadata.json
├── testng.xml
│
├── api/
│   └── API_PRACTICE.md
│
├── postman/
│   └── ShopFlow-API-Practice.postman_collection.json
│
└── src/
    ├── main/
    │   └── java/
    │
    └── test/
        ├── java/
        │   └── com/
        │       └── automation/
        │           ├── api/
        │           │   ├── base/
        │           │   │   └── ApiConfig.java
        │           │   ├── models/
        │           │   │   └── Post.java
        │           │   └── tests/
        │           │       └── JsonPlaceholderApiTest.java
        │           │
        │           ├── base/
        │           │   ├── BasePage.java
        │           │   └── BaseTest.java
        │           │
        │           ├── listeners/
        │           │   ├── ExtentReportListener.java
        │           │   ├── ExtentReportManager.java
        │           │   └── ScreenshotListener.java
        │           │
        │           ├── pages/
        │           │   ├── CartPage.java
        │           │   ├── CheckoutPage.java
        │           │   ├── GooglePage.java
        │           │   ├── LoginPage.java
        │           │   ├── NavigationMenu.java
        │           │   ├── OrderConfirmationPage.java
        │           │   ├── ProductDetailsPage.java
        │           │   └── ProductsPage.java
        │           │
        │           ├── runners/
        │           │   └── CucumberTestRunner.java
        │           │
        │           ├── stepdefinitions/
        │           │   ├── CucumberDriverContext.java
        │           │   ├── CucumberHooks.java
        │           │   └── LoginSteps.java
        │           │
        │           ├── tests/
        │           │   ├── CartTest.java
        │           │   ├── CartWorkflowTest.java
        │           │   ├── CheckoutTest.java
        │           │   ├── CheckoutValidationTest.java
        │           │   ├── InvalidLoginTest.java
        │           │   ├── LockedOutLoginTest.java
        │           │   ├── LoginTest.java
        │           │   ├── NavigationTest.java
        │           │   ├── ProductInteractionsTest.java
        │           │   └── ProductsTest.java
        │           │
        │           └── utils/
        │               ├── ConfigReader.java
        │               ├── DriverFactory.java
        │               └── ScreenshotUtil.java
        │
        └── resources/
            ├── config.properties
            └── features/
                └── login.feature

## Automated coverage

- Successful login
- Invalid login
- Locked-out user validation
- Data-driven valid-user login scenarios
- Product inventory validation
- Product sorting
- Product details
- Add to cart
- Remove from cart
- Cart contents
- Continue shopping
- Checkout validation
- Checkout completion
- Order confirmation
- Navigation menu
- Logout

The TestNG tests demonstrate smoke, login, products, cart, checkout, navigation, and regression groups; priorities; parameters; data providers; assertions; and test dependencies.

Each Selenium test creates its own browser session, so browser state is not shared between tests.

## Page Object Model

The UI automation follows the Page Object Model to keep locators and page interactions separate from test logic.

LoginPage
    ↓
ProductsPage
    ↓
ProductDetailsPage
    ↓
CartPage
    ↓
CheckoutPage
    ↓
OrderConfirmationPage

BasePage provides common page-level functionality, while BaseTest manages the TestNG browser lifecycle.

NavigationMenu provides reusable navigation functionality.

## Cucumber / BDD

The project includes a Cucumber BDD login scenario using Gherkin.

Feature:

src/test/resources/features/login.feature

Step definitions:

src/test/java/com/automation/stepdefinitions/

- CucumberDriverContext.java
- CucumberHooks.java
- LoginSteps.java

Runner:

src/test/java/com/automation/runners/CucumberTestRunner.java

The scenario follows the Gherkin structure:

Given
When
Then

## API Testing & Postman

The API practice material is deliberately separate from the UI framework:

- api/API_PRACTICE.md
- postman/ShopFlow-API-Practice.postman_collection.json

The API practice uses JSONPlaceholder: https://jsonplaceholder.typicode.com/, a public fake REST API. Its write operations are simulated and do not modify persistent production data.

The Postman collection demonstrates:

- Collection GET
- Single-resource GET
- Path parameters
- Query parameters
- POST requests
- PUT requests
- DELETE requests
- Request headers
- JSON request payloads
- Response-body checks
- Response-header checks
- Negative 404 testing
- Collection variables
- Postman test scripts

Collection variables:

- {{baseUrl}}
- {{postId}}
- {{userId}}

CRUD operations are demonstrated using:

- Create → POST
- Read → GET
- Update → PUT
- Delete → DELETE

## REST Assured API Automation

The REST Assured TestNG suite automates the same JSONPlaceholder scenarios from Java.

REST Assured uses BDD-style:

given()
    .when()
    .then();

given() prepares the request, when() invokes the HTTP method, and then() verifies the response.

Shared API configuration is provided through:

com.automation.api.base.ApiConfig

The API automation demonstrates:

- Base URI configuration
- Request specifications
- Response specifications
- Path parameters
- Query parameters
- Request logging
- Failure logging
- Status-code assertions
- Response-body assertions
- Response-header assertions
- Hamcrest matchers
- JSONPath extraction
- Serialization
- Deserialization

The Post POJO demonstrates serialization of Java objects into JSON and deserialization of JSON responses into Java objects.

## HTTP status codes

The API practice covers the major HTTP response families:

- 1xx Informational
- 2xx Success
- 3xx Redirection
- 4xx Client Error
- 5xx Server Error

The implemented examples validate:

- 200 OK
- 201 Created
- 404 Not Found

## Authentication

JSONPlaceholder does not provide a suitable authentication workflow for this project, so Basic Authentication and OAuth are not artificially simulated or supplied with invented credentials.

For reference, REST Assured normally supports:

auth().basic(username, password);

for Basic Authentication and:

auth().oauth2(token);

for OAuth 2.0 bearer-token authentication using securely supplied credentials or tokens.

XML payload support is likewise not demonstrated because the public practice API returns JSON.

## Configuration

Runtime configuration is maintained in:

src/test/resources/config.properties

The configuration contains the browser, base URL, and standard SauceDemo test credentials.

The framework supports:

- Chrome
- Firefox

No browser-driver download or local driver path is required; Selenium Manager resolves the appropriate browser driver automatically.

## Prerequisites

- JDK 17
- Maven 3.9+
- Google Chrome and/or Mozilla Firefox
- Git

Verify Java:

java -version

Verify Maven:

mvn -version

## Run the complete suite

Run the complete UI, Cucumber, and REST Assured suite:

mvn clean test

Run the same complete suite in Firefox:

mvn clean test -Dbrowser=firefox

testng.xml is the central batch entry point. It includes the TestNG regression classes, the Cucumber TestNG runner, and the REST Assured tests.

Browser, base URL, and standard SauceDemo credentials are maintained in:

src/test/resources/config.properties

## Parallel execution

Independent Selenium regression classes run in parallel through TestNG using:

parallel="classes"

with three threads.

Each BaseTest instance creates and quits its own WebDriver, so browser sessions are not shared.

Cucumber and REST Assured remain sequential to keep scenario state and calls to the public simulated API stable.

## Reports and screenshots

### TestNG / Surefire reports

target/surefire-reports/

Including:

- index.html
- emailable-report.html
- testng-results.xml

### Cucumber HTML report

target/cucumber-reports/cucumber.html

### Extent HTML report

target/extent-reports/shopflow-test-report.html

### Failure screenshots

screenshots/

Screenshots are captured safely only when a WebDriver instance exists.

Failed Selenium screenshots are attached to the Extent report when available.

The report records:

- Test status
- Duration
- Failures
- TestNG groups
- Execution details

Generated reports, screenshots, IDE settings, and Selenium metadata are excluded from version control where appropriate.

## Test results

The complete Maven suite has been successfully validated with:

24 Tests
24 Passed
0 Failed
0 Errors
0 Skipped

BUILD SUCCESS

The suite includes UI TestNG automation, Cucumber BDD execution, and REST Assured API automation.

## Example automation flow

LoginPage
    ↓
ProductsPage
    ↓
ProductDetailsPage
    ↓
CartPage
    ↓
CheckoutPage
    ↓
OrderConfirmationPage

## Project highlights

- Java 17 automation framework
- Selenium WebDriver
- Page Object Model
- TestNG
- Cucumber / Gherkin
- REST Assured
- Postman
- Maven
- Chrome and Firefox support
- Parallel Selenium execution
- TestNG groups
- Priorities
- Parameters
- DataProviders
- Assertions
- Test dependencies
- ExtentReports
- Failure screenshots
- Surefire reporting
- Reusable driver management
- Centralized configuration
- UI + API automation in one project
- 24/24 verified Maven tests passing

## GitHub

ShopFlow Automation:
https://github.com/Gagan-Mittal-afk/shopflow-automation