package com.github.karixdev.game;

import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.CommandFactory;
import com.github.karixdev.game.coinflip.CoinFlipCommandFactory;
import com.github.karixdev.game.russianroulette.RussianRouletteCommandFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GameCommandFactoryManager {

    private final AccountService accountService;

    public CommandFactory chooseFactory(List<String> params) {
        if (params.size() < 1) {
            // TODO: send "you have to choose game" message
        }

        String game = params.get(0);

        return switch (game) {
            case "coin-flip" -> new CoinFlipCommandFactory(accountService);
            case "russian-roulette" -> new RussianRouletteCommandFactory(accountService);
            default -> null;
        };
    }
}
