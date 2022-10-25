package com.github.karixdev.game;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

public class GameMessagesUtils {

    private static void sendGameResultMessage(MessageChannelUnion channel, User user, int credits, String message, String emojis) {
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

    public static void sendWinMessage(MessageChannelUnion channel, User user, int wonCredits) {
        sendGameResultMessage(channel, user, wonCredits,
                " Congratulations you have won: ", " credits :partying_face: :champagne_glass:");
    }

    public static void sendLossMessage(MessageChannelUnion channel, User user, int lostCredits) {
        sendGameResultMessage(channel, user, lostCredits,
                " Sorry but you have lost: ", " credits :cry: :face_with_spiral_eyes:");
    }
}
