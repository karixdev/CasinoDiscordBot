package com.github.karixdev.account;

import com.github.karixdev.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountService {

    private final Database database;

    private final static int DEFAULT_CREDITS = 1000;

    public AccountService(Database database) {
        this.database = database;
    }

    public void createAccount(int discordId) throws SQLException {
        String sql = """
                INSERT INTO account(discord_id, credits)
                VALUES (?, ?)
                """;

        Connection connection = database.establishConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, discordId);
        preparedStatement.setInt(2, DEFAULT_CREDITS);

        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
    }
}
