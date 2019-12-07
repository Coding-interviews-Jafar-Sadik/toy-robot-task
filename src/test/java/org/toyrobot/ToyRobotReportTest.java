package org.toyrobot;

import org.junit.Test;
import org.toyrobot.test.BaseToyRobotTest;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("squid:S1192")
public class ToyRobotReportTest extends BaseToyRobotTest {
    @Test
    public void shouldReportPositionAndDirection() {
        toyRobot.execute("place 2,3,east");
        var response = toyRobot.execute("report");
        assertThat(response).contains("Output: 2,3,EAST");
    }

    @Test
    public void shouldDiscardReportBeforePlaceCommand() {
        var response = toyRobot.execute("report");
        assertThat(response).isEmpty();
    }

    @Test
    public void shouldReportTheSameStateIfInvokedMultipleTimes() {
        toyRobot.execute("place 4,1,south");
        repeat(idx -> {
            var response = toyRobot.execute("report");
            assertThat(response).contains("Output: 4,1,SOUTH");
        }, 5);
    }

    @Test
    public void shouldReportStateAfterRightCommand() {
        toyRobot.execute("place 2,3,east");
        toyRobot.execute("right");
        var response = toyRobot.execute("report");
        assertThat(response).contains("Output: 2,3,SOUTH");
    }

    @Test
    public void shouldReportStateAfterLeftCommand() {
        toyRobot.execute("place 1,3,east");
        toyRobot.execute("left");
        var response = toyRobot.execute("report");
        assertThat(response).contains("Output: 1,3,NORTH");
    }

    @Test
    public void shouldReportStateAfterMoveCommand() {
        toyRobot.execute("place 2,3,west");
        toyRobot.execute("move");
        var response = toyRobot.execute("report");
        assertThat(response).contains("Output: 1,3,WEST");
    }
}