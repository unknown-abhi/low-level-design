package com.lld.parking_lot.model;

import lombok.Getter;
import com.lld.parking_lot.enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ParkingFloor {
    private final String id;
    private final Map<String, ParkingSpot> spots = new HashMap<>();

    /**
     * Creates a new ParkingFloor instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public ParkingFloor(String id) {
        this.id = id;
    }

    /**
     * Handles add spot for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void addSpot(ParkingSpot spot) {
        spots.put(spot.getId(), spot);
    }

    /**
     * Handles find available spot for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public ParkingSpot findAvailableSpot(VehicleType vehicleType) {
        for (ParkingSpot spot : spots.values()) {
            if (spot.getAllowedType() == vehicleType && spot.tryOccupy()) {
                return spot;
            }
        }
        return null;
    }
}
