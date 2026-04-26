package com.lld.atm.state;

import lombok.AllArgsConstructor;
import com.lld.atm.cor.CashDispenser;
import com.lld.atm.cor.CashDispenserChainBuilder;
import com.lld.atm.enums.ATMStatus;
import com.lld.atm.model.Card;
import com.lld.atm.service.ATMMachine;

@AllArgsConstructor
public class DispenseCashState implements ATMState {
    private final ATMMachine atmMachine;
    private final CashDispenser chain = CashDispenserChainBuilder.buildChain();

    @Override
    /**
     * Handles insert card for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void insertCard(Card card) {
        System.out.println("Transaction in progress. Cannot insert another card.");
    }

    @Override
    /**
     * Handles enter pin for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void enterPin(String pin) {
        System.out.println("Already authenticated.");
    }

    @Override
    /**
     * Handles select option for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void selectOption(String option) {
        System.out.println("Option already selected.");
    }

    @Override
    /**
     * Handles dispense cash for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void dispenseCash(int amount) {
        double atmBalance = atmMachine.getAtm().getCashAvailable();
        double accountBalance = atmMachine.getCurrentCard()
                .getAccount()
                .getBalance();

        if (amount > atmBalance) {
            System.out.println("ATM has insufficient cash. Cannot dispense " + amount);
            ejectCard();
            return;
        }

        if (amount > accountBalance) {
            System.out.println("Insufficient account balance.");
            ejectCard();
            return;
        }

        // Now check if note combination is possible
        if (chain.canDispense(atmMachine.getAtm(), amount)) {
            chain.dispense(atmMachine.getAtm(), amount);

            // Deduct from ATM cash & account balance
            atmMachine.getAtm().setCashAvailable(atmBalance - amount);
            atmMachine.getCurrentCard().getAccount().setBalance(accountBalance - amount);

            ejectCard();
            System.out.println("Cash dispensed: " + amount);
        } else {
            System.out.println("Cannot dispense requested amount with available denominations.");
            ejectCard();
        }
    }

    @Override
    /**
     * Handles eject card for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void ejectCard() {
        atmMachine.setCurrentCard(null);
        System.out.println("Card ejected.");
        atmMachine.setState(new IdleState(atmMachine)); // use factory
    }

    @Override
    /**
     * Handles get status for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public ATMStatus getStatus() {
        return ATMStatus.DISPENSE_CASH;
    }
}
