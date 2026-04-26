package com.lld.atm.cor;

import com.lld.atm.model.ATM;

public class TwoThousandDispenser implements CashDispenser {
    private CashDispenser next;

    @Override
    /**
     * Handles set next dispenser for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void setNextDispenser(CashDispenser next) {
        this.next = next;
    }

    @Override
    /**
     * Handles can dispense for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public boolean canDispense(ATM atm, int amount) {
        int count = atm.getTwoThousandCount();
        int notes = Math.min(amount / 2000, count);
        int remainder = amount - notes * 2000;
        return remainder == 0 || (next != null && next.canDispense(atm, remainder));
    }

    @Override
    /**
     * Dispenses the dispense requested by the caller.
     * It checks availability, updates balances, and hands work to the dispenser chain.
     */
    public void dispense(ATM atm, int amount) {
        int count = atm.getTwoThousandCount();
        int notes = Math.min(amount / 2000, count);
        atm.setTwoThousandCount(count - notes);

        int remainder = amount - notes * 2000;
        if (notes > 0)
            System.out.println("Dispensed " + notes + " x 2000 notes");

        if (remainder > 0 && next != null) {
            next.dispense(atm, remainder);
        }
    }
}
