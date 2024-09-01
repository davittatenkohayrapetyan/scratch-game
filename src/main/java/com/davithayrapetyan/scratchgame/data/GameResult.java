package com.davithayrapetyan.scratchgame.data;

import java.util.List;
import java.util.Map;

public class GameResult {
    private List<List<String>> matrix;
    private int reward;
    private Map<String, List<String>> appliedWinningCombinations;
    private String appliedBonusSymbol;

    // Constructor
    public GameResult(List<List<String>> matrix, int reward, Map<String, List<String>> appliedWinningCombinations, String appliedBonusSymbol) {
        this.matrix = matrix;
        this.reward = reward;
        this.appliedWinningCombinations = appliedWinningCombinations;
        this.appliedBonusSymbol = appliedBonusSymbol;
    }

    // Getters and Setters
    public List<List<String>> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<List<String>> matrix) {
        this.matrix = matrix;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public Map<String, List<String>> getAppliedWinningCombinations() {
        return appliedWinningCombinations;
    }

    public void setAppliedWinningCombinations(Map<String, List<String>> appliedWinningCombinations) {
        this.appliedWinningCombinations = appliedWinningCombinations;
    }

    public String getAppliedBonusSymbol() {
        return appliedBonusSymbol;
    }

    public void setAppliedBonusSymbol(String appliedBonusSymbol) {
        this.appliedBonusSymbol = appliedBonusSymbol;
    }

    // Override toString() for better output in the console
    @Override
    public String toString() {
        return "GameResult{" +
                "matrix=" + matrix +
                ", reward=" + reward +
                ", appliedWinningCombinations=" + appliedWinningCombinations +
                ", appliedBonusSymbol='" + appliedBonusSymbol + '\'' +
                '}';
    }
}
