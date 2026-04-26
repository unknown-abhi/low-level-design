package com.lld.parking_lot.strategy.payment;

import com.lld.parking_lot.model.Ticket;

public class CashPayment implements PaymentStrategy {
    @Override
    /**
     * Handles process payment for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public boolean processPayment(Ticket ticket, double amount) {
        System.out.println("Paid ₹" + amount + " for ticket " + ticket.getTicketId() + " via Cash.");
        return true;
    }
}
