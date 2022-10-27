package com.github.karixdev.command;

import com.github.karixdev.account.AccountCommandFactory;
import com.github.karixdev.account.AccountService;
import com.github.karixdev.game.coinflip.CoinFlipCommandFactory;
import com.github.karixdev.game.russianroulette.RussianRouletteCommandFactory;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.*;

public class CommandHandler {

    public final static char COMMAND_PREFIX = '!';

    private final AccountService accountService;

    public CommandHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    public void handle(String[] args, MessageReceivedEvent event) {
        String command = args[0].substring(1);

        List<String> params = Arrays.stream(args)
                .filter(arg -> !arg.equals(args[0]))
                .toList();

        executeCertainCommand(command, params, event);
    }

    public void executeCertainCommand(String command, List<String> params, MessageReceivedEvent event) {
        CommandFactory commandFactory = switch (command) {
            case "account" -> new AccountCommandFactory(accountService);
            case "coin-flip" -> new CoinFlipCommandFactory(accountService);
            case "russian" -> new RussianRouletteCommandFactory(accountService);
            default -> null;
        };

        if (commandFactory == null) {
            sendCommandNotFoundMessage(event.getMessage());
        } else {
            commandFactory.process(event, params);
        }
    }

    private void sendCommandNotFoundMessage(Message message) {
        message
                .reply("Command was not found :face_with_monocle:")
                .queue();
    }
}
