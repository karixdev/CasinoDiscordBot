package com.github.karixdev.game;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.AbstractCommand;
import com.github.karixdev.command.ICommand;
import com.github.karixdev.validator.Constraint;
import com.github.karixdev.validator.CreditsConstraint;
import com.github.karixdev.validator.EnoughCreditsConstraint;
import com.github.karixdev.validator.ParamsListSizeConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.beans.ConstructorProperties;
import java.util.List;

@Getter
public abstract class BaseGameCommand extends AbstractCommand {

    private final GameDataDto gameDataDto;

    public BaseGameCommand(AccountService accountService, Account account, List<String> params) {
        super(accountService, account, params);
        gameDataDto = new GameDataDtoAdapter(params);
    }

    protected abstract void play(MessageReceivedEvent event, String param, int credits, Account account);

    @Override
    public void execute(MessageReceivedEvent event) {
        GameMessagesUtils gameMessagesUtils = new GameMessagesUtils(event.getMessage());

        int oldCredits = getAccount().getCredits();
        play(event, gameDataDto.getParam(), gameDataDto.getCredits(), getAccount());
        accountService.updateCredits(getAccount(), getAccount().getCredits());
        int difference = getAccount().getCredits() - oldCredits;

        if (difference > 0) {
            gameMessagesUtils.sendWinMessage(difference);
        } else {
            gameMessagesUtils.sendLossMessage(Math.abs(difference));
        }
    }

    @Override
    public List<Constraint> getConstraint() {
        return List.of(
                new ParamsListSizeConstraint(getParams(), 2),
                new CreditsConstraint(gameDataDto.getCredits()),
                new EnoughCreditsConstraint(getAccount(), gameDataDto.getCredits())
        );
    }
}
