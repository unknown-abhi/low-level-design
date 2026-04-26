package com.lld.atm.state;

import com.lld.atm.enums.ATMStatus;
import com.lld.atm.model.Card;

public interface ATMState {
    void insertCard(Card card);

    void enterPin(String pin);

    void selectOption(String option);

    void dispenseCash(int amount);

    void ejectCard();

    ATMStatus getStatus();
}
