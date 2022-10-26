package com.github.karixdev.game;

import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.CommandFactory;
import com.github.karixdev.game.coinflip.CoinFlipCommandFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GameCommandFactoryManager {

    private final AccountService accountService;

    public CommandFactory chooseFactory(List<String> params) {
        if (params.size() < 1) {
            // TODO: send "you have to choose game" message
        }

        if (params.get(0).equals("coinflip")) {
            return new CoinFlipCommandFactory(accountService);
        }

        return null;
    }
}
