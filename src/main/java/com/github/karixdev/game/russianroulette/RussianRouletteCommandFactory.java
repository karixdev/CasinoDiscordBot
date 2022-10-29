package com.github.karixdev.game.russianroulette;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.AbstractCommand;
import com.github.karixdev.command.CommandFactory;

import java.util.List;

public class RussianRouletteCommandFactory extends CommandFactory {
    public RussianRouletteCommandFactory(AccountService accountService) {
        super(accountService);
    }

    @Override
    protected AbstractCommand createCommand(AccountService accountService, Account account, List<String> params) {
        return new RussianRouletteCommand(accountService, account, params);
    }
}
