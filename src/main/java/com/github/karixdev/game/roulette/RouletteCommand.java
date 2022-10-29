package com.github.karixdev.game.roulette;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.AbstractCommand;
import com.github.karixdev.game.BaseGameCommand;
import com.github.karixdev.validator.Constraint;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RouletteCommand extends BaseGameCommand {

    private final Random random = new Random();

    public RouletteCommand(AccountService accountService, Account account, List<String> params) {
        super(accountService, account, params);
    }

    @Override
    protected void play() {
        /*
        Probability for each color:
            - red = 48%
            - black = 48%
            - green = 4%
         */

        String color;
        int prob = random.nextInt(0, 101);

        if (prob <= 4) {
            color = "green";
        } else {
            if (prob % 2 == 0) {
                color = "red";
            } else {
                color = "black";
            }
        }

        int multiplier = (color.equals("green")) ? 14 : 2;
        int newCredits;

        if (gameDataDto.getParam().equals(color)) {
            newCredits = account.getCredits() + (multiplier * gameDataDto.getCredits());
        } else {
            newCredits = account.getCredits() - gameDataDto.getCredits();
        }

        account.setCredits(newCredits);
    }

    @Override
    public String getTemplateCommand() {
        return "!roulette color[black/red/green] credits[>0]";
    }

    @Override
    public List<Constraint> getConstraints() {
        constraints.add(new RouletteCommandConstraint(gameDataDto.getParam()));

        return super.getConstraints();
    }
}
