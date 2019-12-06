package org.toyrobot.math;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class Point2DTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Point2D.class).verify();
    }
}