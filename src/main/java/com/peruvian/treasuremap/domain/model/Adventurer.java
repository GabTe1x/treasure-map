package com.peruvian.treasuremap.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Adventurer {
    private final String name;
    private Coordinates coordinates;
    private Orientation orientation;
    private String movements;
    private int treasuresFound;
}