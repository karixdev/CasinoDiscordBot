package com.github.karixdev.account;

import com.github.karixdev.command.CommandFactory;
import com.github.karixdev.command.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class AccountCommandFactory extends CommandFactory {
    public AccountCommandFactory(AccountService accountService) {
        super(accountService);
    }

    @Override
    protected ICommand createCommand(AccountService accountService, MessageReceivedEvent event, List<String> params) {
        return new AccountCommand(accountService);
    }
}
