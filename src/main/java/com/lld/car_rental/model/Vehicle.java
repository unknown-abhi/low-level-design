package com.lld.car_rental.model;

import lombok.Getter;
import lombok.Setter;
import com.lld.car_rental.enums.*;

import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
public abstract class Vehicle {
    private String licensePlate;
    private VehicleStatus status;
    private double pricePerHour;
    private double pricePerKm;
    private VehicleType type;
    private int bookingCount = 0;

    private final AtomicBoolean isBooked = new AtomicBoolean(false);

    /**
     * Creates a new Vehicle instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Vehicle(String licensePlate, double pricePerHour, double pricePerKm, VehicleType type) {
        this.licensePlate = licensePlate;
        this.status = VehicleStatus.AVAILABLE;
        this.pricePerHour = pricePerHour;
        this.pricePerKm = pricePerKm;
        this.type = type;
    }

    /**
     * Handles increment booking count for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void incrementBookingCount() {
        this.bookingCount++;
    }
}
