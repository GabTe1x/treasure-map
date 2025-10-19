package com.peruvian.treasuremap.domain.service;

import com.peruvian.treasuremap.domain.model.Adventurer;
import com.peruvian.treasuremap.domain.model.Coordinates;
import com.peruvian.treasuremap.domain.utils.OrientationUtils;

public class AdventurerService {

    public boolean hasNextMove(Adventurer adventurer) {
        return !adventurer.getMovements().isEmpty();
    }

    public char popNextMove(Adventurer adventurer) {
        String moves = adventurer.getMovements();
        char next = moves.charAt(0);
        adventurer.setMovements(moves.substring(1));
        return next;
    }

    public Coordinates getNextCoordinates(Adventurer adventurer) {
        return switch (adventurer.getOrientation()) {
            case NORTH -> new Coordinates(adventurer.getCoordinates().x(),
                    adventurer.getCoordinates().y() - 1);
            case SOUTH -> new Coordinates(adventurer.getCoordinates().x(),
                    adventurer.getCoordinates().y() + 1);
            case EAST -> new Coordinates(adventurer.getCoordinates().x() + 1,
                    adventurer.getCoordinates().y());
            case WEST -> new Coordinates(adventurer.getCoordinates().x() - 1,
                    adventurer.getCoordinates().y());
        };
    }

    public void turnLeft(Adventurer adventurer) {
        adventurer.setOrientation(OrientationUtils.turnLeft(adventurer.getOrientation()));
    }

    public void turnRight(Adventurer adventurer) {
        adventurer.setOrientation(OrientationUtils.turnRight(adventurer.getOrientation()));
    }

    public void collectTreasure(Adventurer adventurer) {
        adventurer.setTreasuresFound(adventurer.getTreasuresFound() + 1);
    }
}
