package com.automation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    private static final DateTimeFormatter TIMESTAMP_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private ScreenshotUtil() {
    }

    public static String captureScreenshot(WebDriver driver, String testName) {

        if (!(driver instanceof TakesScreenshot)) {
            return null;
        }

        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        String safeTestName = testName.replaceAll("[^a-zA-Z0-9.-]", "_");

        String folderPath = "screenshots";
        String filePath = folderPath + "/" + safeTestName + "_" + timestamp
                + "_" + UUID.randomUUID() + ".png";

        try {
            Files.createDirectories(Paths.get(folderPath));

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Path destination = Paths.get(filePath);

            Files.copy(
                    source.toPath(),
                    destination
            );

            return destination.toString();

        } catch (IOException | RuntimeException e) {
            return null;
        }
    }
}
