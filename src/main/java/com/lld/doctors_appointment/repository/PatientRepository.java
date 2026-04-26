package com.lld.doctors_appointment.repository;

import com.lld.doctors_appointment.model.Patient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PatientRepository {
    private final Map<UUID, Patient> patientMap = new HashMap<>();

    /**
     * Saves the save into the backing store.
     * It updates repository state so later operations can retrieve the same data.
     */
    public void save(Patient patient) {
        patientMap.put(patient.getId(), patient);
    }

    /**
     * Handles find by id for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Patient findById(UUID id) {
        return patientMap.get(id);
    }
}
