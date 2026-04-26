package com.lld.parking_lot.factory;

import com.lld.parking_lot.enums.PaymentMode;
import com.lld.parking_lot.strategy.payment.*;

public class PaymentStrategyFactory {
    /**
     * Returns the get requested by the caller.
     * It reads the current state and exposes the value without changing behavior.
     */
    public static PaymentStrategy get(PaymentMode mode) {
        return switch (mode) {
            case CASH -> new CashPayment();
            case UPI -> new UpiPayment();
            case CARD -> new CardPayment();
        };
    }
}
