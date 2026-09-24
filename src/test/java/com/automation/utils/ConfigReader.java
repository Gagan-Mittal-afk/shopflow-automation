package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    public ConfigReader() throws IOException {

        properties = new Properties();

        FileInputStream file = new FileInputStream(
                "src/test/resources/config.properties"
        );

        properties.load(file);
        file.close();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
