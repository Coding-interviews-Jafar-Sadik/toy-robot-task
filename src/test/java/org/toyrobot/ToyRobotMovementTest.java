package org.toyrobot;

import org.junit.Test;
import org.toyrobot.commands.MoveCommand;
import org.toyrobot.commands.PlaceCommand;
import org.toyrobot.test.BaseToyRobotTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.toyrobot.math.Direction.*;
import static org.toyrobot.math.Point2D.point2d;

public class ToyRobotMovementTest extends BaseToyRobotTest {
    private final MoveCommand moveCommand = new MoveCommand();

    @Test
    public void shouldMoveRobotNorthByOneUnit() {
        toyRobot.execute(new PlaceCommand(point2d(0, 0), NORTH));
        toyRobot.execute(moveCommand);
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(0, 1));
    }

    @Test
    public void shouldMoveRobotNorthByTwoUnits() {
        toyRobot.execute(new PlaceCommand(point2d(3, 0), NORTH));
        repeat(idx -> toyRobot.execute(moveCommand), 2);
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(3, 2));
    }

    @Test
    public void shouldMoveRobotSouthByOneUnit() {
        toyRobot.execute(new PlaceCommand(point2d(0, 4), SOUTH));
        toyRobot.execute(moveCommand);
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(0, 3));
    }

    @Test
    public void shouldMoveRobotWestByOneUnit() {
        toyRobot.execute(new PlaceCommand(point2d(4, 0), WEST));
        toyRobot.execute(moveCommand);
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(3, 0));
    }

    @Test
    public void shouldMoveRobotEastByOneUnit() {
        toyRobot.execute(new PlaceCommand(point2d(0, 0), EAST));
        toyRobot.execute(moveCommand);
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(1, 0));
    }

    @Test
    public void shouldMoveRobotEastByFourUnits() {
        toyRobot.execute(new PlaceCommand(point2d(0, 2), EAST));
        repeat(idx -> toyRobot.execute(moveCommand), 4);
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(4, 2));
    }

    @Test
    public void toyRobotShouldNotLeaveTableWhenMovingNorth() {
        toyRobot.execute(new PlaceCommand(point2d(0, 0), NORTH));
        repeat(idx -> toyRobot.execute(moveCommand), 10);
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(0, 4));
    }

    @Test
    public void toyRobotShouldNotLeaveTableWhenMovingSouth() {
        toyRobot.execute(new PlaceCommand(point2d(0, 4), SOUTH));
        repeat(idx -> toyRobot.execute(moveCommand), 10);
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(0, 0));
    }

    @Test
    public void toyRobotShouldNotLeaveTableWhenMovingWest() {
        toyRobot.execute(new PlaceCommand(point2d(4, 0), WEST));
        repeat(idx -> toyRobot.execute(moveCommand), 10);
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(0, 0));
    }

    @Test
    public void toyRobotShouldNotLeaveTableWhenMovingEast() {
        toyRobot.execute(new PlaceCommand(point2d(0, 0), EAST));
        repeat(idx -> toyRobot.execute(moveCommand), 10);
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(4, 0));
    }
}
