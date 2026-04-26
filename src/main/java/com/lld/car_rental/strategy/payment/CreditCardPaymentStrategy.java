package com.lld.car_rental.strategy.payment;

import com.lld.car_rental.model.Booking;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    /**
     * Handles process payment for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public boolean processPayment(Booking booking) {
        // Simulate credit card processing
        System.out.println("Processing credit card payment for booking: " + booking.getBookingId());
        return true;
    }
}
