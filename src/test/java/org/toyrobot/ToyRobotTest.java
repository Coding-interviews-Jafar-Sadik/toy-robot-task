package org.toyrobot;

import org.junit.Test;
import org.toyrobot.math.Point2D;

import static org.assertj.core.api.Assertions.assertThat;

public class ToyRobotTest {
    private final ToyRobot toyRobot = new ToyRobot();

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

    public void shouldPreventPlacingRobotOutsideTable() {
        assertThat(toyRobot.execute("place 5,5,NORTH")).isFalse();
        assertThat(toyRobot.execute("place 5,0,NORTH")).isFalse();
        assertThat(toyRobot.execute("place 0,5,NORTH")).isFalse();
    }

    private Point2D point2d(int x, int y) {
        return new Point2D(x, y);
    }
}