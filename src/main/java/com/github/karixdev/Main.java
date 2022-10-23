package com.github.karixdev;

import com.github.karixdev.account.AccountService;
import com.github.karixdev.database.Database;
import com.github.karixdev.discordbot.DiscordBot;
import com.github.karixdev.environment.Environment;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        Environment environment = new Environment(classloader);
        Database database = new Database(environment);

        AccountService accountService = new AccountService(database);

        DiscordBot bot = new DiscordBot(environment, accountService);
        bot.run();
    }
}