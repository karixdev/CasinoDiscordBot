package com.github.karixdev.game.coinflip;

import com.github.karixdev.game.GameCommandValidator;

public class CoinFlipCommandValidator implements GameCommandValidator {
    @Override
    public boolean isParamValid(String param) {
        return param.equals("heads") || param.equals("tails");
    }
}
