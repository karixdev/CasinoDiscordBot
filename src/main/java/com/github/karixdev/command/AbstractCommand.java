package com.github.karixdev.command;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public abstract class AbstractCommand implements ICommand {
    protected final AccountService accountService;

    private final Account account;
    private final List<String> params;
}
