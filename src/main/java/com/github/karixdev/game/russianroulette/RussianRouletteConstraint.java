package com.github.karixdev.game.russianroulette;

import com.github.karixdev.validator.Constraint;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RussianRouletteConstraint implements Constraint {

    private final int shots;

    @Override
    public boolean isValid() {
        return shots > 0 && shots <= 5;
    }
}
