package com.github.karixdev.account;

import com.github.karixdev.command.ICommand;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

@RequiredArgsConstructor
public class AccountCommand implements ICommand {

    private final AccountService accountService;

    @Override
    public void execute(Account account, MessageReceivedEvent event, List<String> params) {
        response(event.getMessage(), account.getCredits());
    }

    @Override
    public int expectedParamsCount() {
        return 0;
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
