package com.lld.car_rental.strategy.payment;

import com.lld.car_rental.model.Booking;

public class WalletPaymentStrategy implements PaymentStrategy {
    @Override
    /**
     * Handles process payment for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public boolean processPayment(Booking booking) {
        // Simulate wallet payment processing
        System.out.println("Processing wallet payment for booking: " + booking.getBookingId());
        return true;
    }
}
