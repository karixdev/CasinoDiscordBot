package com.github.karixdev.account;

import com.github.karixdev.TestDatabase;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;

public class AccountServiceTest {
    private AccountService accountService;

    @BeforeEach
    public void setup() throws IOException {
        accountService = new AccountService(TestDatabase.getDatabase());
    }

    @AfterEach
    public void cleanUpDatabase() throws IOException, SQLException {
        TestDatabase.cleanUp();
    }

    @Test
    public void GivenDiscordId_WhenCreateAccount_ThenDoesNotThrowSqlException() {
        Assertions.assertDoesNotThrow(() -> accountService.createAccount(11));
    }
}
