package com.lld.doctors_appointment.exception;

public class DoctorNotFoundException extends RuntimeException {
    /**
     * Creates a new DoctorNotFoundException instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public DoctorNotFoundException(String message) {
        super(message);
    }
}
