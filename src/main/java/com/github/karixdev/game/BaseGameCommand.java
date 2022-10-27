package com.github.karixdev.game;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.ICommand;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

@RequiredArgsConstructor
public abstract class BaseGameCommand implements ICommand {

    protected final AccountService accountService;

    protected abstract String expectedInput();

    protected abstract void play(MessageReceivedEvent event, String param, int credits, Account account);

    protected abstract GameCommandValidator getValidator();

    @Override
    public void execute(Account account, MessageReceivedEvent event, List<String> params) {
        if (params.size() < 2) {
            GameMessagesUtils.sendInvalidParamsMessage(event.getMessage(), expectedInput());
            return;
        }

        String param = params.get(0);
        int credits = Integer.parseInt(params.get(1));

        if (credits <= 0 || !getValidator().isParamValid(param)) {
            GameMessagesUtils.sendInvalidParamsMessage(event.getMessage(), expectedInput());
            return;
        }

        int oldCredits = account.getCredits();

        if (account.getCredits() <= 0 || account.getCredits() < credits) {
            GameMessagesUtils.sendNotEnoughCreditsMessage(event.getMessage());
            return;
        }

        play(event, param, credits, account);
        accountService.updateCredits(account, account.getCredits());
        int difference = account.getCredits() - oldCredits;

        if (difference > 0) {
            GameMessagesUtils.sendWinMessage(event.getChannel(), event.getAuthor(), difference);
        } else {
            GameMessagesUtils.sendLossMessage(event.getChannel(), event.getAuthor(), Math.abs(difference));
        }
    }
}
