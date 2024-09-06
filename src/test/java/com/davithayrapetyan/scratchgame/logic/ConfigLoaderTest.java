package com.davithayrapetyan.scratchgame.logic;

import com.davithayrapetyan.scratchgame.data.GameConfig;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ConfigLoaderTest {

    @Test
    void testLoadConfig() throws IOException {
        // Path to the test JSON config file (in src/test/resources)
        String configFilePath = "src/test/resources/config.json";

        // Load the configuration using the ConfigLoader
        GameConfig config = ConfigLoader.loadConfig(configFilePath);

        // Assert basic configuration values
        assertNotNull(config, "Config should not be null");
        assertEquals(4, config.getColumns(), "Columns should be 4");
        assertEquals(4, config.getRows(), "Rows should be 4");

        // Assert symbols configuration
        assertNotNull(config.getSymbols(), "Symbols should not be null");
        assertTrue(config.getSymbols().containsKey("A"), "Symbol A should be present");
        assertEquals(5.0, config.getSymbols().get("A").getRewardMultiplier(), "Symbol A's reward multiplier should be 5.0");
        assertEquals("standard", config.getSymbols().get("A").getType(), "Symbol A's type should be 'standard'");

        // Assert bonus symbol configuration
        assertTrue(config.getSymbols().containsKey("10x"), "Bonus symbol 10x should be present");
        assertEquals(10.0, config.getSymbols().get("10x").getRewardMultiplier(), "Bonus 10x's reward multiplier should be 10");
        assertEquals("multiply_reward", config.getSymbols().get("10x").getImpact(), "Bonus 10x's impact should be 'multiply_reward'");

        // Assert probabilities configuration
        assertNotNull(config.getProbabilities(), "Probabilities should not be null");
        assertNotNull(config.getProbabilities().getStandardSymbols(), "Standard symbols probabilities should not be null");

        // Check a standard symbol probability
        GameConfig.StandardSymbolProbability standardSymbolProbability = config.getProbabilities().getStandardSymbols().get(0);
        assertEquals(0, standardSymbolProbability.getColumn(), "Standard symbol probability column should be 0");
        assertEquals(0, standardSymbolProbability.getRow(), "Standard symbol probability row should be 0");
        assertTrue(standardSymbolProbability.getSymbols().containsKey("A"), "Standard symbol probability should contain symbol A");

        // Assert win combinations configuration
        assertNotNull(config.getWinCombinations(), "Win combinations should not be null");
        assertTrue(config.getWinCombinations().containsKey("same_symbol_3_times"), "Win combination 'same_symbol_3_times' should be present");
        assertEquals(1.0, config.getWinCombinations().get("same_symbol_3_times").getRewardMultiplier(), "Win combination reward multiplier should be 1.0");
        assertEquals("same_symbols", config.getWinCombinations().get("same_symbol_3_times").getWhenCondition(), "Win combination when condition should be 'same_symbols'");
    }
}
