package com.lld.doctors_appointment.model;

import lombok.Getter;
import lombok.Setter;
import com.lld.doctors_appointment.enums.Specialization;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class Doctor {
    private final UUID id;
    @Setter
    private String name;
    @Setter
    private Specialization specialization;
    private final Map<String, Boolean> availability = new HashMap<>(); // slot -> isAvailable
    @Setter
    private double rating;

    /**
     * Creates a new Doctor instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Doctor(String name, Specialization specialization, double rating) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.specialization = specialization;
        this.rating = rating;
    }
}
