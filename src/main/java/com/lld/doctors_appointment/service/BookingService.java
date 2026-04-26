package com.lld.doctors_appointment.service;

import lombok.AllArgsConstructor;
import com.lld.doctors_appointment.enums.Specialization;
import com.lld.doctors_appointment.exception.BookingNotFoundException;
import com.lld.doctors_appointment.model.Booking;
import com.lld.doctors_appointment.model.Doctor;
import com.lld.doctors_appointment.dto.DoctorSlot;
import com.lld.doctors_appointment.model.Patient;
import com.lld.doctors_appointment.repository.BookingRepository;
import com.lld.doctors_appointment.repository.DoctorRepository;
import com.lld.doctors_appointment.repository.PatientRepository;
import com.lld.doctors_appointment.strategy.SlotRankStrategy;

import java.util.*;

@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    /**
     * Handles search for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public List<DoctorSlot> search(Specialization spec, SlotRankStrategy strategy) {
        List<Doctor> doctors = doctorRepo.findBySpecialization(spec);
        List<DoctorSlot> result = new ArrayList<>();

        for (Doctor d : doctors) {
            for (Map.Entry<String, Boolean> e : d.getAvailability().entrySet()) {
                if (e.getValue())
                    result.add(new DoctorSlot(d, e.getKey()));
            }
        }
        return strategy.rank(result);
    }

    /**
     * Books the book for the caller.
     * It checks availability, creates the booking, and updates the participating entities.
     */
    public Booking book(UUID patientId, UUID doctorId, String slot) {
        Doctor doctor = doctorRepo.findById(doctorId);
        Map<String, Boolean> availability = doctor.getAvailability();

        // Slot not declared
        if (!availability.containsKey(slot)) {
            throw new RuntimeException("Invalid slot: Doctor has not declared availability for this slot.");
        }

        // Patient already has a booking in this slot
        for (Booking b : bookingRepo.findByPatient(patientId)) {
            if (b.getSlot().equals(slot)) {
                throw new RuntimeException("Patient already has an appointment at this time");
            }
        }

        // Book if slot is available
        if (availability.get(slot)) {
            Booking booking = new Booking(patientId, doctorId, slot);
            bookingRepo.save(booking);
            availability.put(slot, false); // mark slot as booked

            System.out.println("\n" + patientRepo.findById(patientId).getName()
                    + " booked a slot successfully for slot : " + slot);

            return booking;
        } else {
            // Add to waitlist if valid but booked
            String key = doctorId.toString() + "-" + slot;
            bookingRepo.addToWaitlist(key, patientId);
            throw new RuntimeException("Slot already booked. Added to waitlist.");
        }
    }

    /**
     * Handles cancel for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void cancel(UUID bookingId) {
        Booking booking = bookingRepo.getBookingById(bookingId);
        if (booking == null)
            throw new BookingNotFoundException("Booking not found");

        Doctor doctor = doctorRepo.findById(booking.getDoctorId());
        doctor.getAvailability().put(booking.getSlot(), true); // Mark slot as available
        bookingRepo.delete(booking);

        System.out.println("\n" + patientRepo.findById(booking.getPatientId()).getName()
                + " cancelled the booking for slot : " + booking.getSlot());

        // Promote first patient in waitlist
        String key = doctor.getId().toString() + "-" + booking.getSlot();
        UUID nextPatient = bookingRepo.popFromWaitlist(key);
        if (nextPatient != null) {
            book(nextPatient, doctor.getId(), booking.getSlot());
        }
    }

    /**
     * Handles view bookings by doctor for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public List<Booking> viewBookingsByDoctor(UUID doctorId) {
        return bookingRepo.findByDoctor(doctorId);
    }

    /**
     * Handles view bookings by patient for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public List<Booking> viewBookingsByPatient(UUID patientId) {
        return bookingRepo.findByPatient(patientId);
    }
}
