package com.lld.doctors_appointment.service;

import lombok.AllArgsConstructor;
import com.lld.doctors_appointment.exception.PatientNotFoundException;
import com.lld.doctors_appointment.model.Patient;
import com.lld.doctors_appointment.repository.PatientRepository;

import java.util.UUID;

@AllArgsConstructor
public class PatientService {
    private final PatientRepository repo;

    /**
     * Handles register for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Patient register(String name) {
        Patient p = new Patient(name);
        repo.save(p);
        return p;
    }

    /**
     * Handles find by id for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Patient findById(UUID id) {
        Patient patient = repo.findById(id);
        if (patient == null)
            throw new PatientNotFoundException("Patient not found");
        return patient;
    }
}
