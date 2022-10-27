package com.github.karixdev.game;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@ToString
public class GameDataDtoAdapter implements GameDataDto {

    private final List<String> params;

    @Override
    public String getParam() {
        try {
            return params.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int getCredits() {
        try {
            return Integer.parseInt(params.get(1));
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }
}
