package com.lld.doctors_appointment.exception;

public class PatientNotFoundException extends RuntimeException {
    /**
     * Creates a new PatientNotFoundException instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public PatientNotFoundException(String message) {
        super(message);
    }
}
