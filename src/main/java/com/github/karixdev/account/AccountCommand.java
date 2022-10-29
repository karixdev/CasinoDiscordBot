package com.github.karixdev.account;

import com.github.karixdev.command.AbstractCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class AccountCommand extends AbstractCommand {

    public AccountCommand(AccountService accountService, Account account, List<String> params) {
        super(accountService, account, params);
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        response(event.getMessage(), account.getCredits());
    }

    @Override
    public String getTemplateCommand() {
        return "!account";
    }

    private void response(Message message, int credits) {
        message
                .reply("You have: " + credits + " credits :moneybag:")
                .queue();
    }
}
