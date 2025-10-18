package com.peruvian.treasuremap.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrientationTest {

    @Test
    void fromChar_shouldReturnCorrectOrientation() {
        assertEquals(Orientation.SOUTH, Orientation.fromChar('S'));
        assertEquals(Orientation.WEST, Orientation.fromChar('O'));
        assertEquals(Orientation.NORTH, Orientation.fromChar('N'));
        assertEquals(Orientation.EAST, Orientation.fromChar('E'));
    }

    @Test
    void fromChar_shouldThrowForInvalidCode() {
        assertThrows(IllegalArgumentException.class, () -> Orientation.fromChar('Z'));
    }
}
