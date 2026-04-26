package com.lld.doctors_appointment.repository;

import lombok.Getter;
import com.lld.doctors_appointment.model.Booking;

import java.util.*;

@Getter
public class BookingRepository {
    private final Map<UUID, Booking> bookingMap = new HashMap<>();
    private final Map<String, Queue<UUID>> waitlist = new HashMap<>(); // slot+doctorId -> queue of patientIds

    // id-starttime
    // 123-9:30

    /**
     * Saves the save into the backing store.
     * It updates repository state so later operations can retrieve the same data.
     */
    public void save(Booking booking) {
        bookingMap.put(booking.getId(), booking);
    }

    /**
     * Handles delete for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void delete(Booking booking) {
        bookingMap.remove(booking.getId());
    }

    /**
     * Handles get booking by id for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Booking getBookingById(UUID id) {
        return bookingMap.get(id);
    }

    /**
     * Handles find by doctor for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public List<Booking> findByDoctor(UUID doctorId) {
        List<Booking> res = new ArrayList<>();
        for (Booking b : bookingMap.values()) {
            if (b.getDoctorId().equals(doctorId))
                res.add(b);
        }
        return res;
    }

    /**
     * Handles find by patient for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public List<Booking> findByPatient(UUID patientId) {
        List<Booking> res = new ArrayList<>();
        for (Booking b : bookingMap.values()) {
            if (b.getPatientId().equals(patientId))
                res.add(b);
        }
        return res;
    }

    /**
     * Handles add to waitlist for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void addToWaitlist(String doctorSlotKey, UUID patientId) {
        waitlist.putIfAbsent(doctorSlotKey, new LinkedList<>());
        waitlist.get(doctorSlotKey).offer(patientId);
    }

    /**
     * Handles pop from waitlist for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public UUID popFromWaitlist(String doctorSlotKey) {
        Queue<UUID> queue = waitlist.get(doctorSlotKey);
        return (queue != null) ? queue.poll() : null;
    }
}
