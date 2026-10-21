package com.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Creates the single Maven-generated Extent HTML report for a test run. */
final class ExtentReportManager {

    private static final Path REPORT_PATH = Path.of(
            "target", "extent-reports", "shopflow-test-report.html"
    );
    private static ExtentReports extentReports;

    private ExtentReportManager() {
    }

    static synchronized ExtentReports getExtentReports() {
        if (extentReports == null) {
            try {
                Files.createDirectories(REPORT_PATH.getParent());
            } catch (IOException exception) {
                throw new IllegalStateException("Unable to create report directory", exception);
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH.toString());
            sparkReporter.config().setDocumentTitle("ShopFlow Automation Report");
            sparkReporter.config().setReportName("ShopFlow Test Execution");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Java", System.getProperty("java.version"));
            extentReports.setSystemInfo("Browser", System.getProperty("browser", "chrome"));
        }

        return extentReports;
    }

    static synchronized void flush() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
