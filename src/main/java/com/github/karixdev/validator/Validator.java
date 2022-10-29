package com.github.karixdev.validator;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Validator {

    private final Verifiable verifiable;

    public boolean isValid() {
        for (Constraint constraint : verifiable.getConstraints()) {
            if (!constraint.isValid()) {
                return false;
            }
        }

        return true;
    }
}
