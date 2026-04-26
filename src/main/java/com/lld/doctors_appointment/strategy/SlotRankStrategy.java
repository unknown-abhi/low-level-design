package com.lld.doctors_appointment.strategy;

import com.lld.doctors_appointment.dto.DoctorSlot;

import java.util.List;

public interface SlotRankStrategy {
    List<DoctorSlot> rank(List<DoctorSlot> slots);
}
