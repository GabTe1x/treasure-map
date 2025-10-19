package com.peruvian.treasuremap.iomanager;

import com.peruvian.treasuremap.domain.model.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class TreasureMapBuilder {

    @Setter
    private Map map;

    private final List<Mountain> mountains = new ArrayList<>();
    private final List<Treasure> treasures = new ArrayList<>();
    private final List<Adventurer> adventurers = new ArrayList<>();

    public void addMountain(Mountain m) {
        mountains.add(m);
    }

    public void addTreasure(Treasure t) {
        treasures.add(t);
    }

    public void addAdventurer(Adventurer a) {
        adventurers.add(a);
    }

    public TreasureMap build() {
        if (map == null) {
            throw new IllegalStateException("A map must be defined to build a TreasureMap.");
        }
        if (adventurers.isEmpty()) {
            throw new IllegalStateException("At least one adventurer must be defined to build a TreasureMap.");
        }
        return new TreasureMap(map, mountains, treasures, adventurers);
    }
}

