package com.github.karixdev.command;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.validator.Validator;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

@RequiredArgsConstructor
public abstract class CommandFactory {

    protected final AccountService accountService;

    protected abstract ICommand createCommand(AccountService accountService, Account account, List<String> params);

    public void process(MessageReceivedEvent event, List<String> params) {
        long authorId = event.getAuthor().getIdLong();
        Account account = accountService.getAccountIfNotExistsCreateOne(authorId);

        ICommand command = createCommand(accountService, account, params);

        Validator validator = new Validator(command.getConstraints());

        if (validator.isValid()) {
            try {
                command.execute(event);
            } catch (RuntimeException e) {
                onFailure(event.getMessage());
                e.printStackTrace();
            }
        } else {
            event.getMessage()
                    .reply("You have passed wrong data. Expected: " + command.getTemplateCommand())
                    .queue();
        }
    }

    protected void onFailure(Message message) {
        message
                .reply("Something went wrong. Please try again later :exploding_head:")
                .queue();
    }
}
