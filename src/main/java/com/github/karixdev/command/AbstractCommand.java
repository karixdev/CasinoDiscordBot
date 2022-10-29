package com.github.karixdev.command;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractCommand {
    protected final AccountService accountService;

    protected final Account account;
    protected final List<String> params;

    public abstract void execute(MessageReceivedEvent event);
    public abstract String getTemplateCommand();
}
