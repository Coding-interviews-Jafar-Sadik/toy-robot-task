package org.toyrobot;

import org.junit.Test;
import org.toyrobot.commands.LeftCommand;
import org.toyrobot.commands.PlaceCommand;
import org.toyrobot.commands.RightCommand;
import org.toyrobot.test.BaseToyRobotTest;

import static org.junit.Assert.assertEquals;
import static org.toyrobot.math.Direction.*;
import static org.toyrobot.math.Point2D.point2d;

public class ToyRobotDirectionTest extends BaseToyRobotTest {
    @Test
    public void shouldPlaceToyRobotToFaceNorth() {
        toyRobot.execute(new PlaceCommand(point2d(3, 3), NORTH));
        assertEquals(NORTH, toyRobot.getDirection());
    }

    @Test
    public void shouldPlaceToyRobotToFaceSouth() {
        toyRobot.execute(new PlaceCommand(point2d(3, 3), SOUTH));
        assertEquals(SOUTH, toyRobot.getDirection());
    }

    @Test
    public void shouldPlaceToyRobotToFaceEast() {
        toyRobot.execute(new PlaceCommand(point2d(3, 3), EAST));
        assertEquals(EAST, toyRobot.getDirection());
    }

    @Test
    public void shouldPlaceToyRobotToFaceWest() {
        toyRobot.execute(new PlaceCommand(point2d(3, 3), WEST));
        assertEquals(WEST, toyRobot.getDirection());
    }

    @Test
    public void shouldIgnoreCommandGivenUnknownDirection() {
        assertRobotCommandIgnored(new PlaceCommand(point2d(3, 3), UNKNOWN));
    }

    @Test
    public void shouldRotateRobotClockwiseWithoutChangingItsLocation() {
        toyRobot.execute(new PlaceCommand(point2d(3, 3), NORTH));
        toyRobot.execute(new RightCommand());
        assertEquals(EAST, toyRobot.getDirection());
        assertEquals(point2d(3, 3), toyRobot.getPosition());
    }

    @Test
    public void shouldRotateRobotAntiClockwiseWithoutChangingItsLocation() {
        toyRobot.execute(new PlaceCommand(point2d(3, 3), NORTH));
        toyRobot.execute(new LeftCommand());
        assertEquals(WEST, toyRobot.getDirection());
        assertEquals(point2d(3, 3), toyRobot.getPosition());
    }
}
