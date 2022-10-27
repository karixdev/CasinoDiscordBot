package com.github.karixdev.command;

import com.github.karixdev.account.Account;
import com.github.karixdev.validator.Constraint;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public interface ICommand {
    void execute(MessageReceivedEvent event);
    String getTemplateCommand();
    List<Constraint> getConstraint();
}
