package com.github.karixdev.command;

public class NotValidCommandException extends RuntimeException {
    public NotValidCommandException(AbstractCommand command) {
        super("Command is not valid. Expected: " + command.getTemplateCommand() + ". Also you must have enough money :wink:");
    }
}
