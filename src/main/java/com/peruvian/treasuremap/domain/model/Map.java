package com.peruvian.treasuremap.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Map {
    private final int width;
    private final int height;
}
