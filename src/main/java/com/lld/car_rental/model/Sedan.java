package com.lld.car_rental.model;

import com.lld.car_rental.enums.VehicleType;

public class Sedan extends Vehicle {
    /**
     * Creates a new Sedan instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Sedan(String licensePlate, double pricePerHour, double pricePerKm) {
        super(licensePlate, pricePerHour, pricePerKm, VehicleType.SEDAN);
    }
}
