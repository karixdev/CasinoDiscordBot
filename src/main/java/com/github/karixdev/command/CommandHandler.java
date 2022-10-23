package com.github.karixdev.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.*;

public class CommandHandler {

    public final static char COMMAND_PREFIX = '!';

    private final Map<String, ICommand> commandMap = Map.of();

    public void handle(String[] args, MessageReceivedEvent event) {
        String command = args[0].substring(1);

        List<String> params = Arrays.stream(args).filter(arg -> !arg.equals(command)).toList();

        if (!commandMap.containsKey(command)) {
            event.getMessage()
                    .reply("Command \"" + command + "\" does not exist :face_with_monocle:")
                    .queue();
        } else {
            commandMap.get(command).execute(event, params);
        }
    }
}
