package com.peruvian.treasuremap.iomanager;

import com.peruvian.treasuremap.domain.model.*;
import com.peruvian.treasuremap.domain.model.Map;

import java.io.*;

public class TreasureMapParser {

    public static TreasureMap parse(BufferedReader br) throws IOException {
        TreasureMapBuilder builder = new TreasureMapBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            String[] tokens = line.split("\\s*-\\s*");
            char type = tokens[0].charAt(0);

            switch (type) {
                case 'C' -> builder.setMap(parseMap(tokens));
                case 'M' -> builder.addMountain(parseMountain(tokens));
                case 'T' -> builder.addTreasure(parseTreasure(tokens));
                case 'A' -> builder.addAdventurer(parseAdventurer(tokens));
                default -> throw(new RuntimeException("Invalid line format: " + line));
            }
        }
        return builder.build();
    }

    private static Map parseMap(String[] tokens) {
        int width = Integer.parseInt(tokens[1]);
        int height = Integer.parseInt(tokens[2]);
        return new Map(width, height);
    }

    private static Mountain parseMountain(String[] tokens) {
        Coordinates coords = new Coordinates(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
        return new Mountain(coords);
    }

    private static Treasure parseTreasure(String[] tokens) {
        Coordinates coords = new Coordinates(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
        int amount = Integer.parseInt(tokens[3]);
        return new Treasure(coords, amount);
    }

    private static Adventurer parseAdventurer(String[] tokens) {
        String name = tokens[1];
        Coordinates coords = new Coordinates(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        Orientation orientation = Orientation.fromChar(tokens[4].charAt(0));
        String path = tokens[5];
        return new Adventurer(name, coords, orientation, path, 0);
    }
}
