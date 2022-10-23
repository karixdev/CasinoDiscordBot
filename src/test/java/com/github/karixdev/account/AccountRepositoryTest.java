package com.github.karixdev.account;

import com.github.karixdev.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.sql.SQLException;

public class AccountRepositoryTest {
    private AccountRepository accountRepository;

    @BeforeEach
    public void setup() throws IOException {
        accountRepository = new AccountRepository(TestDatabase.getDatabase());
    }

    @AfterEach
    public void cleanUpDatabase() throws IOException, SQLException {
        TestDatabase.cleanUp();
    }

    @Test
    public void GivenNonExistingId_WhenSelectById_ThenReturnsNull() {
        int id = 11;

        Account result = accountRepository.selectById(id);

        assertNull(result);
    }

    @Test
    public void GivenDiscordId_WhenPersist_ThenPersistsUserToDatabase() {
        Account account = new Account(11, 1000);

        accountRepository.persist(account);

        assertEquals(accountRepository.selectById(11), account);
    }
}
