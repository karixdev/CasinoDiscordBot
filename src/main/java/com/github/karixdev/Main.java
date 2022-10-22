package com.github.karixdev;

import com.github.karixdev.discordbot.Bot;
import com.github.karixdev.environment.Environment;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        Environment environment = new Environment(classloader);

        Bot bot = new Bot(environment);
        bot.run();
    }
}