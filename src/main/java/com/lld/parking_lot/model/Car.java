package com.lld.parking_lot.model;

import com.lld.parking_lot.enums.VehicleType;

public class Car extends Vehicle {
    /**
     * Creates a new Car instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Car(String number) {
        super(number, VehicleType.CAR);
    }
}
