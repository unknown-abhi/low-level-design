package com.lld.atm.cor;

public class CashDispenserChainBuilder {
    /**
     * Handles build chain for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public static CashDispenser buildChain() {
        CashDispenser d1 = new TwoThousandDispenser();
        CashDispenser d2 = new FiveHundredDispenser();
        CashDispenser d3 = new OneHundredDispenser();

        d1.setNextDispenser(d2);
        d2.setNextDispenser(d3);
        return d1;
    }
}
