package com.lld.parking_lot.service;

import com.lld.parking_lot.enums.PaymentStatus;
import com.lld.parking_lot.model.Ticket;
import com.lld.parking_lot.strategy.payment.PaymentStrategy;

public class PaymentProcessor {
    private final PaymentStrategy strategy;

    /**
     * Creates a new PaymentProcessor instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public PaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Pays for the pay using the selected strategy.
     * It delegates payment handling and updates the resulting status.
     */
    public boolean pay(Ticket ticket, double amount) {
        boolean success = strategy.processPayment(ticket, amount);
        if (success) {
            ticket.setPaymentStatus(PaymentStatus.SUCCESS);
        } else {
            ticket.setPaymentStatus(PaymentStatus.FAILED);
            System.out.println("Payment failed for ticket: " + ticket.getTicketId());
        }
        return success;
    }
}
