package com.github.karixdev.validator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    @Test
    public void GivenListOfConstraintsWhereAtLeastOneReturnsFalse_WhenIsValid_ThenReturnsFalse() {
        // given
        List<Constraint> constraints = List.of(
                new CreditsConstraint(-1),
                new ParamsListSizeConstraint(List.of("a"), 1)
        );

        // when
        Validator validator = new Validator(constraints);
        boolean result = validator.isValid();

        // then
        assertFalse(result);
    }

    @Test
    public void GivenListOfConstraintsWhereAllReturnFalse_WhenIsValid_ThenReturnsFalse() {
        // given
        List<Constraint> constraints = List.of(
                new CreditsConstraint(-1),
                new ParamsListSizeConstraint(List.of("a"), 2)
        );

        // when
        Validator validator = new Validator(constraints);
        boolean result = validator.isValid();

        // then
        assertFalse(result);
    }

    @Test
    public void GivenListOfConstraintsWhereAllReturnTrue_WhenIsValid_ThenReturnsTrue() {
        // given
        List<Constraint> constraints = List.of(
                new CreditsConstraint(100),
                new ParamsListSizeConstraint(List.of("a"), 1)
        );

        // when
        Validator validator = new Validator(constraints);
        boolean result = validator.isValid();

        // then
        assertTrue(result);
    }
}
