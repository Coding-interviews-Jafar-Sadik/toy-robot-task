package org.toyrobot.math;

import java.util.Objects;

public final class Point2D {
    final int x;
    final int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point2D point2d(int x, int y) {
        return new Point2D(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return x == point2D.x &&
                y == point2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
