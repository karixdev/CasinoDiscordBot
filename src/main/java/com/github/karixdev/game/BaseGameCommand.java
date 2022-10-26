package com.github.karixdev.game;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.ICommand;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.nio.channels.Channel;
import java.util.List;

@RequiredArgsConstructor
public abstract class BaseGameCommand implements ICommand {

    protected final AccountService accountService;

    protected abstract String expectedInput();

    protected abstract void play(MessageReceivedEvent event, String param, int credits, Account account);

    @Override
    public void execute(MessageReceivedEvent event, List<String> params) {
        if (params.size() == 0 || params.size() < 3) {
            GameMessagesUtils.sendInvalidParamsMessage(event.getMessage(), expectedInput());
            return;
        }

        String param = params.get(1);
        int credits = Integer.parseInt(params.get(2));
        long authorId = event.getAuthor().getIdLong();

        if (credits <= 0) {
            GameMessagesUtils.sendInvalidParamsMessage(event.getMessage(), expectedInput());
            return;
        }

        Account account = accountService.get(authorId);
        int userCredits = account.getCredits();

        if (!doesUserHaveEnoughCredits(account, credits)) {
            GameMessagesUtils.sendNotEnoughCreditsMessage(event.getMessage());
            return;
        }

        play(event, param, credits, account);
        if (userCredits != account.getCredits()) {
            accountService.updateCredits(account, account.getCredits());
        }
    }

    private boolean doesUserHaveEnoughCredits(Account account, int credits) {
        return account.getCredits() > 0 && account.getCredits() >= credits;
    }
}
