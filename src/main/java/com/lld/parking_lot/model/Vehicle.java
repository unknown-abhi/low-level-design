package com.lld.parking_lot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.lld.parking_lot.enums.VehicleType;

@Getter
@RequiredArgsConstructor
public abstract class Vehicle {
    private final String number;
    private final VehicleType type;
}
