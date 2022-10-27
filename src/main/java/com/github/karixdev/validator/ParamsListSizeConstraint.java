package com.github.karixdev.validator;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ParamsListSizeConstraint implements Constraint {

    private final List<String> params;
    private final int expectedSize;

    @Override
    public boolean isValid() {
        return params.size() >= expectedSize;
    }
}
