package com.lld.parking_lot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.lld.parking_lot.enums.GateType;

@Getter
@AllArgsConstructor
public abstract class Gate {
    protected final String id;

    public abstract GateType getType();
}
