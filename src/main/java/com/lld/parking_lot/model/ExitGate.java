package com.lld.parking_lot.model;

import com.lld.parking_lot.enums.GateType;
import com.lld.parking_lot.enums.PaymentMode;
import com.lld.parking_lot.service.ParkingLot;

import java.time.LocalDateTime;

public class ExitGate extends Gate {

    /**
     * Creates a new ExitGate instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public ExitGate(String id) {
        super(id);
    }

    @Override
    /**
     * Handles get type for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public GateType getType() {
        return GateType.EXIT;
    }

    /**
     * Handles unpark vehicle for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void unparkVehicle(String ticketId, LocalDateTime exitTime, PaymentMode paymentMode) {
        ParkingLot.getInstance().unparkVehicle(ticketId, exitTime, paymentMode);
    }
}
