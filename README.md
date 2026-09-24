# 🛒 ShopFlow Automation

A Java-based Selenium automation framework for testing an e-commerce workflow using Selenium WebDriver, TestNG, Maven, and the Page Object Model (POM).

The project automates a complete shopping flow on SauceDemo, including login, product selection, cart validation, checkout, and order confirmation.


## 🛠️ Tech Stack

• Java 17
• Selenium WebDriver 4.35.0
• TestNG 7.11.0
• Maven
• Google Chrome
• Page Object Model (POM)
• Git & GitHub


## 🎯 Automated Scenarios

### Successful Purchase Flow

Login
  ↓
Products
  ↓
Add Sauce Labs Backpack
  ↓
Cart
  ↓
Checkout
  ↓
Enter Customer Information
  ↓
Checkout Overview
  ↓
Complete Order
  ↓
Verify Order Confirmation


### Invalid Login

Verifies that an appropriate error message is displayed when invalid credentials are used.


## 📂 Project Structure

java-selenium-automation/
│
├── pom.xml
├── testng.xml
├── README.md
├── .gitignore
│
└── src/
    └── test/
        ├── java/
        │   └── com/
        │       └── automation/
        │           ├── base/
        │           │   ├── BasePage.java
        │           │   └── BaseTest.java
        │           │
        │           ├── listeners/
        │           │   └── ScreenshotListener.java
        │           │
        │           ├── pages/
        │           │   ├── CartPage.java
        │           │   ├── CheckoutPage.java
        │           │   ├── LoginPage.java
        │           │   ├── OrderConfirmationPage.java
        │           │   └── ProductsPage.java
        │           │
        │           ├── tests/
        │           │   ├── LoginTest.java
        │           │   └── InvalidLoginTest.java
        │           │
        │           └── utils/
        │               ├── ConfigReader.java
        │               └── ScreenshotUtil.java
        │
        └── resources/
            └── config.properties


## 🏗️ Framework Features

• Page Object Model architecture
• Reusable page classes
• Explicit waits using WebDriverWait
• TestNG assertions
• Configuration using config.properties
• Screenshot capture on test failure
• Maven-based test execution
• Surefire test reports
• Chrome browser automation


## ⚙️ Configuration

Configuration is maintained in:

src/test/resources/config.properties

Example:

browser=chrome
url=https://www.saucedemo.com/


## 🚀 Getting Started

### Prerequisites

Make sure the following are installed:

• Java JDK 17+
• Maven
• Google Chrome
• Git

Verify Java installation:

java -version

Verify Maven installation:

mvn -version


### Clone the Repository

git clone https://github.com/Gagan-Mittal-afk/shopflow-automation.git

Navigate to the project directory:

cd shopflow-automation


### Run the Tests

Execute the complete TestNG suite using Maven:

mvn clean test

A successful execution should produce:

Tests run: 2
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS


## 📊 Test Reports

Maven Surefire generates test reports in:

target/surefire-reports/

Important reports include:

• index.html
• emailable-report.html
• testng-results.xml


## 📸 Failure Screenshots

Screenshots are automatically captured when a test fails.

They are stored in:

screenshots/

The screenshots directory is excluded from Git using .gitignore.


## 🔄 Automation Flow

                    SauceDemo
                        │
                        ▼
                   LoginPage
                        │
                        ▼
                  ProductsPage
                        │
                        ▼
                    CartPage
                        │
                        ▼
                  CheckoutPage
                        │
                        ▼
            OrderConfirmationPage
                        │
                        ▼
                 Order Completed


## 📈 Future Enhancements

• Cross-browser testing
• Data-driven testing
• Parallel test execution
• CI/CD integration
• GitHub Actions
• Additional e-commerce test scenarios
• Advanced test reporting


## 👨‍💻 Author

Gagan Mittal

B.Tech CSE – Data Science


⭐ Built using Java, Selenium WebDriver, TestNG, and Maven.