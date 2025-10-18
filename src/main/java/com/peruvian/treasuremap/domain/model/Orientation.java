package com.peruvian.treasuremap.domain.model;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Orientation {
    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('O');

    private final char code;

    public static Orientation fromChar(char code) {
        return Arrays.stream(values())
                .filter(orientation -> orientation.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Code d'orientation invalide : " + code));
    }
}
