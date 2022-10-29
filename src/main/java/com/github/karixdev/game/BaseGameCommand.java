package com.github.karixdev.game;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.AbstractCommand;
import com.github.karixdev.command.NotValidCommandException;
import com.github.karixdev.validator.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseGameCommand extends AbstractCommand implements Verifiable {

    protected final GameDataDto gameDataDto;

    protected List<Constraint> constraints = new ArrayList<>();

    private final Validator validator;

    public BaseGameCommand(AccountService accountService, Account account, List<String> params) {
        super(accountService, account, params);
        gameDataDto = new GameDataDtoAdapter(params);
        validator = new Validator(this);
    }

    protected abstract void play();

    @Override
    public void execute(MessageReceivedEvent event) {
        GameMessagesUtils gameMessagesUtils = new GameMessagesUtils(event.getMessage());

        if (!validator.isValid()) {
            throw new NotValidCommandException(this);
        }

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
