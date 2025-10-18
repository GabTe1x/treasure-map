package com.peruvian.treasuremap.iomanager;

import com.peruvian.treasuremap.domain.model.TreasureMap;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class TreasureMapParserTest {

    @Test
    void parse_validInput_parsesEntities() throws Exception {
        String input = String.join("\n",
                "C - 3 - 4",
                "M - 1 - 0",
                "T - 2 - 1 - 3",
                "A - Lara - 1 - 1 - S - AADG",
                "",
                "# This is a comment line"
        );

        TreasureMap result = TreasureMapParser.parse(new BufferedReader(new StringReader(input)));

        assertNotNull(result);
        assertNotNull(result.getMap());
        assertEquals(1, result.getMountains().size());
        assertEquals(1, result.getTreasures().size());
        assertEquals(1, result.getAdventurers().size());
    }

    @Test
    void parse_shouldThrowOnInvalidLine() {
        String invalidInput = "Z - 1 - 2 - 3";
        BufferedReader reader = new BufferedReader(new StringReader(invalidInput));

        assertThrows(RuntimeException.class, () -> TreasureMapParser.parse(reader));
    }
}
