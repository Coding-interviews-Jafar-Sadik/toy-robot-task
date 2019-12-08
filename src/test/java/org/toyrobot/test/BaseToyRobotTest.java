package org.toyrobot.test;

import org.toyrobot.ToyRobot;
import org.toyrobot.commands.AbstractCommand;
import org.toyrobot.math.Direction;
import org.toyrobot.math.Point2D;

import java.util.function.Consumer;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class BaseToyRobotTest {
    protected final ToyRobot toyRobot = new ToyRobot();
    private Point2D prevPosition;
    private Direction prevDirection;

    protected void assertRobotCommandIgnored(AbstractCommand robotCommand) throws AssertionError {
        saveRobotState(toyRobot);
        toyRobot.execute(robotCommand);
        assertFalse("Expected robot command to be ignored: " + robotCommand, hasRobotStateChanged(toyRobot));
    }

    protected void assertRobotCommandExecuted(AbstractCommand robotCommand) throws AssertionError {
        saveRobotState(toyRobot);
        toyRobot.execute(robotCommand);
        assertTrue("Expected robot command to be executed: " + robotCommand, hasRobotStateChanged(toyRobot));
    }

    protected void repeat(Consumer<Object> fun, int times) {
        IntStream.range(0, times).forEach(fun::accept);
    }

    private void saveRobotState(ToyRobot toyRobot) {
        this.prevPosition = toyRobot.getPosition();
        this.prevDirection = toyRobot.getDirection();
    }

    private boolean hasRobotStateChanged(ToyRobot toyRobot) {
        return !prevPosition.equals(toyRobot.getPosition()) || !prevDirection.equals(toyRobot.getDirection());
    }
}
