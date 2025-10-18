package com.peruvian.treasuremap.iomanager;

import com.peruvian.treasuremap.domain.model.*;

import java.io.*;

public class TreasureMapWriter {

    public static void writeOutput(TreasureMap map, String outputFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writeMap(writer, map);
            writeMountains(writer, map);
            writeTreasures(writer, map);
            writeAdventurers(writer, map);
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }

    private static void writeMap(BufferedWriter writer, TreasureMap map) throws IOException {
        writer.write(String.format("C - %d - %d", map.getMap().getWidth(), map.getMap().getHeight()));
        writer.newLine();
    }

    private static void writeMountains(BufferedWriter writer, TreasureMap map) throws IOException {
        for (Mountain m : map.getMountains()) {
            writer.write(String.format("M - %d - %d", m.getCoordinates().x(), m.getCoordinates().y()));
            writer.newLine();
        }
    }

    private static void writeTreasures(BufferedWriter writer, TreasureMap map) throws IOException {
        for (Treasure t : map.getTreasures()) {
            if (t.getQuantity() > 0) {
                writer.write(String.format("T - %d - %d - %d",
                        t.getCoordinates().x(),
                        t.getCoordinates().y(),
                        t.getQuantity()));
                writer.newLine();
            }
        }
    }

    private static void writeAdventurers(BufferedWriter writer, TreasureMap map) throws IOException {
        for (Adventurer a : map.getAdventurers()) {
            writer.write(String.format(
                    "A - %s - %d - %d - %s - %d",
                    a.getName(),
                    a.getCoordinates().x(),
                    a.getCoordinates().y(),
                    a.getOrientation().toString().charAt(0),
                    a.getTreasuresFound()
            ));
            writer.newLine();
        }
    }
}
