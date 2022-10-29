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

    protected abstract AbstractCommand createCommand(AccountService accountService, Account account, List<String> params);

    public void process(MessageReceivedEvent event, List<String> params) {
        long authorId = event.getAuthor().getIdLong();
        Account account = accountService.getAccountIfNotExistsCreateOne(authorId);

        AbstractCommand command = createCommand(accountService, account, params);

        try {
            command.execute(event);
        } catch (NotValidCommandException e) {
            onFailure(event.getMessage(), e.getMessage());
        } catch (RuntimeException e) {
            onFailure(event.getMessage(), "Something went wrong. Please try again later :exploding_head:");
            e.printStackTrace();
        }
    }

    protected void onFailure(Message message, String content) {
        message
                .reply(content)
                .queue();
    }
}
