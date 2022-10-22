package com.github.karixdev.environment;

import com.github.karixdev.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {
    private final Properties properties = new Properties();
    private final Properties localProperties = new Properties();

    public Environment(ClassLoader classLoader) throws IOException {
        properties.load(classLoader.getResourceAsStream("application.properties"));
        localProperties.load(classLoader.getResourceAsStream("application.local.properties"));
    }

    public String getLocalVariable(String name) {
        return get(name, localProperties);
    }

    public String getVariable(String name) {
        return get(name, properties);
    }

    private String get(String name, Properties whichFile) {
        if (!whichFile.containsKey(name)) {
            throw new PropertyNotFoundException("Property with name: " + name + " was not found");
        }

        return whichFile.getProperty(name);
    }
}
