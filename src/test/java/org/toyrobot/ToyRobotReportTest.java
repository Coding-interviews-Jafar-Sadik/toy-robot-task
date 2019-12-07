package org.toyrobot;

import org.junit.Test;
import org.toyrobot.test.BaseToyRobotTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@SuppressWarnings("squid:S1192")
public class ToyRobotReportTest extends BaseToyRobotTest {
    @Test
    public void shouldReportPositionAndDirection() {
        toyRobot.execute("place 2,3,east");
        toyRobot.execute("report");
        verify(runtimeMock).print("=> Output: 2,3,EAST");
        verifyNoMoreInteractions(runtimeMock);
    }

    @Test
    public void shouldDiscardReportBeforePlaceCommand() {
        toyRobot.execute("report");
        verifyZeroInteractions(runtimeMock);
    }

    @Test
    public void shouldReportTheSameStateIfInvokedMultipleTimes() {
        toyRobot.execute("place 4,1,south");
        repeat(idx -> toyRobot.execute("report"), 5);
        verify(runtimeMock, times(5)).print("=> Output: 4,1,SOUTH");
        verifyNoMoreInteractions(runtimeMock);
    }

    @Test
    public void shouldReportStateAfterRightCommand() {
        toyRobot.execute("place 2,3,east");
        toyRobot.execute("right");
        toyRobot.execute("report");
        verify(runtimeMock).print("=> Output: 2,3,SOUTH");
        verifyNoMoreInteractions(runtimeMock);
    }

    @Test
    public void shouldReportStateAfterLeftCommand() {
        toyRobot.execute("place 1,3,east");
        toyRobot.execute("left");
        toyRobot.execute("report");
        verify(runtimeMock).print("=> Output: 1,3,NORTH");
        verifyNoMoreInteractions(runtimeMock);
    }

    @Test
    public void shouldReportStateAfterMoveCommand() {
        toyRobot.execute("place 2,3,west");
        toyRobot.execute("move");
        toyRobot.execute("report");
        verify(runtimeMock).print("=> Output: 1,3,WEST");
        verifyNoMoreInteractions(runtimeMock);
    }
}