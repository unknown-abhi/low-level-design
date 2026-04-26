package com.lld.atm.cor;

import com.lld.atm.model.ATM;

public class OneHundredDispenser implements CashDispenser {
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
        int availableNotes = atm.getOneHundredCount();
        int notes = Math.min(amount / 100, availableNotes);
        int remainder = amount - notes * 100;
        return remainder == 0;
    }

    @Override
    /**
     * Dispenses the dispense requested by the caller.
     * It checks availability, updates balances, and hands work to the dispenser chain.
     */
    public void dispense(ATM atm, int amount) {
        int availableNotes = atm.getOneHundredCount();
        int notes = Math.min(amount / 100, availableNotes);
        atm.setOneHundredCount(availableNotes - notes);
        if (notes > 0)
            System.out.println("Dispensed " + notes + " x ₹100 notes");
    }
}
