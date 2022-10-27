package com.github.karixdev.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditsConstraintTest {
    @Test
    public void GivenNonPositiveCredits_WhenIsValid_ThenReturnFalse() {
        // given
        int credits1 = 0;
        int credits2 = -1;

        // when
        Constraint constraint1 = new CreditsConstraint(credits1);
        Constraint constraint2 = new CreditsConstraint(credits2);

        boolean result1 = constraint1.isValid();
        boolean result2 = constraint2.isValid();

        // then
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void GivenPositiveCredits_WhenIsValid_ThenReturnTrue() {
        // given
        int credits = 100;

        // when
        Constraint constraint = new CreditsConstraint(credits);

        boolean result = constraint.isValid();

        // then
        assertTrue(result);
    }
}
