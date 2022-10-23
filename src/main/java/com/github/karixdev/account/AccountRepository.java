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

    public void persist(Account account) {
        String sql = """
                INSERT INTO account(discord_id, credits)
                VALUES (?, ?)
                """;

        try {
            Connection connection = database.establishConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, account.discordId());
            preparedStatement.setInt(2, account.credits());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account selectById(int id) {
        String sql = """
                SELECT *
                FROM account
                WHERE discord_id = ?
                LIMIT 1
                """;

        try {
            Connection connection = database.establishConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.setMaxRows(1);

            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return new Account(rs.getInt("discord_id"), rs.getInt("credits"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
