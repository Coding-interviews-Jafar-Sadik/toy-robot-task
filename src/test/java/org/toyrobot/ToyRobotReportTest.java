package org.toyrobot;

import org.junit.Test;
import org.toyrobot.commands.*;
import org.toyrobot.math.Direction;
import org.toyrobot.test.BaseToyRobotTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.toyrobot.math.Point2D.point2d;

public class ToyRobotReportTest extends BaseToyRobotTest {
    private final ReportCommand reportCommand = new ReportCommand();

    @Test
    public void shouldReportPositionAndDirection() {
        toyRobot.execute(new PlaceCommand(point2d(2, 3), Direction.EAST));
        var response = toyRobot.execute(reportCommand);
        assertThat(response).contains("Output: 2,3,EAST");
    }

    @Test
    public void shouldDiscardReportBeforePlaceCommand() {
        var response = toyRobot.execute(reportCommand);
        assertThat(response).isEmpty();
    }

    @Test
    public void shouldReportTheSameStateIfInvokedMultipleTimes() {
        toyRobot.execute(new PlaceCommand(point2d(4, 1), Direction.SOUTH));
        repeat(idx -> {
            var response = toyRobot.execute(reportCommand);
            assertThat(response).contains("Output: 4,1,SOUTH");
        }, 5);
    }

    @Test
    public void shouldReportStateAfterRightCommand() {
        toyRobot.execute(new PlaceCommand(point2d(2, 3), Direction.EAST));
        toyRobot.execute(new RightCommand());
        var response = toyRobot.execute(reportCommand);
        assertThat(response).contains("Output: 2,3,SOUTH");
    }

    @Test
    public void shouldReportStateAfterLeftCommand() {
        toyRobot.execute(new PlaceCommand(point2d(1, 3), Direction.EAST));
        toyRobot.execute(new LeftCommand());
        var response = toyRobot.execute(reportCommand);
        assertThat(response).contains("Output: 1,3,NORTH");
    }

    @Test
    public void shouldReportStateAfterMoveCommand() {
        toyRobot.execute(new PlaceCommand(point2d(2, 3), Direction.WEST));
        toyRobot.execute(new MoveCommand());
        var response = toyRobot.execute(reportCommand);
        assertThat(response).contains("Output: 1,3,WEST");
    }
}