package com.lld.atm.cor;

import com.lld.atm.model.ATM;

public class FiveHundredDispenser implements CashDispenser {
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
        int availableNotes = atm.getFiveHundredCount();
        int notes = Math.min(amount / 500, availableNotes);
        int remainder = amount - notes * 500;
        return remainder == 0 || (next != null && next.canDispense(atm, remainder));
    }

    @Override
    /**
     * Dispenses the dispense requested by the caller.
     * It checks availability, updates balances, and hands work to the dispenser chain.
     */
    public void dispense(ATM atm, int amount) {
        int availableNotes = atm.getFiveHundredCount();
        int notes = Math.min(amount / 500, availableNotes);
        atm.setFiveHundredCount(availableNotes - notes);
        int remainder = amount - notes * 500;

        if (notes > 0)
            System.out.println("Dispensed " + notes + " x ₹500 notes");

        if (remainder > 0 && next != null) {
            next.dispense(atm, remainder);
        }
    }
}
