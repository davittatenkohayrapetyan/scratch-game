package com.davithayrapetyan.scratchgame.logic;

import com.davithayrapetyan.scratchgame.data.GameConfig;
import com.davithayrapetyan.scratchgame.data.GameConfig.StandardSymbolProbability;
import com.davithayrapetyan.scratchgame.data.GameConfig.BonusSymbolProbability;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatrixGenerator {

    public static List<List<String>> generateMatrix(GameConfig config) {
        List<List<String>> matrix = new ArrayList<>();

        for (int row = 0; row < config.getRows(); row++) {
            List<String> rowSymbols = new ArrayList<>();
            for (int col = 0; col < config.getColumns(); col++) {
                String symbol = generateSymbol(config, row, col);
                rowSymbols.add(symbol);
            }
            matrix.add(rowSymbols);
        }

        // Optionally, add a bonus symbol randomly in the matrix
        addBonusSymbolToMatrix(config, matrix);

        return matrix;
    }

    private static String generateSymbol(GameConfig config, int row, int col) {
        List<StandardSymbolProbability> standardSymbols = config.getProbabilities().getStandardSymbols();
        StandardSymbolProbability symbolProbability = standardSymbols.stream()
                .filter(probability -> probability.getRow() == row && probability.getColumn() == col)
                .findFirst()
                .orElse(standardSymbols.get(0));

        Map<String, Integer> symbols = symbolProbability.getSymbols();
        int totalProbability = symbols.values().stream().mapToInt(Integer::intValue).sum();
        int random = new Random().nextInt(totalProbability);

        int cumulativeProbability = 0;
        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            cumulativeProbability += entry.getValue();
            if (random < cumulativeProbability) {
                return entry.getKey();
            }
        }

        return "MISS";
    }

    private static void addBonusSymbolToMatrix(GameConfig config, List<List<String>> matrix) {
        Random random = new Random();
        int row = random.nextInt(config.getRows());
        int col = random.nextInt(config.getColumns());

        String bonusSymbol = generateBonusSymbol(config);
        matrix.get(row).set(col, bonusSymbol);
    }

    protected static String generateBonusSymbol(GameConfig config) {
        BonusSymbolProbability bonusSymbols = config.getProbabilities().getBonusSymbols();
        Map<String, Integer> symbols = bonusSymbols.getSymbols();

        // Calculate total probability
        int totalProbability = symbols.values().stream().mapToInt(Integer::intValue).sum();

        // If total probability is 0, return "MISS"
        if (totalProbability == 0) {
            return "MISS";
        }

        // Generate a random number within the range of total probability
        int random = new Random().nextInt(totalProbability);

        int cumulativeProbability = 0;
        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            cumulativeProbability += entry.getValue();
            if (random < cumulativeProbability) {
                return entry.getKey();
            }
        }

        return "MISS";
    }

}
