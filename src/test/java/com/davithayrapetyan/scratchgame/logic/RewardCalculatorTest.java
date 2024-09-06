package com.davithayrapetyan.scratchgame.logic;

import com.davithayrapetyan.scratchgame.data.GameConfig;
import com.davithayrapetyan.scratchgame.data.Symbol;
import com.davithayrapetyan.scratchgame.data.WinCombination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RewardCalculatorTest {

    private GameConfig config;
    private Map<String, Symbol> symbolMap;
    private Map<String, WinCombination> winCombinationMap;

    @BeforeEach
    void setUp() {
        // Mock symbols
        Symbol symbolA = new Symbol();
        symbolA.setRewardMultiplier(5);

        Symbol symbolB = new Symbol();
        symbolB.setRewardMultiplier(3);

        Symbol bonus10x = new Symbol();
        bonus10x.setRewardMultiplier(10);
        bonus10x.setImpact("multiply_reward");

        Symbol bonus500 = new Symbol();
        bonus500.setExtra(500);
        bonus500.setImpact("extra_bonus");

        symbolMap = new HashMap<>();
        symbolMap.put("A", symbolA);
        symbolMap.put("B", symbolB);
        symbolMap.put("10x", bonus10x);
        symbolMap.put("+500", bonus500);

        // Mock win combinations
        WinCombination winCombination3Times = new WinCombination();
        winCombination3Times.setRewardMultiplier(2);

        winCombinationMap = new HashMap<>();
        winCombinationMap.put("same_symbol_3_times", winCombination3Times);

        // Set up GameConfig with mocked symbols and win combinations
        config = mock(GameConfig.class);
        when(config.getSymbols()).thenReturn(symbolMap);
        when(config.getWinCombinations()).thenReturn(winCombinationMap);
    }

    @Test
    void testCalculateRewardWithMultiplyingBonus() {
        // Applied combinations for symbol A
        Map<String, List<String>> appliedCombinations = new HashMap<>();
        appliedCombinations.put("A", List.of("same_symbol_3_times"));

        // Run the reward calculator with bonus multiplier
        int reward = RewardCalculator.calculateReward(100, appliedCombinations, "10x", config);

        // (100 * 5) * 2 = 1000, then *10 bonus multiplier = 10000
        assertEquals(10000, reward, "Reward should be 10000 with a 10x bonus multiplier");
    }

    @Test
    void testCalculateRewardWithExtraBonus() {
        // Applied combinations for symbol B
        Map<String, List<String>> appliedCombinations = new HashMap<>();
        appliedCombinations.put("B", List.of("same_symbol_3_times"));

        // Run the reward calculator with extra bonus
        int reward = RewardCalculator.calculateReward(100, appliedCombinations, "+500", config);

        // (100 * 3) * 2 = 600, then +500 bonus = 1100
        assertEquals(1100, reward, "Reward should be 1100 with a +500 bonus");
    }

    @Test
    void testCalculateRewardWithoutBonus() {
        // Applied combinations for symbol A
        Map<String, List<String>> appliedCombinations = new HashMap<>();
        appliedCombinations.put("A", List.of("same_symbol_3_times"));

        // Run the reward calculator without any bonus
        int reward = RewardCalculator.calculateReward(100, appliedCombinations, null, config);

        // (100 * 5) * 2 = 1000
        assertEquals(1000, reward, "Reward should be 1000 without any bonus");
    }
}
