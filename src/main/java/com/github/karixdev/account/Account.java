package com.github.karixdev.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    public final static int DEFAULT_CREDITS = 1000;

    private long discordId;
    private int credits = DEFAULT_CREDITS;

    public Account(long discordId) {
        this.discordId = discordId;
    }
}
