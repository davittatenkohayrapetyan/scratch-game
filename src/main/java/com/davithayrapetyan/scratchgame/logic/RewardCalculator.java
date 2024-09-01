package com.davithayrapetyan.scratchgame.logic;

import com.davithayrapetyan.scratchgame.data.GameConfig;
import com.davithayrapetyan.scratchgame.data.Symbol;
import com.davithayrapetyan.scratchgame.data.WinCombination;

import java.util.List;
import java.util.Map;

public class RewardCalculator {

    public static int calculateReward(int betAmount, Map<String, List<String>> appliedCombinations, String appliedBonusSymbol, GameConfig config) {
        int reward = 0;

        for (Map.Entry<String, List<String>> entry : appliedCombinations.entrySet()) {
            String symbol = entry.getKey();
            Symbol symbolConfig = config.getSymbols().get(symbol);
            int baseReward = (int) (betAmount * symbolConfig.getRewardMultiplier());

            int symbolReward = baseReward;
            for (String combinationName : entry.getValue()) {
                WinCombination combination = config.getWinCombinations().get(combinationName);
                symbolReward *= combination.getRewardMultiplier();
            }

            reward += symbolReward;
        }

        if (appliedBonusSymbol != null) {
            Symbol bonusSymbolConfig = config.getSymbols().get(appliedBonusSymbol);
            switch (bonusSymbolConfig.getImpact()) {
                case "multiply_reward":
                    reward *= bonusSymbolConfig.getRewardMultiplier();
                    break;
                case "extra_bonus":
                    reward += bonusSymbolConfig.getExtra();
                    break;
                default:
                    break;
            }
        }

        return reward;
    }
}
