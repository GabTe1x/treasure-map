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
            System.err.println("Usage: java TreasureMapApplication <input-file> <output-file>");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try {
            BufferedReader input =FileReaderFactory.createReader(inputFile);
            TreasureMap treasureMap = TreasureMapParser.parse(input);
            Processor processor = new Processor(treasureMap, new TreasureMapService(),  new AdventurerService());
            processor.runTurns();

            TreasureMapWriter.writeOutput(treasureMap, outputFile);

            System.out.println("Simulation complete. Output written to: " + outputFile);
        } catch (IOException e) {
            System.err.println("An error has occurred during the processing of your simulation : "+e.getMessage());
        }
    }
}
