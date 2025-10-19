package com.peruvian.treasuremap.iomanager;

import java.io.*;

public class FileReaderFactory {
    public static BufferedReader createReader(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }
}
