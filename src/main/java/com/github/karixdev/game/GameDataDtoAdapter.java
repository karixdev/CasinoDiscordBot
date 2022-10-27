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
        return params.get(0);
    }

    @Override
    public int getCredits() {
        return Integer.parseInt(params.get(1));
    }
}
