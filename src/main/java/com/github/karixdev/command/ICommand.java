package com.github.karixdev.command;

import com.github.karixdev.account.Account;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public interface ICommand {
    void execute(Account account, MessageReceivedEvent event, List<String> params);
    int expectedParamsCount();
    String getTemplateCommand();
}
