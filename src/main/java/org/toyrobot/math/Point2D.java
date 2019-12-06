package org.toyrobot.math;

import lombok.Value;

@Value
public final class Point2D {
    private final int x;
    private final int y;

    public static Point2D point2d(int x, int y) {
        return new Point2D(x, y);
    }
}
