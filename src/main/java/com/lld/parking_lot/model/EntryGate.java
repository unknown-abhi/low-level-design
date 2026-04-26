package com.lld.parking_lot.model;

import com.lld.parking_lot.enums.GateType;
import com.lld.parking_lot.service.ParkingLot;
import java.time.LocalDateTime;

public class EntryGate extends Gate {

    /**
     * Creates a new EntryGate instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public EntryGate(String id) {
        super(id);
    }

    @Override
    /**
     * Handles get type for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public GateType getType() {
        return GateType.ENTRY;
    }

    /**
     * Handles park vehicle for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime entryTime) {
        return ParkingLot.getInstance().parkVehicle(vehicle, entryTime);
    }
}
