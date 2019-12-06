package org.toyrobot;

import org.junit.Test;
import org.toyrobot.math.Point2D;

import static org.assertj.core.api.Assertions.assertThat;

public class ToyRobotTest {
    private final ToyRobot toyRobot = new ToyRobot();

    @Test
    public void shouldPlaceRobotInTheSouthWestCorner() {
        toyRobot.execute("place 0, 0, NORTH");
        assertThat(toyRobot.getPosition()).isEqualTo(point2d(0, 0));
    }

    private Point2D point2d(int x, int y) {
        return new Point2D(x, y);
    }
}