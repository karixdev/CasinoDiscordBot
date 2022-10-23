package com.github.karixdev.account;

import com.github.karixdev.TestDatabase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

public class AccountServiceTest {
    private AccountService accountService;

    @BeforeEach
    public void setup() throws IOException {
        accountService = new AccountService(TestDatabase.getDatabase());
    }

    @AfterAll
    public static void cleanUpDatabase() throws IOException, SQLException {
        TestDatabase.cleanUp();
    }

    @Test
    public void GivenDiscordId_WhenCreateAccount_ThenDoesNotThrowSqlException() {
        Assertions.assertDoesNotThrow(() -> accountService.createAccount(11));
    }
}
