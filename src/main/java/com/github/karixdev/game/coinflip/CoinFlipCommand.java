package com.github.karixdev.game.coinflip;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.game.BaseGameCommand;
import com.github.karixdev.game.GameCommandValidator;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

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
            newCredits += credits;
        } else {
            newCredits -= credits;
        }

        account.setCredits(newCredits);
    }

    @Override
    public String getTemplateCommand() {
        return "!coin-flip side[heads/tails] credits[>0]";
    }
}
