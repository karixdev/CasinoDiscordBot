package com.github.karixdev.environment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EnvironmentTest {

    private Environment environment;

    @BeforeEach
    public void setup() throws IOException {
        environment = new Environment(Thread.currentThread().getContextClassLoader());
    }

    @Test
    public void GivenNotExistingPropertyName_WhenGet_ThenThrowsPropertyNotFoundException() {
        String name = "bar";

        assertThrows(PropertyNotFoundException.class, () -> environment.getVariable(name));
    }

    @Test
    public void GivenExistingPropertyName_WhenGet_ThenReturnsCorrectValue() {
        String name = "foo";

        String result = environment.getVariable(name);

        assertEquals("1", result);
    }
}
