package com.github.karixdev;

import com.github.karixdev.discordbot.DiscordBot;
import com.github.karixdev.environment.Environment;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        Environment environment = new Environment(classloader);

        DiscordBot bot = new DiscordBot(environment);
        bot.run();
    }
}