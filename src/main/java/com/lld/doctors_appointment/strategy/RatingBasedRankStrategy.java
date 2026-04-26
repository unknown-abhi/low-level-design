package com.lld.doctors_appointment.strategy;

import com.lld.doctors_appointment.dto.DoctorSlot;

import java.util.List;

public class RatingBasedRankStrategy implements SlotRankStrategy {
    @Override
    /**
     * Ranks the rank for downstream selection.
     * It compares candidates and orders them according to the strategy rules.
     */
    public List<DoctorSlot> rank(List<DoctorSlot> slots) {
        slots.sort((a, b) -> Double.compare(b.getDoctor().getRating(), a.getDoctor().getRating()));
        return slots;
    }
}
