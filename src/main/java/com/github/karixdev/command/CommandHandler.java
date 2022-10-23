package com.github.karixdev.command;

import com.github.karixdev.account.AccountService;
import com.github.karixdev.database.Database;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.*;

public class CommandHandler {

    public final static char COMMAND_PREFIX = '!';

    private final AccountService accountService;

    private final Map<String, ICommand> commandMap = Map.of();

    public CommandHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    public void handle(String[] args, MessageReceivedEvent event) {
        String command = args[0].substring(1);

        List<String> params = Arrays.stream(args).filter(arg -> !arg.equals(command)).toList();


    }
}
