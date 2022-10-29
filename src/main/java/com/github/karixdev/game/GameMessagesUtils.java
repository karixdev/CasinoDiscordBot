package com.github.karixdev.game;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;

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
}
