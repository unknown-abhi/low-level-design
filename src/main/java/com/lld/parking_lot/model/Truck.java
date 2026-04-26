package com.lld.parking_lot.model;

import com.lld.parking_lot.enums.VehicleType;

public class Truck extends Vehicle {
    public Truck(String number) {
        super(number, VehicleType.TRUCK);
    }
}
