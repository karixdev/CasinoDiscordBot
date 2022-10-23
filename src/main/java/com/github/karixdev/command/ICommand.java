package com.github.karixdev.command;

import com.github.karixdev.account.AccountService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.SQLException;
import java.util.List;

public interface ICommand {
    void execute() throws SQLException;
}
