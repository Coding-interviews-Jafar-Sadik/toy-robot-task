package org.toyrobot.math;

import static org.toyrobot.math.Point2D.point2d;

public enum Direction {
    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    UNKNOWN(0, 0);

    Direction(int dx, int dy) {
        this.vector = point2d(dx, dy);
    }

    public final Point2D vector;

    public static Direction parse(String direction) {
        try {
            return Direction.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
