package org.toyrobot;

import org.junit.Test;
import org.toyrobot.test.BaseToyRobotTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.toyrobot.math.Point2D.point2d;

public class ToyRobotPlacementTest extends BaseToyRobotTest {
    @Test
    public void shouldPlaceRobotInSouthWestCorner() {
        toyRobot.execute("place 0,0,NORTH");
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(0, 0));
    }

    @Test
    public void shouldPlaceRobotInSouthEastCorner() {
        toyRobot.execute("place 4,0,NORTH");
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(4, 0));
    }

    @Test
    public void shouldPlaceRobotInNorthEastCorner() {
        toyRobot.execute("place 4,4,NORTH");
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(4, 4));
    }

    @Test
    public void shouldPlaceRobotInNorthWestCorner() {
        toyRobot.execute("place 0,4,NORTH");
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(0, 4));
    }

    @Test
    public void shouldPreventPlacingRobotOutsideTable() {
        assertRobotCommandIgnored(toyRobot.execute("place 5,5,NORTH"));
        assertRobotCommandIgnored(toyRobot.execute("place 5,0,NORTH"));
        assertRobotCommandIgnored(toyRobot.execute("place 0,5,NORTH"));
    }

    @Test
    public void shouldDiscardAllCommandsBeforePlaceCommand() {
        assertRobotCommandIgnored(toyRobot.execute("move"));
        assertRobotCommandIgnored(toyRobot.execute("left"));
        assertRobotCommandExecuted(toyRobot.execute("place 3,3,NORTH"));
    }

    @Test
    public void shouldAllowCommandsAfterPlaceCommand() {
        toyRobot.execute("place 3,3,NORTH");
        assertRobotCommandExecuted(toyRobot.execute("left"));
        assertRobotCommandExecuted(toyRobot.execute("place 1,4,NORTH"));
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(1, 4));
    }
}