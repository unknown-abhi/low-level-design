package com.lld.car_rental.strategy.payment;

import com.lld.car_rental.model.Booking;

public interface PaymentStrategy {
    boolean processPayment(Booking booking);
}
