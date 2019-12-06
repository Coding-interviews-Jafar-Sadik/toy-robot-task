package org.toyrobot.math;

import java.util.List;
import java.util.Map;

import static org.toyrobot.math.Point2D.point2d;

public enum Direction {
    NORTH,
    SOUTH,
    WEST,
    EAST,
    UNKNOWN;

    public Point2D vector() {
        return directionVector.get(this);
    }

    public Direction rotateClockwise() {
        return directionsClockwise.get((directionsClockwise.indexOf(this) + 1) % 4);
    }

    public Direction rotateAntiClockwise() {
        return directionsAntiClockwise.get((directionsAntiClockwise.indexOf(this) + 1) % 4);
    }

    public static Direction parse(String direction) {
        try {
            return Direction.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }

    private static final List<Direction> directionsClockwise = List.of(NORTH, EAST, SOUTH, WEST);
    private static final List<Direction> directionsAntiClockwise = List.of(NORTH, WEST, SOUTH, EAST);
    private static final Map<Direction, Point2D> directionVector = Map.of(
            NORTH, point2d(0, 1),
            SOUTH, point2d(0, -1),
            WEST, point2d(-1, 0),
            EAST, point2d(1, 0)
    );
}
