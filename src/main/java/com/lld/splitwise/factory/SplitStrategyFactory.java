package com.lld.splitwise.factory;

import com.lld.splitwise.enums.SplitType;
import com.lld.splitwise.strategy.EqualSplitStrategy;
import com.lld.splitwise.strategy.PercentageSplitStrategy;
import com.lld.splitwise.strategy.SplitStrategy;

public class SplitStrategyFactory {
    /**
     * Handles get strategy for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public static SplitStrategy getStrategy(SplitType splitType) {
        return switch (splitType) {
            case EQUAL -> new EqualSplitStrategy();
            case PERCENTAGE -> new PercentageSplitStrategy();
        };
    }
}
