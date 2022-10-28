package com.github.karixdev.command;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractCommand implements ICommand {
    protected final AccountService accountService;

    protected final Account account;
    protected final List<String> params;
}
