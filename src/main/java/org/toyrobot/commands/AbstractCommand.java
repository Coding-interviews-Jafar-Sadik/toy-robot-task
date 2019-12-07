package org.toyrobot.commands;

import lombok.Getter;

@Getter
public abstract class AbstractCommand {
    private CommandType type;

    protected AbstractCommand(CommandType type) {
        this.type = type;
    }
}
