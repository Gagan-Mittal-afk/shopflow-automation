package com.automation.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/** Creates configured local browsers through Selenium Manager. */
public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver create(String browser) {
        if (!"chrome".equalsIgnoreCase(browser)) {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--disable-save-password-bubble");

        Map<String, Object> preferences = new HashMap<>();
        preferences.put("credentials_enable_service", false);
        preferences.put("profile.password_manager_enabled", false);
        preferences.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", preferences);

        return new ChromeDriver(options);
    }
}
