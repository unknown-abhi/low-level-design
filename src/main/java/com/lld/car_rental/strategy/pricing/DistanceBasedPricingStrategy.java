package com.lld.car_rental.strategy.pricing;

import com.lld.car_rental.model.Vehicle;

import java.time.LocalDateTime;

public class DistanceBasedPricingStrategy implements PricingStrategy {
    @Override
    /**
     * Handles calculate price for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public double calculatePrice(Vehicle vehicle, LocalDateTime start, LocalDateTime end, double distanceKm) {
        return distanceKm * vehicle.getPricePerKm();
    }
}
