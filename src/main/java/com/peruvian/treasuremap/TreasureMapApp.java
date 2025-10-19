package com.peruvian.treasuremap;

import com.peruvian.treasuremap.application.Processor;
import com.peruvian.treasuremap.domain.model.TreasureMap;
import com.peruvian.treasuremap.domain.service.AdventurerService;
import com.peruvian.treasuremap.domain.service.TreasureMapService;
import com.peruvian.treasuremap.iomanager.FileReaderFactory;
import com.peruvian.treasuremap.iomanager.TreasureMapParser;
import com.peruvian.treasuremap.iomanager.TreasureMapWriter;

import java.io.BufferedReader;
import java.io.IOException;

public class TreasureMapApp {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java TreasureMapApp <input-file> <output-file>");
            System.exit(1);
        }
        new TreasureMapApp().launch(args[0], args[1]);
    }

    public void launch(String inputFile, String outputFile) {
        try (BufferedReader input = FileReaderFactory.createReader(inputFile)) {
            TreasureMap treasureMap = TreasureMapParser.parse(input);
            Processor processor = new Processor(treasureMap, new TreasureMapService(), new AdventurerService());
            processor.runTurns();
            TreasureMapWriter.writeOutput(treasureMap, outputFile);
            System.out.println("Simulation complete. Output written to: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error processing simulation: " + e.getMessage());
        }
    }
}
