package com.lld.doctors_appointment.repository;

import com.lld.doctors_appointment.model.Doctor;
import com.lld.doctors_appointment.enums.Specialization;

import java.util.*;

public class DoctorRepository {
    private final Map<UUID, Doctor> doctorMap = new HashMap<>();

    /**
     * Saves the save into the backing store.
     * It updates repository state so later operations can retrieve the same data.
     */
    public void save(Doctor doctor) {
        doctorMap.put(doctor.getId(), doctor);
    }

    /**
     * Handles find by id for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Doctor findById(UUID id) {
        return doctorMap.get(id);
    }

    /**
     * Handles find by specialization for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public List<Doctor> findBySpecialization(Specialization specialization) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor doc : doctorMap.values()) {
            if (doc.getSpecialization() == specialization)
                result.add(doc);
        }
        return result;
    }
}
