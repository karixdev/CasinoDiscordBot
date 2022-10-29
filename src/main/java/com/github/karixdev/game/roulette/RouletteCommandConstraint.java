package com.github.karixdev.game.roulette;

import com.github.karixdev.validator.Constraint;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RouletteCommandConstraint implements Constraint {

    private final String color;

    @Override
    public boolean isValid() {
        return color.equals("red") || color.equals("green") || color.equals("black");
    }
}
