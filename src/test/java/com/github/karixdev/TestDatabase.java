package com.github.karixdev;

import com.github.karixdev.database.Database;
import com.github.karixdev.environment.Environment;

import java.io.IOException;

public class TestDatabase {
    public static Database getDatabase() throws IOException {
        return new Database(
                new Environment(Thread.currentThread().getContextClassLoader()));
    }
}
