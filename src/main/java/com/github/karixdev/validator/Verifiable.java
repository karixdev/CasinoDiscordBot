package com.github.karixdev.validator;

import java.util.List;

public interface Verifiable {
    List<Constraint> getConstraints();
}
