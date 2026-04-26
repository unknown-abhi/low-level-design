package com.lld.parking_lot.model;

import com.lld.parking_lot.enums.VehicleType;

public class Bike extends Vehicle {
    /**
     * Creates a new Bike instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Bike(String number) {
        super(number, VehicleType.BIKE);
    }
}
