package com.github.karixdev.validator;

import com.github.karixdev.account.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnoughCreditsConstraintTest {
    @Test
    public void GivenAccountWithNotEnoughCredits_WhenIsValid_ThenReturnsFalse() {
        // given
        Account account = new Account(123);
        int cost = 2000;

        // when
        Constraint constraint = new EnoughCreditsConstraint(account, cost);
        boolean result = constraint.isValid();

        // then
        assertFalse(result);
    }

    @Test
    public void GivenAccountWithEnoughCredits_WhenIsValid_ThenReturnsTrue() {
        // given
        Account account = new Account(123);
        int cost = 1000;

        // when
        Constraint constraint = new EnoughCreditsConstraint(account, cost);
        boolean result = constraint.isValid();

        // then
        assertTrue(result);
    }
}
