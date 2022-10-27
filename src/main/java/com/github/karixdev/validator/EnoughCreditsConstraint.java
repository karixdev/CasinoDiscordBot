package com.github.karixdev.validator;

import com.github.karixdev.account.Account;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnoughCreditsConstraint implements Constraint {

    private final Account account;
    private final int credits;

    @Override
    public boolean isValid() {
        return account.getCredits() > 0 && account.getCredits() >= credits;
    }
}
