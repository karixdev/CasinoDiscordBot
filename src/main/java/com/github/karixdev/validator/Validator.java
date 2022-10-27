package com.github.karixdev.validator;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Validator {
    private final List<Constraint> constraints;

    public boolean isValid() {
        return constraints.stream()
                .filter(Constraint::isValid)
                .toList()
                .size() == constraints.size();
    }
}
