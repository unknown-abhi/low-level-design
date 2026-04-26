package com.lld.parking_lot.model;

import com.lld.parking_lot.enums.GateType;
import com.lld.parking_lot.service.ParkingLot;
import java.time.LocalDateTime;

public class EntryGate extends Gate {

    public EntryGate(String id) {
        super(id);
    }

    @Override
    public GateType getType() {
        return GateType.ENTRY;
    }

    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime entryTime) {
        return ParkingLot.getInstance().parkVehicle(vehicle, entryTime);
    }
}