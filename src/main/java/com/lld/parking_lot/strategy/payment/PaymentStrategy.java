package com.lld.parking_lot.strategy.payment;

import com.lld.parking_lot.model.Ticket;

public interface PaymentStrategy {
    boolean processPayment(Ticket ticket, double amount);
}