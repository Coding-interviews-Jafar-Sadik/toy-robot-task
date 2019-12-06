package org.toyrobot.math;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.toyrobot.math.Point2D.point2d;

public class Point2DTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Point2D.class).verify();
    }

    @Test
    public void builderConstructsPoint2d() {
        Point2D point2D = point2d(10, 4);
        assertEquals(10, point2D.x);
        assertEquals(4, point2D.y);
    }

    @Test
    public void toStringTest() {
        assertEquals("Point2D{x=10, y=4}", point2d(10, 4).toString());
    }
}