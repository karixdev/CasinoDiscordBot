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
        accountService = new AccountService(TestDatabase.getDatabase());
    }

    @AfterEach
    public void cleanUpDatabase() throws IOException, SQLException {
        TestDatabase.cleanUp();
    }

    @Test
    public void GivenDiscordId_WhenCreateAccount_ThenDoesNotThrowSqlException() {
        assertDoesNotThrow(() -> accountService.createAccount(11));
    }

    @Test
    public void GivenDiscordId_WhenUpdateCredits_ThenReturnsValidCredits() throws SQLException {
        int id = 11;
        accountService.createAccount(id);

        int result = accountService.updateCredits(id, 1237);

        assertEquals(1237, result);
    }

    @Test
    public void GivenDiscordIdFromNonExistingAccount_WhenGetCredits_ThenReturnDefaultCredits() throws SQLException {
        // given
        int id = 11;

        // when
        int result = accountService.getCredits(id);

        // then
        assertEquals(AccountService.DEFAULT_CREDITS, result);
    }

    @Test
    public void GivenDiscordIdFromExistingAccount_WhenGetCredits_ThenReturnValidCredits() throws SQLException {
        // given
        int id = 11;
        accountService.createAccount(id);
        accountService.updateCredits(id, 1237);

        // when
        int result = accountService.getCredits(id);

        // then
        assertEquals(1237, result);
    }
}