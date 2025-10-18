package com.peruvian.treasuremap.iomanager;

import static org.junit.jupiter.api.Assertions.*;

import com.peruvian.treasuremap.domain.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class TreasureMapWriterTest {

    @TempDir
    Path tempDir;

    @Test
    void writeOutput_writes_correct_file_content() throws IOException {
        TreasureMap treasureMap = getTreasureMap();
        Path outputFile = tempDir.resolve("output.txt");

        TreasureMapWriter.writeOutput(treasureMap, outputFile.toString());

        List<String> lines = Files.readAllLines(outputFile);
        assertEquals(4, lines.size());
        assertEquals("C - 3 - 4", lines.get(0));
        assertEquals("M - 1 - 1", lines.get(1));
        assertEquals("T - 0 - 1 - 2", lines.get(2));
        assertEquals("A - Lara - 1 - 2 - S - 3", lines.get(3));
    }

    @Test
    void writeOutput_creates_empty_file_for_empty_map() throws IOException {
        TreasureMap emptyMap = new TreasureMap(new Map(1, 1), List.of(), List.of(), List.of());
        Path outputFile = tempDir.resolve("empty.txt");

        TreasureMapWriter.writeOutput(emptyMap, outputFile.toString());

        List<String> lines = Files.readAllLines(outputFile);
        assertEquals(1, lines.size());
        assertEquals("C - 1 - 1", lines.getFirst());
    }

    @Test
    void writeOutput_does_not_throw_for_invalid_path() {
        TreasureMap dummyMap = new TreasureMap(new Map(1, 1), List.of(), List.of(), List.of());
        String invalidPath = "/invalid_path/output.txt";

        assertDoesNotThrow(() -> TreasureMapWriter.writeOutput(dummyMap, invalidPath));
    }

    private static TreasureMap getTreasureMap() {
        Map map = new Map(3, 4);
        Mountain mountain = new Mountain(new Coordinates(1, 1));
        Treasure treasure1 = new Treasure(new Coordinates(0, 1), 2);
        Treasure treasure2 = new Treasure(new Coordinates(2, 2), 0); // Should be skipped
        Adventurer adventurer = new Adventurer("Lara", new Coordinates(1, 2), Orientation.SOUTH, "", 3);
        return new TreasureMap(
                map,
                List.of(mountain),
                List.of(treasure1, treasure2),
                List.of(adventurer)
        );
    }
}
