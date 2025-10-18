package com.peruvian.treasuremap.domain.service;

import com.peruvian.treasuremap.domain.model.Coordinates;
import com.peruvian.treasuremap.domain.model.Treasure;
import com.peruvian.treasuremap.domain.model.TreasureMap;

public class TreasureMapService {

    public boolean canMoveTo(TreasureMap map, Coordinates c) {
        return isInsideBounds(map, c) && !isOccupied(map, c);
    }

    public boolean checkForTreasure(TreasureMap map, Coordinates coordinates) {
        return map.getTreasures().stream()
                .filter(treasure -> treasure.getCoordinates().equals(coordinates) && treasure.getQuantity() > 0)
                .findFirst()
                .map(this::collect)
                .orElse(false);
    }

    private boolean isOccupied(TreasureMap map, Coordinates coordinates) {
        return isMountainAt(map, coordinates) || isAdventurerAt(map, coordinates);
    }

    private boolean isMountainAt(TreasureMap map, Coordinates coordinates) {
        return map.getMountains().stream().anyMatch(mountain -> mountain.getCoordinates().equals(coordinates));
    }

    private boolean isAdventurerAt(TreasureMap map, Coordinates coordinates) {
        return map.getAdventurers().stream().anyMatch(adventurer -> adventurer.getCoordinates().equals(coordinates));
    }

    private boolean isInsideBounds(TreasureMap map, Coordinates c) {
        int width = map.getMap().getWidth();
        int height = map.getMap().getHeight();
        return c.x() >= 0 && c.y() >= 0 && c.x() < width && c.y() < height;
    }

    public boolean collect(Treasure treasure) {
        if (treasure.getQuantity() > 0) {
            treasure.setQuantity(treasure.getQuantity() - 1);
            return true;
        }
        return false;
    }
}
