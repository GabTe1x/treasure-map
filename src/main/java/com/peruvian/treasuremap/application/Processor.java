package com.peruvian.treasuremap.application;

import com.peruvian.treasuremap.domain.model.Adventurer;
import com.peruvian.treasuremap.domain.model.Coordinates;
import com.peruvian.treasuremap.domain.model.TreasureMap;
import com.peruvian.treasuremap.domain.service.AdventurerService;
import com.peruvian.treasuremap.domain.service.TreasureMapService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Processor {

    private final TreasureMap treasureMap;
    private final TreasureMapService treasureMapService;
    private final AdventurerService adventurerService;

    public void runTurns() {
        boolean movesLeft;
        do {
            movesLeft = false;
            for (Adventurer adventurer : treasureMap.getAdventurers()) {
                if (adventurerService.hasNextMove(adventurer)) {
                    movesLeft = true;
                    processMove(adventurer);
                }
            }
        } while (movesLeft);
    }

    private void processMove(Adventurer adventurer) {
        char move = adventurerService.popNextMove(adventurer);
        switch (move) {
            case 'A' -> moveForward(adventurer);
            case 'G' -> adventurerService.turnLeft(adventurer);
            case 'D' -> adventurerService.turnRight(adventurer);
        }
    }

    private void moveForward(Adventurer adventurer) {
        Coordinates next = adventurerService.getNextCoordinates(adventurer);
        if (treasureMapService.canMoveTo(treasureMap, next)) {
            adventurer.setCoordinates(next);
            if (treasureMapService.checkForTreasure(treasureMap, next)) {
                adventurerService.collectTreasure(adventurer);
            }
        }
    }
}
