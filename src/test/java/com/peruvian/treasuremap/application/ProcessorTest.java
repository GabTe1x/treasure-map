package com.peruvian.treasuremap.application;

import com.peruvian.treasuremap.domain.model.*;
import com.peruvian.treasuremap.domain.service.AdventurerService;
import com.peruvian.treasuremap.domain.service.TreasureMapService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {

    @Test
    void processor_shouldMoveAdventurerAndCollectTreasure() {
        TreasureMap map = new TreasureMap(
                new Map(3, 3),
                List.of(),
                List.of(new Treasure(new Coordinates(1, 0), 1)),
                List.of(new Adventurer("Lara", new Coordinates(0, 0), Orientation.EAST, "A", 0)));
        Processor processor = new Processor(map, new TreasureMapService(), new AdventurerService());

        processor.runTurns();

        Adventurer lara = map.getAdventurers().getFirst();
        assertEquals(new Coordinates(1, 0), lara.getCoordinates());
        assertEquals(1, lara.getTreasuresFound());
        assertEquals(0, map.getTreasures().getFirst().getQuantity());
    }

    @Test
    void shouldNotMoveAdventurerWhenMoveIsInvalid() {
        TreasureMap map = new TreasureMap(
                new Map(3, 3),
                List.of(),
                List.of(new Treasure(new Coordinates(1, 0), 1)),
                List.of(new Adventurer("Lara", new Coordinates(0, 0), Orientation.EAST, "Z", 0))
        );
        Processor processor = new Processor(map, new TreasureMapService(), new AdventurerService());

        processor.runTurns();

        Adventurer lara = map.getAdventurers().getFirst();
        assertEquals(new Coordinates(0, 0), lara.getCoordinates());
        assertEquals(0, lara.getTreasuresFound());
    }

    @Test
    void shouldNotMoveAdventurerOutOfBounds() {
        TreasureMap map = new TreasureMap(
                new Map(3, 3),
                List.of(),
                List.of(),
                List.of(new Adventurer("Lara", new Coordinates(0, 0), Orientation.WEST, "A", 0))
        );
        Processor processor = new Processor(map, new TreasureMapService(), new AdventurerService());

        processor.runTurns();

        Adventurer lara = map.getAdventurers().getFirst();
        assertEquals(new Coordinates(0, 0), lara.getCoordinates());
        assertEquals(0, lara.getTreasuresFound());
    }

    @Test
    void shouldMoveAdventurerWhenNoTreasurePresent() {
        TreasureMap map = new TreasureMap(
                new Map(3, 3),
                List.of(),
                List.of(),
                List.of(new Adventurer("Lara", new Coordinates(0, 0), Orientation.EAST, "A", 0))
        );
        Processor processor = new Processor(map, new TreasureMapService(), new AdventurerService());

        processor.runTurns();

        Adventurer lara = map.getAdventurers().getFirst();
        assertEquals(new Coordinates(1, 0), lara.getCoordinates());
        assertEquals(0, lara.getTreasuresFound());
    }
}
