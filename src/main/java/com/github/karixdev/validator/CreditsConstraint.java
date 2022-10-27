package com.github.karixdev.validator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreditsConstraint implements Constraint {
    private final int credits;


    @Override
    public boolean isValid() {
        return credits > 0;
    }
}
