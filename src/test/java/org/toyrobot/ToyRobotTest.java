package org.toyrobot;

import org.junit.Test;
import org.toyrobot.test.BaseToyRobotTest;

public class ToyRobotTest extends BaseToyRobotTest {
    @Test
    public void ignoreUnknownCommand() {
        assertRobotCommandIgnored(toyRobot.execute("jump"));
    }

    @Test
    public void ignoreNonParameterizedCommand() {
        assertRobotCommandIgnored(toyRobot.execute("place"));
    }

    @Test
    public void ignoreCommandWithWrongParameters() {
        assertRobotCommandIgnored(toyRobot.execute("place 10,4"));
    }
}