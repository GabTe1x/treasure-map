package com.peruvian.treasuremap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class TreasureMapAppTest {

    @TempDir
    Path tempDir;

    @Test
    void main_shouldRunASimulationAndWriteResultInOutput() throws IOException {
        Path input = tempDir.resolve("input.txt");
        Path output = tempDir.resolve("output.txt");
        Files.writeString(input, """
                C - 3 - 4
                M - 1 - 0
                M - 2 - 1
                T - 0 - 3 - 2
                T - 1 - 3 - 3
                A - Lara - 1 - 1 - S - AADADAGGA
                """);
        String expected = """
                C - 3 - 4
                M - 1 - 0
                M - 2 - 1
                T - 1 - 3 - 2
                A - Lara - 0 - 3 - S - 3""".trim();

        TreasureMapApp.main(new String[]{input.toString(), output.toString()});

        assertTrue(Files.exists(output));
        String result = Files.readString(output).replace("\r\n", "\n").trim();
        assertEquals(expected, result);
    }
}