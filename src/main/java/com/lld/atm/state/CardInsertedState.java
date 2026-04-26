package com.lld.atm.state;

import lombok.AllArgsConstructor;
import com.lld.atm.enums.ATMStatus;
import com.lld.atm.model.Card;
import com.lld.atm.service.ATMMachine;

@AllArgsConstructor
public class CardInsertedState implements ATMState {
    private final ATMMachine atmMachine;

    @Override
    /**
     * Handles insert card for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void insertCard(Card card) {
        System.out.println("Card already inserted.");
    }

    @Override
    /**
     * Handles enter pin for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void enterPin(String pin) {
        if (atmMachine.getCurrentCard().getPin().equals(pin)) {
            System.out.println("PIN correct. Authenticated.");
            atmMachine.setState(new AuthenticatedState(atmMachine));
        } else {
            System.out.println("Invalid PIN.");
        }
    }

    @Override
    /**
     * Handles select option for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void selectOption(String option) {
        System.out.println("Enter PIN first.");
    }

    @Override
    /**
     * Handles dispense cash for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void dispenseCash(int amount) {
        System.out.println("Enter PIN before dispensing.");
    }

    @Override
    /**
     * Handles eject card for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void ejectCard() {
        atmMachine.setCurrentCard(null);
        System.out.println("Card ejected.");
        atmMachine.setState(new IdleState(atmMachine));
    }

    @Override
    /**
     * Handles get status for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public ATMStatus getStatus() {
        return ATMStatus.CARD_INSERTED;
    }
}
