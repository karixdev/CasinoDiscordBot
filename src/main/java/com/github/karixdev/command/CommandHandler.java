package com.github.karixdev.command;

import com.github.karixdev.account.AccountCommandFactory;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.database.Database;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.*;

public class CommandHandler {

    public final static char COMMAND_PREFIX = '!';

    private final AccountService accountService;
    public CommandHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    public void handle(String[] args, MessageReceivedEvent event) {
        String command = args[0].substring(1);

        List<String> params = Arrays.stream(args).filter(arg -> !arg.equals(command)).toList();

        CommandFactory commandFactory = null;

        if (command.equals("account")) {
            commandFactory = new AccountCommandFactory(accountService);
        }

        if (commandFactory == null) {
            sendCommandNotFoundMessage(event.getMessage());
        } else {
            commandFactory.process(event, params);
        }
    }

    private void sendCommandNotFoundMessage(Message message) {
        message
                .reply("Command was not found")
                .queue();
    }
}
