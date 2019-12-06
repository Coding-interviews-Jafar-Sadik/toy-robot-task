package org.toyrobot.math;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    UNKNOWN;

    public static Direction parse(String direction) {
        try {
            return Direction.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
