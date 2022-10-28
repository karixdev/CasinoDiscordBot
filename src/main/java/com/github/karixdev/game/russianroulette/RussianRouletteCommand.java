package com.github.karixdev.game.russianroulette;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.game.BaseGameCommand;
import com.github.karixdev.validator.Constraint;

import java.util.List;
import java.util.Random;

public class RussianRouletteCommand extends BaseGameCommand {

    public RussianRouletteCommand(AccountService accountService, Account account, List<String> params) {
        super(accountService, account, params);
    }

    @Override
    protected void play() {
        int shots = Integer.parseInt(gameDataDto.getParam());

        Random random = new Random();
        int bulletPositionInChamber = random.nextInt(1, 6) + 1;

        int expectedWin = shots * gameDataDto.getCredits();

        if (shots < bulletPositionInChamber) {
            account.setCredits(account.getCredits() + expectedWin);
        } else {
            account.setCredits(account.getCredits() - gameDataDto.getCredits());
        }
    }

    @Override
    public String getTemplateCommand() {
        return "!russian shots_num[0; 5] credits[>0]";
    }

    @Override
    public List<Constraint> getConstraints() {
        super.getConstraints();
        constraints.add(new RussianRouletteConstraint(Integer.parseInt(gameDataDto.getParam())));

        return constraints;
    }
}
