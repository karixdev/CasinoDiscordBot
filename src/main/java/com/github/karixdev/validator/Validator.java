package com.github.karixdev.validator;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Validator {
    private final List<Constraint> constraints;

    public boolean isValid() {
        System.out.println(constraints);
        for (Constraint constraint : constraints) {
            if (!constraint.isValid()) {
                return false;
            }
        }

        return true;
    }
}
