package org.toyrobot.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionTest {
    @Test
    public void shouldParseNorth() {
        assertEquals(Direction.NORTH, Direction.parse("NORTH"));
    }

    @Test
    public void shouldParseSouth() {
        assertEquals(Direction.SOUTH, Direction.parse("SOUTH"));
    }

    @Test
    public void shouldParseEast() {
        assertEquals(Direction.EAST, Direction.parse("EAST"));
    }

    @Test
    public void shouldParseWest() {
        assertEquals(Direction.WEST, Direction.parse("WEST"));
    }

    @Test
    public void shouldParseMixCaseDirections() {
        assertEquals(Direction.WEST, Direction.parse("west"));
        assertEquals(Direction.NORTH, Direction.parse("north"));
    }

    @Test
    public void shouldReturnUnknownDirectionWhenDirectionIsInvalid() {
        assertEquals(Direction.UNKNOWN, Direction.parse("south-east"));
    }
}