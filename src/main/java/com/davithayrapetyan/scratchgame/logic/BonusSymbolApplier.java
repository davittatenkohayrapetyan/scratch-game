package com.davithayrapetyan.scratchgame.logic;

import com.davithayrapetyan.scratchgame.data.GameConfig;
import com.davithayrapetyan.scratchgame.data.Symbol;

import java.util.List;

public class BonusSymbolApplier {

    public static String applyBonusSymbol(List<List<String>> matrix, GameConfig config) {
        for (List<String> row : matrix) {
            for (String symbol : row) {
                Symbol bonusSymbol = config.getSymbols().get(symbol);
                if (bonusSymbol != null && "bonus".equals(bonusSymbol.getType())) {
                    return symbol;
                }
            }
        }
        return null;
    }
}
