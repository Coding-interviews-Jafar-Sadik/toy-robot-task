package org.toyrobot.test;

import org.toyrobot.ToyRobot;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class BaseToyRobotTest {
    protected final ToyRobot toyRobot = new ToyRobot();

    protected void assertRobotCommandIgnored(String robotCommand) throws AssertionError {
        assertFalse("Expected robot command to be ignored: " + robotCommand, toyRobot.execute(robotCommand));
    }

    protected void assertRobotCommandExecuted(String robotCommand) throws AssertionError {
        assertTrue("Expected robot command to be executed: " + robotCommand, toyRobot.execute(robotCommand));
    }

    protected void repeat(Supplier<Object> fun, int times) {
        IntStream.range(0, times).forEach(it -> fun.get());
    }
}
