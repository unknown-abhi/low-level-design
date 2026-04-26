package com.lld.car_rental.model;

import com.lld.car_rental.enums.VehicleType;

public class SUV extends Vehicle {
    /**
     * Creates a new SUV instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public SUV(String licensePlate, double pricePerHour, double pricePerKm) {
        super(licensePlate, pricePerHour, pricePerKm, VehicleType.SUV);
    }
}
