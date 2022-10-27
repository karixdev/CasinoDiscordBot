package com.github.karixdev.validator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParamsListSizeConstraintTest {
    @Test
    public void GivenParamsListWithSmallerSizeThanExpected_WhenIsValid_ThenReturnsFalse() {
        // given
        List<String> params = List.of();
        int expected = 2;

        // when
        Constraint constraint = new ParamsListSizeConstraint(params, expected);
        boolean result = constraint.isValid();

        // then
        assertFalse(result);
    }

    @Test
    public void GivenParamsListWithExpectedSize_WhenIsValid_ThenReturnsTrue() {
        // given
        List<String> params = List.of("param1", "param2");
        int expected = 2;

        // when
        Constraint constraint = new ParamsListSizeConstraint(params, expected);
        boolean result = constraint.isValid();

        // then
        assertTrue(result);
    }

    @Test
    public void GivenParamsListWithBiggerSizeThanExpected_WhenIsValid_ThenReturnsTrue() {
        // given
        List<String> params = List.of("param1", "param2");
        int expected = 1;

        // when
        Constraint constraint = new ParamsListSizeConstraint(params, expected);
        boolean result = constraint.isValid();

        // then
        assertTrue(result);
    }
}
