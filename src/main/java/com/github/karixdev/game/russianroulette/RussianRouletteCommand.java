package com.github.karixdev.game.russianroulette;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.AbstractCommand;
import com.github.karixdev.command.ICommand;
import com.github.karixdev.game.BaseGameCommand;
import com.github.karixdev.game.GameDataDto;
import com.github.karixdev.game.GameDataDtoAdapter;
import com.github.karixdev.game.coinflip.CoinFlipConstraint;
import com.github.karixdev.validator.Constraint;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RussianRouletteCommand extends BaseGameCommand {

    public RussianRouletteCommand(AccountService accountService, Account account, List<String> params) {
        super(accountService, account, params);
    }

    @Override
    protected void play() {
        int shots = Integer.parseInt(getGameDataDto().getParam());

        Random random = new Random();
        int bulletPositionInChamber = random.nextInt(1, 6) + 1;

        int expectedWin = shots * getGameDataDto().getCredits();

        if (shots < bulletPositionInChamber) {
            getAccount().setCredits(getAccount().getCredits() + expectedWin);
        } else {
            getAccount().setCredits(getAccount().getCredits() - getGameDataDto().getCredits());
        }
    }

    @Override
    public String getTemplateCommand() {
        return "!russian shots_num[0; 5] credits[>0]";
    }

    @Override
    public List<Constraint> getConstraints() {
        List<Constraint> list = new LinkedList<>(super.getConstraints());
        list.add(new RussianRouletteConstraint(Integer.parseInt(getGameDataDto().getParam())));

        return list;
    }
}
