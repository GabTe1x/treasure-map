package com.peruvian.treasuremap.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class TreasureMap {
    private final Map map;
    private final List<Mountain> mountains;
    private final List<Treasure> treasures;
    private final List<Adventurer> adventurers;
}
