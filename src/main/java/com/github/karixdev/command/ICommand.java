package com.github.karixdev.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public interface ICommand {
    void execute(MessageReceivedEvent event, List<String> params);
}
