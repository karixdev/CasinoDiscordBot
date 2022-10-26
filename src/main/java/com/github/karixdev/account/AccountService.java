package com.github.karixdev.account;

import com.github.karixdev.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountService {

    private final AccountRepository repository;

    public final static int DEFAULT_CREDITS = 1000;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public void createAccount(long discordId) {
        Account account = new Account(discordId, DEFAULT_CREDITS);

        repository.create(account);
    }

    public Account updateCredits(Account account, int newCredits) {
        if (repository.selectById(account.getDiscordId()) == null) {
            throw new IllegalArgumentException("Could not find account with that id");
        }

        account.setCredits(newCredits);
        repository.update(account);

        return account;
    }

    public int getCredits(long discordId) {
        Account account = repository.selectById(discordId);

        if (account == null) {
            throw new IllegalArgumentException("Could not find account with that id");
        }

        return account.getCredits();
    }

    public Account get(long discordId) {
        return repository.selectById(discordId);
    }
}
