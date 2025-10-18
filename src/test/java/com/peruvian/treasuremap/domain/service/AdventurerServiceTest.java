package com.peruvian.treasuremap.domain.service;

import com.peruvian.treasuremap.domain.model.Adventurer;
import com.peruvian.treasuremap.domain.model.Coordinates;
import com.peruvian.treasuremap.domain.model.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerServiceTest {

    private AdventurerService service;
    private Adventurer adventurer;

    @BeforeEach
    void setup() {
        service = new AdventurerService();
        adventurer = new Adventurer("Lara", new Coordinates(1, 1), Orientation.NORTH, "AAD", 0);
    }

    @Test
    void hasNextMove_shouldReturnTrueWhenMovesLeft() {
        assertTrue(service.hasNextMove(adventurer));
    }

    @Test
    void nextMove_shouldConsumeFirstMove() {
        char move = service.nextMove(adventurer);
        assertEquals('A', move);
        assertEquals("AD", adventurer.getMovements());
    }

    @Test
    void getNextCoordinates_shoulMoveNorth() {
        Coordinates next = service.getNextCoordinates(adventurer);
        assertEquals(new Coordinates(1, 0), next);
    }

    @Test
    void getNextCoordinates_shouldMoveSouth() {
        adventurer.setOrientation(Orientation.SOUTH);
        Coordinates next = service.getNextCoordinates(adventurer);
        assertEquals(new Coordinates(1, 2), next);
    }

    @Test
    void getNextCoordinates_shouldMoveEast() {
        adventurer.setOrientation(Orientation.EAST);
        Coordinates next = service.getNextCoordinates(adventurer);
        assertEquals(new Coordinates(2, 1), next);
    }

    @Test
    void getNextCoordinates_shouldMoveWest() {
        adventurer.setOrientation(Orientation.WEST);
        Coordinates next = service.getNextCoordinates(adventurer);
        assertEquals(new Coordinates(0, 1), next);
    }

    @Test
    void turnLeft_shouldChangeOrientation() {
        service.turnLeft(adventurer);
        assertEquals(Orientation.WEST, adventurer.getOrientation());
    }

    @Test
    void turnRight_shouldChangeOrientation() {
        service.turnRight(adventurer);
        assertEquals(Orientation.EAST, adventurer.getOrientation());
    }

    @Test
    void collectTreasure_shouldIncreaseCount() {
        service.collectTreasure(adventurer);
        assertEquals(1, adventurer.getTreasuresFound());
    }
}
