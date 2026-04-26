package com.lld.atm.state;

import lombok.AllArgsConstructor;
import com.lld.atm.enums.ATMStatus;
import com.lld.atm.model.Card;
import com.lld.atm.service.ATMMachine;

@AllArgsConstructor
public class IdleState implements ATMState {
    private final ATMMachine atmMachine;

    @Override
    /**
     * Handles insert card for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void insertCard(Card card) {
        atmMachine.setCurrentCard(card);
        System.out.println("Card inserted.");
        atmMachine.setState(new CardInsertedState(atmMachine));
    }

    @Override
    /**
     * Handles enter pin for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void enterPin(String pin) {
        System.out.println("No card inserted.");
    }

    @Override
    /**
     * Handles select option for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void selectOption(String option) {
        System.out.println("No card inserted.");
    }

    @Override
    /**
     * Handles dispense cash for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void dispenseCash(int amount) {
        System.out.println("No card inserted.");
    }

    @Override
    /**
     * Handles eject card for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void ejectCard() {
        System.out.println("No card to eject.");
    }

    @Override
    /**
     * Handles get status for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public ATMStatus getStatus() {
        return ATMStatus.IDLE;
    }
}
