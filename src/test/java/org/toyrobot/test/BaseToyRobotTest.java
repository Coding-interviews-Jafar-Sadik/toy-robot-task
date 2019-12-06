package org.toyrobot.test;

import org.toyrobot.ToyRobot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class BaseToyRobotTest {
    protected final ToyRobot toyRobot = new ToyRobot();

    protected void assertRobotCommandIgnored(boolean robotCommandStatus) throws AssertionError {
        assertFalse("Expected robot command to be ignored", robotCommandStatus);
    }

    protected void assertRobotCommandExecuted(boolean robotCommandStatus) throws AssertionError {
        assertTrue("Expected robot command to be executed", robotCommandStatus);
    }
}
