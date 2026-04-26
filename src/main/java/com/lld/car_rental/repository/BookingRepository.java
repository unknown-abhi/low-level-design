package com.lld.car_rental.repository;

import com.lld.car_rental.model.Booking;

import java.util.*;

public class BookingRepository {
    private final Map<String, Booking> bookings = new HashMap<>();

    /**
     * Handles add booking for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void addBooking(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }

    /**
     * Handles get booking by id for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Optional<Booking> getBookingById(String bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }

    /**
     * Handles remove booking for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void removeBooking(String bookingId) {
        Booking booking = bookings.remove(bookingId);
        if (booking != null) {
            booking.getVehicle().getIsBooked().set(false);
        }
    }

    /**
     * Handles get all bookings for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }
}
