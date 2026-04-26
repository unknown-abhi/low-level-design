package com.lld.parking_lot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import com.lld.parking_lot.enums.VehicleType;

import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
@RequiredArgsConstructor
public class ParkingSpot {
    private final String id;
    private final VehicleType allowedType;

    private AtomicBoolean occupied = new AtomicBoolean(false);

    // t1 t2
    // false false
    // _ true
    // false

    /**
     * Handles try occupy for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public boolean tryOccupy() {
        return occupied.compareAndSet(false, true);
    }

    /**
     * Handles vacate for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void vacate() {
        occupied.set(false);
    }

    /**
     * Handles is occupied for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public boolean isOccupied() {
        return occupied.get();
    }
}
