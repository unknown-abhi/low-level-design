package com.lld.parking_lot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.lld.parking_lot.enums.GateType;

@Getter
@AllArgsConstructor
public abstract class Gate {
    protected final String id;

    /**
     * Handles get type for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public abstract GateType getType();
}
