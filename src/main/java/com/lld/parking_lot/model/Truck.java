package com.lld.parking_lot.model;

import com.lld.parking_lot.enums.VehicleType;

public class Truck extends Vehicle {
    /**
     * Creates a new Truck instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Truck(String number) {
        super(number, VehicleType.TRUCK);
    }
}
