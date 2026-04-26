package com.lld.parking_lot.factory;

import com.lld.parking_lot.enums.VehicleType;
import com.lld.parking_lot.model.*;

public class VehicleFactory {
    public static Vehicle create(String number, VehicleType type) {
        return switch (type) {
            case CAR -> new Car(number);
            case BIKE -> new Bike(number);
            case TRUCK -> new Truck(number);
        };
    }
}
