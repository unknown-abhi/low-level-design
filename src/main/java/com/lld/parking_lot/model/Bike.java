package com.lld.parking_lot.model;

import com.lld.parking_lot.enums.VehicleType;

public class Bike extends Vehicle {
    public Bike(String number) {
        super(number, VehicleType.BIKE);
    }
}
