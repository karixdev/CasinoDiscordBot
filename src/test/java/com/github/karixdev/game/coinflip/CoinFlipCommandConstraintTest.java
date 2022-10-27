package com.github.karixdev.game.coinflip;

import com.github.karixdev.game.russianroulette.RussianRouletteConstraint;
import com.github.karixdev.validator.Constraint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoinFlipCommandConstraintTest {
    @Test
    public void GivenInvalidParam_WhenIsValid_ThenReturnsFalse() {
        // given
        String param = "abc";

        // when
        Constraint constraint = new CoinFlipConstraint(param);

        boolean result = constraint.isValid();

        // then
        assertFalse(result);
    }

    @Test
    public void GivenShotsNumOutOfRange_WhenIsValid_ThenReturnsFalse() {
        // given
        String param1 = "heads";
        String param2 = "tails";

        // when
        Constraint constraint1 = new CoinFlipConstraint(param1);
        Constraint constraint2 = new CoinFlipConstraint(param2);

        boolean result1 = constraint1.isValid();
        boolean result2 = constraint2.isValid();

        // then
        assertTrue(result1);
        assertTrue(result2);
    }
}
