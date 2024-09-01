package com.davithayrapetyan.scratchgame.data;

import java.util.Map;
import java.util.List;

public class GameConfig {
    private int columns;
    private int rows;
    private Map<String, Symbol> symbols;
    private Probabilities probabilities;
    private Map<String, WinCombination> winCombinations;

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Symbol> symbols) {
        this.symbols = symbols;
    }

    public Probabilities getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(Probabilities probabilities) {
        this.probabilities = probabilities;
    }

    public Map<String, WinCombination> getWinCombinations() {
        return winCombinations;
    }

    public void setWinCombinations(Map<String, WinCombination> winCombinations) {
        this.winCombinations = winCombinations;
    }

    public static class Probabilities {
        private List<StandardSymbolProbability> standardSymbols;
        private BonusSymbolProbability bonusSymbols;

        public List<StandardSymbolProbability> getStandardSymbols() {
            return standardSymbols;
        }

        public void setStandardSymbols(List<StandardSymbolProbability> standardSymbols) {
            this.standardSymbols = standardSymbols;
        }

        public BonusSymbolProbability getBonusSymbols() {
            return bonusSymbols;
        }

        public void setBonusSymbols(BonusSymbolProbability bonusSymbols) {
            this.bonusSymbols = bonusSymbols;
        }
    }

    public static class StandardSymbolProbability {
        private int column;
        private int row;
        private Map<String, Integer> symbols;

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public Map<String, Integer> getSymbols() {
            return symbols;
        }

        public void setSymbols(Map<String, Integer> symbols) {
            this.symbols = symbols;
        }
    }

    public static class BonusSymbolProbability {
        private Map<String, Integer> symbols;

        public Map<String, Integer> getSymbols() {
            return symbols;
        }

        public void setSymbols(Map<String, Integer> symbols) {
            this.symbols = symbols;
        }
    }
}