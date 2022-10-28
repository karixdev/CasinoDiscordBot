package com.github.karixdev.game;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.AbstractCommand;
import com.github.karixdev.validator.Constraint;
import com.github.karixdev.validator.CreditsConstraint;
import com.github.karixdev.validator.EnoughCreditsConstraint;
import com.github.karixdev.validator.ParamsListSizeConstraint;
import lombok.Getter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseGameCommand extends AbstractCommand {

    protected final GameDataDto gameDataDto;

    protected List<Constraint> constraints = new ArrayList<>();

    public BaseGameCommand(AccountService accountService, Account account, List<String> params) {
        super(accountService, account, params);
        gameDataDto = new GameDataDtoAdapter(params);
    }

    protected abstract void play();

    @Override
    public void execute(MessageReceivedEvent event) {
        GameMessagesUtils gameMessagesUtils = new GameMessagesUtils(event.getMessage());

        int oldCredits = account.getCredits();
        play();
        accountService.updateCredits(account, account.getCredits());
        int difference = account.getCredits() - oldCredits;

        if (difference > 0) {
            gameMessagesUtils.sendWinMessage(difference);
        } else {
            gameMessagesUtils.sendLossMessage(Math.abs(difference));
        }
    }

    @Override
    public List<Constraint> getConstraints() {
        constraints.add(new ParamsListSizeConstraint(params, 2));
        constraints.add(new CreditsConstraint(gameDataDto.getCredits()));
        constraints.add(new EnoughCreditsConstraint(account, gameDataDto.getCredits()));

        return constraints;
    }
}
