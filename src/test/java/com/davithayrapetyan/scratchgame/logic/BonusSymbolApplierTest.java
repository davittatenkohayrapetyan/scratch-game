package com.davithayrapetyan.scratchgame.logic;

import com.davithayrapetyan.scratchgame.data.GameConfig;
import com.davithayrapetyan.scratchgame.data.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BonusSymbolApplierTest {

    private GameConfig config;
    private List<List<String>> matrix;

    @BeforeEach
    void setUp() {
        // Mock symbols
        Symbol standardSymbolA = new Symbol();
        standardSymbolA.setType("standard");

        Symbol bonusSymbol10x = new Symbol();
        bonusSymbol10x.setType("bonus");

        Symbol standardSymbolB = new Symbol();
        standardSymbolB.setType("standard");

        Map<String, Symbol> symbolsMap = new HashMap<>();
        symbolsMap.put("A", standardSymbolA);
        symbolsMap.put("10x", bonusSymbol10x);
        symbolsMap.put("B", standardSymbolB);

        config = new GameConfig();
        config.setSymbols(symbolsMap);

        // Create a sample matrix with standard and bonus symbols
        matrix = new ArrayList<>();
        matrix.add(Arrays.asList("A", "B", "10x"));
        matrix.add(Arrays.asList("B", "A", "B"));
        matrix.add(Arrays.asList("A", "A", "A"));
    }

    @Test
    void testApplyBonusSymbol_Found() {
        // Test for the presence of the bonus symbol "10x"
        String bonusSymbol = BonusSymbolApplier.applyBonusSymbol(matrix, config);
        assertEquals("10x", bonusSymbol, "The bonus symbol should be '10x'");
    }

    @Test
    void testApplyBonusSymbol_NotFound() {
        // Test with a matrix that doesn't contain a bonus symbol
        List<List<String>> noBonusMatrix = new ArrayList<>();
        noBonusMatrix.add(Arrays.asList("A", "B", "B"));
        noBonusMatrix.add(Arrays.asList("B", "A", "A"));
        noBonusMatrix.add(Arrays.asList("A", "A", "A"));

        String bonusSymbol = BonusSymbolApplier.applyBonusSymbol(noBonusMatrix, config);
        assertNull(bonusSymbol, "No bonus symbol should be found, so the result should be null");
    }

    @Test
    void testApplyBonusSymbol_MultipleBonusSymbols() {
        // Test with multiple bonus symbols in the matrix
        List<List<String>> multipleBonusMatrix = new ArrayList<>();
        multipleBonusMatrix.add(Arrays.asList("A", "10x", "B"));
        multipleBonusMatrix.add(Arrays.asList("5x", "A", "B"));
        multipleBonusMatrix.add(Arrays.asList("A", "A", "A"));

        // Add a second bonus symbol to the config
        Symbol bonusSymbol5x = new Symbol();
        bonusSymbol5x.setType("bonus");
        config.getSymbols().put("5x", bonusSymbol5x);

        String bonusSymbol = BonusSymbolApplier.applyBonusSymbol(multipleBonusMatrix, config);
        assertEquals("10x", bonusSymbol, "The first bonus symbol encountered should be '10x'");
    }
}
