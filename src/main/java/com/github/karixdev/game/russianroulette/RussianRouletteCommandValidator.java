package com.github.karixdev.game.russianroulette;

import com.github.karixdev.game.GameCommandValidator;

public class RussianRouletteCommandValidator implements GameCommandValidator {
    @Override
    public boolean isParamValid(String param) {
        int shots = Integer.parseInt(param);

        return shots <= 6 && shots > 0;
    }
}
