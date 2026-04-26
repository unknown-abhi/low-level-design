package com.lld.doctors_appointment.service;

import lombok.AllArgsConstructor;
import com.lld.doctors_appointment.exception.DoctorNotFoundException;
import com.lld.doctors_appointment.model.Doctor;
import com.lld.doctors_appointment.enums.Specialization;
import com.lld.doctors_appointment.repository.DoctorRepository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository repo;

    /**
     * Handles register for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Doctor register(String name, Specialization spec, double rating) {
        Doctor doctor = new Doctor(name, spec, rating);
        repo.save(doctor);
        return doctor;
    }

    /**
     * Handles declare availability for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void declareAvailability(UUID doctorId, List<String> slots) {
        Doctor doc = repo.findById(doctorId);
        if (doc == null)
            throw new DoctorNotFoundException("Doctor not found");
        for (String slot : slots)
            doc.getAvailability().put(slot, true);
    }
}
