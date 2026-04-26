package com.lld.atm.repository;

import com.lld.atm.enums.ATMStatus;
import com.lld.atm.model.ATM;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ATMRepository {
    private final Map<String, ATM> atms = new HashMap<>();

    /**
     * Saves the save into the backing store.
     * It updates repository state so later operations can retrieve the same data.
     */
    public void save(ATM atm) {
        atms.put(atm.getId(), atm);
    }

    /**
     * Handles get by id for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Optional<ATM> getById(String id) {
        return Optional.ofNullable(atms.get(id));
    }

    /**
     * Handles update a t m status by id for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void updateATMStatusById(String id, ATMStatus newStatus) {
        atms.get(id).setStatus(newStatus);
    }
}
