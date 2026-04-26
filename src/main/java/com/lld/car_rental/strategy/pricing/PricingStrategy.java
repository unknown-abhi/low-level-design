package com.lld.car_rental.strategy.pricing;

import com.lld.car_rental.model.Vehicle;

import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculatePrice(Vehicle vehicle, LocalDateTime start, LocalDateTime end, double distanceKm);
}
