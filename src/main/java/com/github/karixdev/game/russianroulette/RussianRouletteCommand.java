package com.github.karixdev.game.russianroulette;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.AbstractCommand;
import com.github.karixdev.command.ICommand;
import com.github.karixdev.game.BaseGameCommand;
import com.github.karixdev.game.GameDataDto;
import com.github.karixdev.game.GameDataDtoAdapter;
import com.github.karixdev.validator.Constraint;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.Random;

public class RussianRouletteCommand extends BaseGameCommand {

    public RussianRouletteCommand(AccountService accountService, Account account, List<String> params) {
        super(accountService, account, params);
    }

    @Override
    protected void play(MessageReceivedEvent event, String param, int credits, Account account) {
        int shots = Integer.parseInt(param);

        Random random = new Random();
        int bulletPositionInChamber = random.nextInt(1, 6) + 1;

        int expectedWin = shots * credits;

        if (shots < bulletPositionInChamber) {
            account.setCredits(account.getCredits() + expectedWin);
        } else {
            account.setCredits(account.getCredits() - credits);
        }
    }

    @Override
    public String getTemplateCommand() {
        return "!russian shots_num[0; 5] credits[>0]";
    }

    @Override
    public List<Constraint> getConstraint() {
        return null;
    }
}
