package org.toyrobot;

import org.junit.Test;
import org.toyrobot.test.BaseToyRobotTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.toyrobot.math.Point2D.point2d;

public class ToyRobotMovementTest extends BaseToyRobotTest {
    @Test
    public void shouldMoveRobotNorthByOneUnit() {
        toyRobot.execute("place 0,0,NORTH");
        toyRobot.execute("move");
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(0, 1));
    }

    @Test
    public void shouldMoveRobotNorthByTwoUnits() {
        toyRobot.execute("place 3,0,NORTH");
        toyRobot.execute("move");
        toyRobot.execute("move");
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(3, 2));
    }
}
