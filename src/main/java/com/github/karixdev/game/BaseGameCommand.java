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

    protected void sendWinMessage(MessageChannelUnion channel, User user, int wonCredits) {
        sendGameResultMessage(channel, user, wonCredits,
                " Congratulations you have won: ", " credits :partying_face: :champagne_glass:");
    }

    protected void sendLossMessage(MessageChannelUnion channel, User user, int lostCredits) {
        sendGameResultMessage(channel, user, lostCredits,
                " Sorry but you have lost: ", " credits :cry: :face_with_spiral_eyes:");
    }

    private void sendGameResultMessage(MessageChannelUnion channel, User user, int credits, String message, String emojis) {
        StringBuilder messageBuilder = new StringBuilder();

        messageBuilder
                .append(user.getAsMention())
                .append(message)
                .append(credits)
                .append(emojis);

        channel
                .sendMessage(messageBuilder)
                .queue();
    }

    protected abstract String expectedInput();

    protected void sendInvalidParamsMessage(Message message) {
        StringBuilder messageBuilder = new StringBuilder();

        messageBuilder
                .append("You have provided wrong parameters. Expected: ")
                .append(expectedInput())
                .append(" :face_with_open_eyes_and_hand_over_mouth:");

        message
                .reply(messageBuilder)
                .queue();
    }

    private boolean doesUserHaveEnoughMoney(long discordId, int credits) {
        int usersCredits = accountService.getCredits(discordId);

        return credits > 0 && usersCredits <= credits;
    }
}
