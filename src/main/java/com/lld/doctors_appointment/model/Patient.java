package com.lld.doctors_appointment.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

@Getter
public class Patient {
    private final UUID id;
    @Setter
    private String name;

    /**
     * Creates a new Patient instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Patient(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
