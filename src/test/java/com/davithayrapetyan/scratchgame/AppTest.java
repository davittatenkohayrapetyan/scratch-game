package com.davithayrapetyan.scratchgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testAppWithValidArguments() throws Exception {
        // Setup - create a mock config.json file
        String configFilePath = "src/test/resources/test-config.json";
        Path configFilePathObj = Paths.get(configFilePath);

        // Ensure test config file exists
        assertTrue(Files.exists(configFilePathObj));

        // Simulate command line arguments
        String[] args = {"--config", configFilePath, "--betting-amount", "100"};

        // Run the App with the arguments
        App.main(args);

        // Verify output or check for specific results
        String output = outContent.toString();
        assertTrue(output.contains("GameResult"), "Expected output to contain 'GameResult'");
        assertFalse(output.contains("Usage"), "Usage should not be printed for valid arguments");
    }

    @Test
    void testAppWithInvalidArguments() {
        // Simulate invalid command line arguments
        String[] args = {"--config", "invalid-file", "--betting-amount", "100"};

        // Run the App with the invalid arguments
        App.main(args);

        // Verify output contains "Configuration file not found" and usage instructions
        String output = outContent.toString();
        assertTrue(output.contains("Configuration file not found"), "Expected error message for missing configuration file");
        assertTrue(output.contains("Usage: java -jar <your-jar-file>.jar"), "Expected usage instructions to be printed");
    }

    @Test
    void testAppWithoutEnoughArguments() {
        // Simulate missing arguments
        String[] args = {"--config", "config.json"};

        // Run the App with missing arguments
        App.main(args);

        // Verify the output contains usage instructions
        String output = outContent.toString();
        assertTrue(output.contains("Usage: java -jar <your-jar-file>.jar"), "Expected usage instructions for missing arguments");
    }
}
