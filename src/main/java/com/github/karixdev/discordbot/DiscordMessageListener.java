package com.github.karixdev.discordbot;

import com.github.karixdev.command.CommandHandler;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

public class DiscordMessageListener extends ListenerAdapter {
    CommandHandler commandHandler = new CommandHandler();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        String[] args = event.getMessage().getContentRaw()
                .toLowerCase()
                .split("\\s+");

        if (isMessageACommand(args[0])) {
            commandHandler.handle(args, event);
        }
    }

    private boolean isMessageACommand(String message) {
        return message.charAt(0) == CommandHandler.COMMAND_PREFIX;
    }
}
