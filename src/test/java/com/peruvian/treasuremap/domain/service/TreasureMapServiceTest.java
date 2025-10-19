package com.peruvian.treasuremap.domain.service;

import com.peruvian.treasuremap.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreasureMapServiceTest {

    private TreasureMapService service;
    private TreasureMap map;

    @BeforeEach
    void setup() {
        service = new TreasureMapService();

        Map baseMap = new Map(3, 3);
        List<Mountain> mountains = List.of(new Mountain(new Coordinates(1, 1)));
        List<Adventurer> adventurers = List.of(new Adventurer("Test", new Coordinates(0, 0), Orientation.NORTH, "", 0));
        List<Treasure> treasures = List.of(new Treasure(new Coordinates(2, 2), 2));

        map = new TreasureMap(baseMap, mountains, treasures, adventurers);
    }

    @Test
    void canMoveTo_shouldReturnTrueForValidCoordinates() {
        assertTrue(service.canMoveTo(map, new Coordinates(2, 2)));
    }

    @Test
    void canMoveTo_shouldReturnFalseForOutOfBounds() {
        assertFalse(service.canMoveTo(map, new Coordinates(3, 2)));
        assertFalse(service.canMoveTo(map, new Coordinates(-1, 0)));
    }

    @Test
    void canMoveTo_shouldReturnFalseIfMountainOrAdventurerPresent() {
        assertFalse(service.canMoveTo(map, new Coordinates(1, 1)));
        assertFalse(service.canMoveTo(map, new Coordinates(0, 0)));
    }

    @Test
    void canMoveTo_shouldReturnTrueIfFreeAndInsideBounds() {
        assertTrue(service.canMoveTo(map, new Coordinates(2, 1)));
    }

    @Test
    void checkForTreasure_shouldReturnTrueAndDecrementCount() {
        Coordinates treasureSpot = new Coordinates(2, 2);
        assertTrue(service.checkForTreasure(map, treasureSpot));

        Treasure treasure = map.getTreasures().getFirst();
        assertEquals(1, treasure.getQuantity());
    }

    @Test
    void checkForTreasure_shouldReturnFalseIfNoTreasurePresent() {
        assertFalse(service.checkForTreasure(map, new Coordinates(1, 2)));
    }
    @Test
    void collect_shouldDecrementAndReturnTrueWhenAvailable() {
        Treasure treasure = new Treasure(new Coordinates(0, 0), 2);
        assertTrue(service.collect(treasure));
        assertEquals(1, treasure.getQuantity());
    }

    @Test
    void collect_shouldReturnFalseWhenEmpty() {
        Treasure treasure = new Treasure(new Coordinates(0, 0), 0);
        assertFalse(service.collect(treasure));
    }
}
