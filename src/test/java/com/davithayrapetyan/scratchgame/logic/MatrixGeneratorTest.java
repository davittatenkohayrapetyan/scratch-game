package com.davithayrapetyan.scratchgame.logic;

import com.davithayrapetyan.scratchgame.data.GameConfig;
import com.davithayrapetyan.scratchgame.data.GameConfig.StandardSymbolProbability;
import com.davithayrapetyan.scratchgame.data.GameConfig.BonusSymbolProbability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MatrixGeneratorTest {

    private GameConfig config;

    @BeforeEach
    void setUp() {
        // Setting up a basic GameConfig instance for testing
        Map<String, Integer> symbolProbabilities = new HashMap<>();
        symbolProbabilities.put("A", 1);
        symbolProbabilities.put("B", 2);
        symbolProbabilities.put("C", 3);

        StandardSymbolProbability standardSymbolProbability = new StandardSymbolProbability();
        standardSymbolProbability.setRow(0);
        standardSymbolProbability.setColumn(0);
        standardSymbolProbability.setSymbols(symbolProbabilities);

        List<StandardSymbolProbability> standardSymbols = new ArrayList<>();
        standardSymbols.add(standardSymbolProbability);

        BonusSymbolProbability bonusSymbolProbability = new BonusSymbolProbability();
        bonusSymbolProbability.setSymbols(Map.of("10x", 1, "5x", 2, "+1000", 3, "+500", 4, "MISS", 5));

        GameConfig.Probabilities probabilities = new GameConfig.Probabilities();
        probabilities.setStandardSymbols(standardSymbols);
        probabilities.setBonusSymbols(bonusSymbolProbability);

        config = new GameConfig();
        config.setColumns(3);
        config.setRows(3);
        config.setProbabilities(probabilities);
    }

    @Test
    void testMatrixGenerationSize() {
        List<List<String>> matrix = MatrixGenerator.generateMatrix(config);
        assertEquals(3, matrix.size(), "Matrix should have 3 rows");
        matrix.forEach(row -> assertEquals(3, row.size(), "Each row should have 3 columns"));
    }

    @Test
    void testStandardSymbolGeneration() {
        // Set bonus symbol probabilities to 0 specifically for this test
        config.getProbabilities().getBonusSymbols().setSymbols(Map.of("10x", 0, "5x", 0, "+1000", 0, "+500", 0, "MISS", 0));

        // Generate the matrix
        List<List<String>> matrix = MatrixGenerator.generateMatrix(config);

        // Set of valid standard symbols (only "A", "B", "C", and "MISS")
        Set<String> validSymbols = new HashSet<>(Arrays.asList("A", "B", "C", "MISS"));

        // Check if each generated symbol is within the set of valid standard symbols
        matrix.forEach(row -> row.forEach(symbol -> {
            assertTrue(validSymbols.contains(symbol),
                    "Symbol should be one of the standard symbols or 'MISS', but got: " + symbol);
        }));
    }


    @Test
    void testBonusSymbolPlacement() {
        List<List<String>> matrix = MatrixGenerator.generateMatrix(config);
        long bonusCount = matrix.stream()
                .flatMap(List::stream)
                .filter(symbol -> Arrays.asList("10x", "5x", "+1000", "+500", "MISS").contains(symbol))
                .count();
        assertTrue(bonusCount > 0, "Matrix should contain at least one bonus symbol");
    }

    @Test
    void testBonusSymbolGeneration() {
        String bonusSymbol = MatrixGenerator.generateBonusSymbol(config);
        assertTrue(Arrays.asList("10x", "5x", "+1000", "+500", "MISS").contains(bonusSymbol),
                "Generated bonus symbol should be one of the predefined bonus symbols");
    }
}
