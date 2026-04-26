package com.lld.doctors_appointment.utils;

import java.time.LocalTime;

public class Utils {
    /**
     * Handles convert string to local time for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public static LocalTime convertStringToLocalTime(String str) {
        String[] time = str.split(":");
        return LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
    }
}
