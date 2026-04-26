package com.lld.atm.factory;

import com.lld.atm.enums.ATMStatus;
import com.lld.atm.service.ATMMachine;
import com.lld.atm.state.*;

public class ATMStateFactory {

    public static ATMState getState(ATMStatus status, ATMMachine machine) {
        return switch (status) {
            case IDLE -> new IdleState(machine);
            case CARD_INSERTED -> new CardInsertedState(machine);
            case AUTHENTICATED -> new AuthenticatedState(machine);
            case DISPENSE_CASH -> new DispenseCashState(machine);
            default -> throw new IllegalArgumentException("Unknown ATM status: " + status);
        };
    }
}
