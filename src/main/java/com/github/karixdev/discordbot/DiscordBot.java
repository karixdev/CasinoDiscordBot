package com.github.karixdev.discordbot;

import com.github.karixdev.account.AccountService;
import com.github.karixdev.environment.Environment;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

@AllArgsConstructor
public class DiscordBot{
    private final Environment localEnvironment;
    private final AccountService accountService;

    public void run() {
        JDABuilder
                .createDefault(localEnvironment.getLocalVariable("bot.token"))
                .addEventListeners(new DiscordMessageListener(accountService))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
    }
}
