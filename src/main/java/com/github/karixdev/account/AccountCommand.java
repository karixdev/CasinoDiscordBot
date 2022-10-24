package com.github.karixdev.account;

import com.github.karixdev.command.ICommand;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class AccountCommand implements ICommand {

    private final AccountService accountService;

    @Override
    public void execute(MessageReceivedEvent event, List<String> params) {
        int credits = 0;
        int authorId = Integer.parseInt(event.getAuthor().getId());

        try {
            credits = accountService.getCredits(authorId);
        } catch (IllegalArgumentException e) {
            accountService.createAccount(authorId);
            credits = AccountService.DEFAULT_CREDITS;
        } finally {
            response(event.getMessage(), credits);
        }
    }

    private void response(Message message, int credits) {
        message
                .reply("You have: " + credits + " credits :moneybag:")
                .queue();
    }
}
