package com.github.karixdev.account;

import com.github.karixdev.database.Database;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class AccountRepository {

    private final Database database;

    public void create(Account account) {
        String sql = """
                INSERT INTO account(discord_id, credits)
                VALUES (?, ?)
                """;

        try {
            Connection connection = database.establishConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, account.getDiscordId());
            preparedStatement.setInt(2, account.getCredits());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account selectById(long id) {
        String sql = """
                SELECT *
                FROM account
                WHERE discord_id = ?
                LIMIT 1
                """;

        try {
            Connection connection = database.establishConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);
            preparedStatement.setMaxRows(1);

            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return new Account(rs.getLong("discord_id"), rs.getInt("credits"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Account account) {
        String sql = """
                UPDATE account
                SET credits = ?
                WHERE discord_id = ?
                """;

        try {
            Connection connection = database.establishConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, account.getCredits());
            preparedStatement.setLong(2, account.getDiscordId());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
