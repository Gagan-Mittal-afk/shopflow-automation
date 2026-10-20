package com.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.automation.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html"},
        monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
