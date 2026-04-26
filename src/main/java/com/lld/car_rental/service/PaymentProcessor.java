package com.lld.car_rental.service;

import com.lld.car_rental.enums.PaymentStatus;
import com.lld.car_rental.model.Booking;
import com.lld.car_rental.strategy.payment.PaymentStrategy;

public class PaymentProcessor {

    private final PaymentStrategy paymentStrategy;

    /**
     * Creates a new PaymentProcessor instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    /**
     * Pays for the pay using the selected strategy.
     * It delegates payment handling and updates the resulting status.
     */
    public boolean pay(Booking booking) {
        boolean success = paymentStrategy.processPayment(booking);

        if (success) {
            booking.setPaymentStatus(PaymentStatus.SUCCESS);
        } else {
            booking.setPaymentStatus(PaymentStatus.FAILED);
            System.out.println("Payment Failed!");
        }

        return success;
    }
}
