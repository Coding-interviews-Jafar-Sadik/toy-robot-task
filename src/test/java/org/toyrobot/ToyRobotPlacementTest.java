package org.toyrobot;

import org.junit.Test;
import org.toyrobot.commands.LeftCommand;
import org.toyrobot.commands.PlaceCommand;
import org.toyrobot.commands.UnknownCommand;
import org.toyrobot.test.BaseToyRobotTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.toyrobot.math.Direction.NORTH;
import static org.toyrobot.math.Point2D.point2d;

public class ToyRobotPlacementTest extends BaseToyRobotTest {
    @Test
    public void shouldPlaceRobotInSouthWestCorner() {
        toyRobot.execute(new PlaceCommand(point2d(0, 0), NORTH));
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(0, 0));
    }

    @Test
    public void shouldPlaceRobotInSouthEastCorner() {
        toyRobot.execute(new PlaceCommand(point2d(4, 0), NORTH));
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(4, 0));
    }

    @Test
    public void shouldPlaceRobotInNorthEastCorner() {
        toyRobot.execute(new PlaceCommand(point2d(4, 4), NORTH));
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(4, 4));
    }

    @Test
    public void shouldPlaceRobotInNorthWestCorner() {
        toyRobot.execute(new PlaceCommand(point2d(0, 4), NORTH));
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(0, 4));
    }

    @Test
    public void shouldPreventPlacingRobotOutsideTable() {
        assertRobotCommandIgnored(new PlaceCommand(point2d(5, 5), NORTH));
        assertRobotCommandIgnored(new PlaceCommand(point2d(5, 0), NORTH));
        assertRobotCommandIgnored(new PlaceCommand(point2d(0, 5), NORTH));
    }

    @Test
    public void shouldDiscardAllCommandsBeforePlaceCommand() {
        assertRobotCommandIgnored(new UnknownCommand());
        assertRobotCommandIgnored(new LeftCommand());
        assertRobotCommandExecuted(new PlaceCommand(point2d(3, 3), NORTH));
    }

    @Test
    public void shouldAllowCommandsAfterPlaceCommand() {
        toyRobot.execute(new PlaceCommand(point2d(3, 3), NORTH));
        assertRobotCommandExecuted(new LeftCommand());
        assertRobotCommandExecuted(new PlaceCommand(point2d(1, 4), NORTH));
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(1, 4));
    }
}