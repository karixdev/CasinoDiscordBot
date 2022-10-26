package com.github.karixdev.game;

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

    protected abstract void play(MessageReceivedEvent event, String param, int credits);

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

        if (!doesUserHaveEnoughCredits(authorId, credits)) {
            GameMessagesUtils.sendNotEnoughCreditsMessage(event.getMessage());
            return;
        }

        play(event, param, credits);
    }

    private boolean doesUserHaveEnoughCredits(long discordId, int credits) {
        int userCredits = accountService.getCredits(discordId);

        return userCredits > 0 && userCredits <= credits;
    }
}
