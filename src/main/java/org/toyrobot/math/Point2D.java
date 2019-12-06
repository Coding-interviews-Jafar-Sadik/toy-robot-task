package org.toyrobot.math;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public final class Point2D {
    private final int x;
    private final int y;

    public static Point2D point2d(int x, int y) {
        return new Point2D(x, y);
    }

    public Point2D add(Point2D that) {
        return point2d(this.getX() + that.getX(), this.getY() + that.getY());
    }
}
