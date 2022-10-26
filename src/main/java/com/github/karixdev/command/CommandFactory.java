package com.github.karixdev.command;

import com.github.karixdev.account.AccountService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

@RequiredArgsConstructor
public abstract class CommandFactory {

    protected final AccountService accountService;

    protected abstract ICommand createCommand(AccountService accountService, MessageReceivedEvent event, List<String> params);

    public void process(MessageReceivedEvent event, List<String> params) {
        ICommand command = createCommand(accountService, event, params);

        try {
            command.execute(event, params);
        } catch (RuntimeException e) {
            onFailure(event.getMessage());
            e.printStackTrace();
        }
    }

    protected void onFailure(Message message) {
        message
                .reply("Something went wrong. Please try again later :exploding_head:")
                .queue();
    }
}
