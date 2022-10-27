package com.github.karixdev.game.russianrouletee;

import com.github.karixdev.game.russianroulette.RussianRouletteConstraint;
import com.github.karixdev.validator.Constraint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RussianRouletteConstraintTest {
    @Test
    public void GivenShotsNumOutOfRange_WhenIsValid_ThenReturnsFalse() {
        // given
        int num1 = 0;
        int num2 = 6;

        // when
        Constraint constraint1 = new RussianRouletteConstraint(num1);
        Constraint constraint2 = new RussianRouletteConstraint(num2);

        boolean result1 = constraint1.isValid();
        boolean result2 = constraint2.isValid();

        // then
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void GivenShotsNumInRange_WhenIsValid_ThenReturnsTrue() {
        // given
        int num = 3;

        // when
        Constraint constraint = new RussianRouletteConstraint(num);

        boolean result = constraint.isValid();

        // then
        assertTrue(result);
    }
}
