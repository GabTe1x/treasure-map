package com.peruvian.treasuremap.application;

import static org.junit.jupiter.api.Assertions.*;

import com.peruvian.treasuremap.domain.model.*;
import com.peruvian.treasuremap.domain.service.*;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    }
}
