package com.github.karixdev.account;

import com.github.karixdev.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    private AccountService accountService;

    @BeforeEach
    public void setup() throws IOException {
        accountService = new AccountService(new AccountRepository(TestDatabase.getDatabase()));
    }

    @AfterEach
    public void cleanUpDatabase() throws IOException, SQLException {
        TestDatabase.cleanUp();
    }

    @Test
    public void GivenNotExistingAccountDiscordId_WhenUpdateCredits_ThenThrowsIllegalArgumentException() {
        long id = 11;
        Account account = new Account(11, 1000);

        assertThrows(IllegalArgumentException.class, () -> accountService.updateCredits(account, 1237));
    }

    @Test
    public void GivenExistingAccountDiscordId_WhenUpdateCredits_ThenReturnsUpdatedAccount() {
        long id = 11;
        Account account = new Account(11, 1000);
        accountService.createAccount(id);

        Account result = accountService.updateCredits(account, 1237);

        account.setCredits(1237);
        assertEquals(account, result);
    }
}
