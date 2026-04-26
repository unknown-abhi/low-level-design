package com.lld.doctors_appointment.strategy;

import com.lld.doctors_appointment.dto.DoctorSlot;
import com.lld.doctors_appointment.utils.Utils;

import java.util.Comparator;
import java.util.List;

public class StartTimeRankStrategy implements SlotRankStrategy {
    /**
     * Ranks the rank for downstream selection.
     * It compares candidates and orders them according to the strategy rules.
     */
    public List<DoctorSlot> rank(List<DoctorSlot> slots) {
        slots.sort(Comparator.comparing(slot -> Utils.convertStringToLocalTime(slot.getSlot())));
        return slots;
    }
}
