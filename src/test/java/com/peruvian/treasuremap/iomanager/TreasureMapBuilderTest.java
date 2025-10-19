package com.peruvian.treasuremap.iomanager;

import com.peruvian.treasuremap.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TreasureMapBuilderTest {

    private TreasureMapBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new TreasureMapBuilder();
    }

    @Test
    void build_shouldCreateTreasureMap() {
        Map map = new Map(3, 4);
        Adventurer adventurer = new Adventurer("Lara", new Coordinates(1, 1), Orientation.SOUTH, "A", 0);
        builder.setMap(map);
        builder.addAdventurer(adventurer);

        TreasureMap treasureMap = builder.build();

        assertNotNull(treasureMap);
        assertEquals(map, treasureMap.getMap());
        assertEquals(Collections.singletonList(adventurer), treasureMap.getAdventurers());
    }

    @Test
    void build_shouldThrowExceptionIfNoAdventurers() {
        builder.setMap(new Map(3, 4));

        Exception exception = assertThrows(IllegalStateException.class, () -> builder.build());
        assertEquals("At least one adventurer must be defined to build a TreasureMap.", exception.getMessage());
    }

    @Test
    void build_shouldThrowExceptionIfNoMap() {
        Adventurer adventurer = new Adventurer("Lara", new Coordinates(1, 1), Orientation.SOUTH, "A", 0);
        builder.addAdventurer(adventurer);

        Exception exception = assertThrows(IllegalStateException.class, () -> builder.build());
        assertEquals("A map must be defined to build a TreasureMap.", exception.getMessage());
    }
}