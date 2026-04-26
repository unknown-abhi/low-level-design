package com.lld.parking_lot.factory;

import com.lld.parking_lot.enums.VehicleType;
import com.lld.parking_lot.model.*;

public class VehicleFactory {
    /**
     * Creates the create needed by this module.
     * It validates inputs, prepares dependencies, and returns the new object.
     */
    public static Vehicle create(String number, VehicleType type) {
        return switch (type) {
            case CAR -> new Car(number);
            case BIKE -> new Bike(number);
            case TRUCK -> new Truck(number);
        };
    }
}
