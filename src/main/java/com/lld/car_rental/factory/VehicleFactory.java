package com.lld.car_rental.factory;

import com.lld.car_rental.model.SUV;
import com.lld.car_rental.model.Sedan;
import com.lld.car_rental.enums.VehicleType;
import com.lld.car_rental.model.Vehicle;

public class VehicleFactory {
    public static Vehicle create(VehicleType type, String licensePlate, double pricePerHour, double pricePerKm) {
        return switch (type) {
            case SEDAN -> new Sedan(licensePlate, pricePerHour, pricePerKm);
            case SUV -> new SUV(licensePlate, pricePerHour, pricePerKm);
        };
    }
}
