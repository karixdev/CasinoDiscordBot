package com.github.karixdev.discordbot;

import com.github.karixdev.environment.Environment;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.JDABuilder;

@AllArgsConstructor
public class DiscordBot{
    private final Environment localEnvironment;

    public void run() {
        JDABuilder
                .createDefault(localEnvironment.getLocalVariable("bot.token"))
                .addEventListeners(new DiscordMessageListener())
                .build();
    }
}
