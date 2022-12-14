package com.github.karixdev.game.coinflip;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.game.BaseGameCommand;
import com.github.karixdev.validator.Constraint;

import java.util.List;
import java.util.Random;

public class CoinFlipCommand extends BaseGameCommand {

    public CoinFlipCommand(AccountService accountService, Account account, List<String> params) {
        super(accountService, account, params);
    }

    @Override
    protected void play() {
        String[] options = {"heads", "tails"};

        Random random = new Random();
        int winner = Math.abs(random.nextInt() % 2);

        int newCredits = account.getCredits();

        if (options[winner].equals(gameDataDto.getParam())) {
            newCredits += gameDataDto.getCredits();
        } else {
            newCredits -= gameDataDto.getCredits();
        }

        account.setCredits(newCredits);
    }

    @Override
    public String getTemplateCommand() {
        return "!coin-flip side[heads/tails] credits[>0]";
    }

    @Override
    public List<Constraint> getConstraints() {
        super.getConstraints();
        constraints.add(new CoinFlipConstraint(gameDataDto.getParam()));

        return constraints;
    }
}
