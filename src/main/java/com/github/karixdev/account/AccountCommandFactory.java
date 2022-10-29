package com.github.karixdev.account;

import com.github.karixdev.command.AbstractCommand;
import com.github.karixdev.command.CommandFactory;

import java.util.List;

public class AccountCommandFactory extends CommandFactory {

    public AccountCommandFactory(AccountService accountService) {
        super(accountService);
    }

    @Override
    protected AbstractCommand createCommand(AccountService accountService, Account account, List<String> params) {
        return new AccountCommand(accountService, account, params);
    }
}
