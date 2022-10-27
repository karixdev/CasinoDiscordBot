package com.github.karixdev.game.coinflip;

import com.github.karixdev.validator.Constraint;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CoinFlipConstraint implements Constraint {

    private final String param;

    @Override
    public boolean isValid() {
        return param.equals("heads") || param.equals("tails");
    }
}
