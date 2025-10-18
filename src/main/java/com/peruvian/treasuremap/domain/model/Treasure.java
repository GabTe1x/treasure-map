package com.peruvian.treasuremap.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@AllArgsConstructor
public class Treasure {
    private final Coordinates coordinates;
    @Setter
    private int quantity;
}
