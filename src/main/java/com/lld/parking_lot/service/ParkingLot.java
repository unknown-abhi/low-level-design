package com.lld.parking_lot.service;

import lombok.Getter;
import lombok.Setter;
import com.lld.parking_lot.enums.PaymentMode;
import com.lld.parking_lot.enums.*;
import com.lld.parking_lot.factory.*;
import com.lld.parking_lot.model.*;
import com.lld.parking_lot.strategy.payment.*;
import com.lld.parking_lot.strategy.pricing.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class ParkingLot {
    private static final ParkingLot INSTANCE = new ParkingLot();

    private final Map<String, ParkingFloor> floors = new HashMap<>();
    private final Map<String, Ticket> activeTickets = new HashMap<>();
    @Setter
    private PricingStrategy pricingStrategy;

    /**
     * Creates a new ParkingLot instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    private ParkingLot() {
        this.pricingStrategy = PricingStrategyFactory.get(PricingStrategyType.TIME_BASED);
    }

    /**
     * Handles get instance for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public static ParkingLot getInstance() {
        return INSTANCE;
    }

    /**
     * Handles add floor for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void addFloor(ParkingFloor floor) {
        floors.put(floor.getId(), floor);
    }

    // b1, b2 (one spot)
    // car, bike

    /**
     * Handles park vehicle for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime entryTime) {
        for (ParkingFloor floor : floors.values()) {
            ParkingSpot spot = floor.findAvailableSpot(vehicle.getType());

            if (spot != null) {
                // Successfully reserved the spot via atomic operation
                String ticketId = UUID.randomUUID().toString();
                Ticket ticket = Ticket.builder()
                        .ticketId(ticketId)
                        .entryTime(entryTime)
                        .vehicle(vehicle)
                        .floorId(floor.getId())
                        .spotId(spot.getId())
                        .build();

                activeTickets.put(ticketId, ticket);
                System.out.println("Vehicle parked. Ticket: " + ticketId);
                return ticket;
            }
        }

        System.out.println("No spot available for vehicle type: " + vehicle.getType());
        return null;
    }

    /**
     * Handles unpark vehicle for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void unparkVehicle(String ticketId, LocalDateTime exitTime, PaymentMode paymentMode) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            System.out.println("Invalid ticket ID.");
            return;
        }

        double fee = pricingStrategy.calculateFee(
                ticket.getVehicle().getType(),
                ticket.getEntryTime(),
                exitTime);

        PaymentStrategy strategy = PaymentStrategyFactory.get(paymentMode);
        PaymentProcessor processor = new PaymentProcessor(strategy);
        boolean paid = processor.pay(ticket, fee);

        if (!paid) {
            System.out.println("Vehicle cannot exit. Payment unsuccessful.");
            return;
        }

        ParkingSpot spot = floors.get(ticket.getFloorId()).getSpots().get(ticket.getSpotId());
        spot.vacate();
        activeTickets.remove(ticketId);

        System.out.println("Vehicle exited. Fee charged: ₹" + fee);
    }

    /**
     * Handles print status for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void printStatus() {
        floors.forEach((floorId, floor) -> {
            System.out.println("Floor: " + floorId);
            floor.getSpots().values().forEach(spot -> {
                System.out.println(" Spot " + spot.getId() + " [" + spot.getAllowedType() + "] - "
                        + (spot.isOccupied() ? "Occupied" : "Free"));
            });
        });
    }
}
