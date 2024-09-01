package com.davithayrapetyan.scratchgame.logic;

import com.davithayrapetyan.scratchgame.data.GameConfig;
import com.davithayrapetyan.scratchgame.data.WinCombination;

import java.util.*;

public class WinChecker {

    public static Map<String, List<String>> checkWinningCombinations(List<List<String>> matrix, GameConfig config) {
        Map<String, List<String>> appliedCombinations = new HashMap<>();

        for (Map.Entry<String, WinCombination> entry : config.getWinCombinations().entrySet()) {
            String name = entry.getKey();
            WinCombination combination = entry.getValue();
            List<String> matchingSymbols = findMatchingSymbols(matrix, combination);

            if (!matchingSymbols.isEmpty()) {
                for (String symbol : matchingSymbols) {
                    appliedCombinations.computeIfAbsent(symbol, k -> new ArrayList<>()).add(name);
                }
            }
        }

        return appliedCombinations;
    }

    private static List<String> findMatchingSymbols(List<List<String>> matrix, WinCombination combination) {
        List<String> matchingSymbols = new ArrayList<>();

        switch (combination.getWhenCondition()) {
            case "same_symbols":
                matchingSymbols = findSameSymbols(matrix, combination);
                break;
            case "linear_symbols":
                matchingSymbols = findLinearSymbols(matrix, combination);
                break;
            default:
                break;
        }

        return matchingSymbols;
    }

    private static List<String> findSameSymbols(List<List<String>> matrix, WinCombination combination) {
        Map<String, Integer> symbolCounts = new HashMap<>();

        // Count occurrences of each symbol
        for (List<String> row : matrix) {
            for (String symbol : row) {
                symbolCounts.put(symbol, symbolCounts.getOrDefault(symbol, 0) + 1);
            }
        }

        List<String> matchingSymbols = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : symbolCounts.entrySet()) {
            if (entry.getValue() >= combination.getCount()) {
                matchingSymbols.add(entry.getKey());
            }
        }

        return matchingSymbols;
    }

    private static List<String> findLinearSymbols(List<List<String>> matrix, WinCombination combination) {
        List<String> matchingSymbols = new ArrayList<>();
        List<List<String>> coveredAreas = combination.getCoveredAreas();

        for (List<String> area : coveredAreas) {
            String firstSymbol = null;
            boolean allMatch = true;

            for (String pos : area) {
                String[] parts = pos.split(":");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                String symbol = matrix.get(row).get(col);

                if (firstSymbol == null) {
                    firstSymbol = symbol;
                } else if (!symbol.equals(firstSymbol)) {
                    allMatch = false;
                    break;
                }
            }

            if (allMatch && firstSymbol != null) {
                matchingSymbols.add(firstSymbol);
            }
        }

        return matchingSymbols;
    }
}
