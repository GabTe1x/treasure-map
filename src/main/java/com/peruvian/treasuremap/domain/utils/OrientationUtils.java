package com.peruvian.treasuremap.domain.utils;

import com.peruvian.treasuremap.domain.model.Orientation;

public class OrientationUtils {

    public static Orientation turnLeft(Orientation orientation) {
        return switch (orientation) {
            case NORTH -> Orientation.WEST;
            case WEST  -> Orientation.SOUTH;
            case SOUTH -> Orientation.EAST;
            case EAST  -> Orientation.NORTH;
        };
    }

    public static Orientation turnRight(Orientation orientation) {
        return switch (orientation) {
            case NORTH -> Orientation.EAST;
            case EAST  -> Orientation.SOUTH;
            case SOUTH -> Orientation.WEST;
            case WEST  -> Orientation.NORTH;
        };
    }
}
