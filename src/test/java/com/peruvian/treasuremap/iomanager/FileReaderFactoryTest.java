package com.peruvian.treasuremap.iomanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderFactoryTest {

    @TempDir
    Path tempDir;

    @Test
    void createReader_reads_file_content() throws IOException {
        Path tempFile = tempDir.resolve("test-file.txt");
        String content = "line1\nline2";
        Files.writeString(tempFile, content);

        try (BufferedReader reader = FileReaderFactory.createReader(tempFile.toString())) {
            assertEquals("line1", reader.readLine());
            assertEquals("line2", reader.readLine());
            assertNull(reader.readLine());
        }
    }

    @Test
    void createReader_throws_when_file_not_found() {
        String nonExistent = tempDir.resolve("missing.txt").toString();

        assertThrows(IOException.class, () -> FileReaderFactory.createReader(nonExistent));
    }
}
