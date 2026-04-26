package com.lld.parking_lot.factory;

import com.lld.parking_lot.enums.PricingStrategyType;
import com.lld.parking_lot.strategy.pricing.*;

public class PricingStrategyFactory {
    /**
     * Returns the get requested by the caller.
     * It reads the current state and exposes the value without changing behavior.
     */
    public static PricingStrategy get(PricingStrategyType type) {
        return switch (type) {
            case TIME_BASED -> new TimeBasedPricing();
            case EVENT_BASED -> new EventBasedPricing();
        };
    }
}
