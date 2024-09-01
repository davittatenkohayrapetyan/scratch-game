package com.davithayrapetyan.scratchgame.logic;

import com.davithayrapetyan.scratchgame.data.GameConfig;
import com.davithayrapetyan.scratchgame.data.GameResult;

import java.util.List;
import java.util.Map;

public class GameRunner {

    public static GameResult runGame(GameConfig config, int betAmount) {
        List<List<String>> matrix = MatrixGenerator.generateMatrix(config);
        Map<String, List<String>> appliedCombinations = WinChecker.checkWinningCombinations(matrix, config);
        String appliedBonusSymbol = BonusSymbolApplier.applyBonusSymbol(matrix, config);
        int reward = RewardCalculator.calculateReward(betAmount, appliedCombinations, appliedBonusSymbol, config);

        return new GameResult(matrix, reward, appliedCombinations, appliedBonusSymbol);
    }
}
