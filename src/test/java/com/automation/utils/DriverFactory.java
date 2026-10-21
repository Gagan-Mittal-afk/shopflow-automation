package com.automation.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/** Creates configured local browsers through Selenium Manager. */
public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver create(String browser) {
        if ("chrome".equalsIgnoreCase(browser)) {
            return createChromeDriver();
        }

        if ("firefox".equalsIgnoreCase(browser)) {
            return createFirefoxDriver();
        }

        throw new IllegalArgumentException(
                "Browser not supported: " + browser + ". Supported browsers: chrome, firefox"
        );
    }

    private static WebDriver createChromeDriver() {
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

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("signon.rememberSignons", false);
        options.addPreference("credentials.enable-autosignin", false);

        return new FirefoxDriver(options);
    }
}
