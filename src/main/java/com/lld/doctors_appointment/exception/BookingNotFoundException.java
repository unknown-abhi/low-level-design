package com.lld.doctors_appointment.exception;

public class BookingNotFoundException extends RuntimeException {
    /**
     * Creates a new BookingNotFoundException instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public BookingNotFoundException(String message) {
        super(message);
    }
}
