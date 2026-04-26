package com.lld.car_rental.strategy.payment;

import com.lld.car_rental.model.Booking;

public class WalletPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean processPayment(Booking booking) {
        // Simulate wallet payment processing
        System.out.println("Processing wallet payment for booking: " + booking.getBookingId());
        return true;
    }
}
