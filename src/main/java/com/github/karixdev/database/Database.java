package com.github.karixdev.database;

import com.github.karixdev.environment.Environment;
import lombok.AllArgsConstructor;

import java.sql.*;

@AllArgsConstructor
public class Database {
    private final Environment environment;

    private Connection establishConnection() throws SQLException {
        return DriverManager.getConnection(
                environment.get("db.url"),
                environment.get("db.user"),
                environment.get("db.password")
        );
    }
}
