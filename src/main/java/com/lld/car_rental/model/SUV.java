package com.lld.car_rental.model;

import com.lld.car_rental.enums.VehicleType;

public class SUV extends Vehicle {
    public SUV(String licensePlate, double pricePerHour, double pricePerKm) {
        super(licensePlate, pricePerHour, pricePerKm, VehicleType.SUV);
    }
}