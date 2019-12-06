package org.toyrobot;

import org.junit.Test;
import org.toyrobot.test.BaseToyRobotTest;

public class ToyRobotTest extends BaseToyRobotTest {
    @Test
    public void ignoreUnknownCommand() {
        assertRobotCommandIgnored("jump");
    }

    @Test
    public void ignoreNonParameterizedCommand() {
        assertRobotCommandIgnored("place");
    }

    @Test
    public void ignoreCommandWithWrongParameters() {
        assertRobotCommandIgnored("place 10,4");
    }

    @Test
    public void allowWhitespacesInCommand() {
        assertRobotCommandExecuted("   place    3  ,  3 , south  ");
        assertRobotCommandExecuted("   move   ");
    }

    @Test
    public void allowCommandsWithCapitalLetters() {
        assertRobotCommandExecuted("   PLACE    3  ,  3 , SOUTH  ");
        assertRobotCommandExecuted("   MOVE   ");
    }
}