package com.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties;

    public ConfigReader() throws IOException {

        properties = new Properties();

        try (InputStream inputStream = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IOException("config.properties was not found on the test classpath");
            }
            properties.load(inputStream);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
