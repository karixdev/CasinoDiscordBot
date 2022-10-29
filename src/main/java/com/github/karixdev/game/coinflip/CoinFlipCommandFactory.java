package com.github.karixdev.game.coinflip;

import com.github.karixdev.account.Account;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.command.AbstractCommand;
import com.github.karixdev.command.CommandFactory;

import java.util.List;

public class CoinFlipCommandFactory extends CommandFactory {
    public CoinFlipCommandFactory(AccountService accountService) {
        super(accountService);
    }

    @Override
    protected AbstractCommand createCommand(AccountService accountService, Account account, List<String> params) {
        return new CoinFlipCommand(accountService, account, params);
    }
}
