package com.github.karixdev.game.coinflip;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.game.BaseGameCommand;
import com.github.karixdev.game.GameCommandValidator;
import com.github.karixdev.game.GameMessagesUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.Random;

public class CoinFlipCommand extends BaseGameCommand {
    public CoinFlipCommand(AccountService accountService) {
        super(accountService);
    }

    @Override
    protected String expectedInput() {
        return "heads/tails credits. Credits must be greater than 0.";
    }

    @Override
    protected int estimateMaximumLoss(String param, int credits) {
        return credits;
    }

    @Override
    protected GameCommandValidator getValidator() {
        return new CoinFlipCommandValidator();
    }

    @Override
    protected void play(MessageReceivedEvent event, String param, int credits, Account account) {
        String[] options = {"heads", "tails"};

        Random random = new Random();
        int winner = Math.abs(random.nextInt() % 2);

        int newCredits = account.getCredits();

        if (options[winner].equals(param)) {
            GameMessagesUtils.sendWinMessage(event.getChannel(), event.getAuthor(), credits);
            newCredits += credits;
        } else {
            GameMessagesUtils.sendLossMessage(event.getChannel(), event.getAuthor(), credits);
            newCredits -= credits;
        }

        account.setCredits(newCredits);
    }
}
