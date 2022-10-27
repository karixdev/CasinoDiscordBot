package com.github.karixdev.discordbot;

import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.CommandHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class DiscordMessageListener extends ListenerAdapter {
    private final CommandHandler commandHandler;

    public DiscordMessageListener(AccountService accountService) {
        this.commandHandler = new CommandHandler(accountService);
    }

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
