package com.github.karixdev.game;

import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.CommandFactory;
import com.github.karixdev.game.coinflip.CoinFlipCommandFactory;
import com.github.karixdev.game.russianroulette.RussianRouletteCommandFactory;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;

import java.util.List;

@RequiredArgsConstructor
public class GameCommandFactoryManager {

    private final AccountService accountService;

    public CommandFactory chooseFactory(Message message, List<String> params) {
        List<String> availableGame = List.of("coin-flip", "russian-roulette");

        if (params.size() < 1) {
            GameMessagesUtils.sendChooseGameMessage(message, availableGame);
        }

        String game = params.get(0);

        return switch (game) {
            case "coin-flip" -> new CoinFlipCommandFactory(accountService);
            case "russian-roulette" -> new RussianRouletteCommandFactory(accountService);
            default -> null;
        };
    }
}
