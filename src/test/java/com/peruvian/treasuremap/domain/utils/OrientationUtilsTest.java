package com.peruvian.treasuremap.domain.utils;

import com.peruvian.treasuremap.domain.model.Orientation;
import org.junit.jupiter.api.Test;

import static com.peruvian.treasuremap.domain.utils.OrientationUtils.turnLeft;
import static com.peruvian.treasuremap.domain.utils.OrientationUtils.turnRight;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrientationUtilsTest {

    @Test
    void turnRight_shouldRotateClockwise() {
        assertEquals(Orientation.EAST, turnRight(Orientation.NORTH));
        assertEquals(Orientation.SOUTH, turnRight(Orientation.EAST));
        assertEquals(Orientation.WEST, turnRight(Orientation.SOUTH));
        assertEquals(Orientation.NORTH, turnRight(Orientation.WEST));
    }

    @Test
    void turnLeft_shouldRotateCounterClockwise() {
        assertEquals(Orientation.WEST, turnLeft(Orientation.NORTH));
        assertEquals(Orientation.SOUTH, turnLeft(Orientation.WEST));
        assertEquals(Orientation.EAST, turnLeft(Orientation.SOUTH));
        assertEquals(Orientation.NORTH, turnLeft(Orientation.EAST));

    }
}