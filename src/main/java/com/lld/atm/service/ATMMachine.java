package com.lld.atm.service;

import lombok.Getter;
import lombok.Setter;
import com.lld.atm.factory.ATMStateFactory;
import com.lld.atm.model.ATM;
import com.lld.atm.model.Card;
import com.lld.atm.repository.ATMRepository;
import com.lld.atm.state.ATMState;

@Getter
public class ATMMachine {
    private final ATM atm;
    private ATMState state;
    private final ATMRepository atmRepository;
    @Setter
    private Card currentCard;

    /**
     * Creates a new ATMMachine instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public ATMMachine(String atmId, ATMRepository atmRepository) {
        this.atmRepository = atmRepository;
        this.atm = atmRepository.getById(atmId)
                .orElseThrow(() -> new RuntimeException("ATM not found"));
        this.state = ATMStateFactory.getState(atm.getStatus(), this);
    }

    /**
     * Handles insert card for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void insertCard(Card card) {
        state.insertCard(card);
    }

    /**
     * Handles enter pin for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void enterPin(String pin) {
        state.enterPin(pin);
    }

    /**
     * Handles select option for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void selectOption(String option) {
        state.selectOption(option);
    }

    /**
     * Handles dispense cash for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void dispenseCash(int amount) {
        state.dispenseCash(amount);
    }

    /**
     * Handles eject card for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void ejectCard() {
        state.ejectCard();
    }

    /**
     * Handles set state for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void setState(ATMState state) {
        this.state = state;
        this.atm.setStatus(state.getStatus());
        // persist the changes in db
    }
}
