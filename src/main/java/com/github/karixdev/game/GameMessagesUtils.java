package com.github.karixdev.game;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.util.List;

@RequiredArgsConstructor
public class GameMessagesUtils {

    private final Message message;

    private void send(String messageContent) {
        message
                .reply(messageContent)
                .queue();
    }

    private void sendGameResultMessage(int credits, String text, String emojis) {
        StringBuilder messageBuilder = new StringBuilder();

        messageBuilder
                .append(text)
                .append(credits)
                .append(emojis);

        send(messageBuilder.toString());
    }

    public void sendWinMessage(int wonCredits) {
        sendGameResultMessage(wonCredits,
                " Congratulations you have won: ", " credits :partying_face: :champagne_glass:");
    }

    public void sendLossMessage(int lostCredits) {
        sendGameResultMessage(lostCredits,
                " Sorry but you have lost: ", " credits :cry: :face_with_spiral_eyes:");
    }

    public void sendInvalidParamsMessage(Message message, String content) {
        StringBuilder messageBuilder = new StringBuilder();

        messageBuilder
                .append("You have provided wrong parameters. Expected: ")
                .append(content)
                .append(" :face_with_open_eyes_and_hand_over_mouth:");

        message
                .reply(messageBuilder)
                .queue();
    }

    public void sendNotEnoughCreditsMessage(Message message) {
        message
                .reply("You dont have enough credits :pleading_face:")
                .queue();
    }
}
