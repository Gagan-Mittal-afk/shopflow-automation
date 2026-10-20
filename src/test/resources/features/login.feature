Feature: SauceDemo login
  As a registered SauceDemo shopper
  I want to sign in with valid credentials
  So that I can browse products

  Scenario: Successful login displays the product inventory
    Given the SauceDemo login page is open
    When the user signs in with configured valid credentials
    Then the Products page is displayed
