package com.github.karixdev.game.russianroulette;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.game.BaseGameCommand;
import com.github.karixdev.game.GameCommandValidator;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RussianRouletteCommand extends BaseGameCommand {
    public RussianRouletteCommand(AccountService accountService) {
        super(accountService);
    }

    @Override
    protected String expectedInput() {
        return null;
    }

    @Override
    protected void play(MessageReceivedEvent event, String param, int credits, Account account) {

    }

    @Override
    protected int estimateMaximumLoss(String param, int credits) {
        return 0;
    }

    @Override
    protected GameCommandValidator getValidator() {
        return new RussianRouletteCommandValidator();
    }
}
