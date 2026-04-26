package com.lld.doctors_appointment.strategy;

import com.lld.doctors_appointment.dto.DoctorSlot;
import com.lld.doctors_appointment.utils.Utils;

import java.util.Comparator;
import java.util.List;

public class StartTimeRankStrategy implements SlotRankStrategy {
    public List<DoctorSlot> rank(List<DoctorSlot> slots) {
        slots.sort(Comparator.comparing(slot -> Utils.convertStringToLocalTime(slot.getSlot())));
        return slots;
    }
}