package org.toyrobot.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.toyrobot.math.Direction.*;
import static org.toyrobot.math.Point2D.point2d;

public class DirectionTest {
    @Test
    public void shouldParseNorth() {
        assertEquals(NORTH, parse("NORTH"));
    }

    @Test
    public void shouldParseSouth() {
        assertEquals(SOUTH, parse("SOUTH"));
    }

    @Test
    public void shouldParseEast() {
        assertEquals(EAST, parse("EAST"));
    }

    @Test
    public void shouldParseWest() {
        assertEquals(WEST, parse("WEST"));
    }

    @Test
    public void shouldParseMixCaseDirections() {
        assertEquals(WEST, parse("west"));
        assertEquals(NORTH, parse("north"));
    }

    @Test
    public void shouldReturnUnknownDirectionWhenDirectionIsInvalid() {
        assertEquals(UNKNOWN, parse("south-east"));
    }

    @Test
    public void shouldReturnDirectionVector() {
        assertEquals(point2d(0, 1), NORTH.vector);
        assertEquals(point2d(0, -1), SOUTH.vector);
        assertEquals(point2d(-1, 0), WEST.vector);
        assertEquals(point2d(1, 0), EAST.vector);
    }
}