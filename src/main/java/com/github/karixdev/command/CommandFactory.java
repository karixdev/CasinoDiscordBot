package com.github.karixdev.command;

import com.github.karixdev.account.AccountService;
import com.github.karixdev.database.Database;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public abstract class CommandFactory {

    private final AccountService accountService;

    public CommandFactory(AccountService accountService) {
        this.accountService = accountService;
    }

    protected abstract ICommand getCommand(AccountService accountService, MessageReceivedEvent event, List<String> params) throws Exception;

    public void execute(MessageReceivedEvent event, List<String> params) {
        try {
            ICommand command = getCommand(accountService, event, params);
        } catch (Exception e) {
            onFailure(event.getMessage());
        }
    }

    protected void onFailure(Message message) {
        message
                .reply("Something went wrong. Please try again later")
                .queue();
    }
}
