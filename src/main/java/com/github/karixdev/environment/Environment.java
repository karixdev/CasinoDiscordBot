package com.github.karixdev.environment;

import com.github.karixdev.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {
    private final Properties properties = new Properties();

    public Environment(InputStream propertiesFileInputStream) throws IOException {
        properties.load(propertiesFileInputStream);
    }

    public String get(String name) {
        if (properties.containsKey(name)) {
            throw new PropertyNotFoundException("Property with name: " + name + " was not found");
        }

        return properties.getProperty(name);
    }
}
