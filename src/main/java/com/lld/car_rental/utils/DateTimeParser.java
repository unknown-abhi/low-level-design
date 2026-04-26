package com.lld.car_rental.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeParser {
    /**
     * Parses the parse into the required representation.
     * It converts raw input into the structured value used by the module.
     */
    public static LocalDateTime parse(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM h:mm a yyyy", Locale.ENGLISH);
        return LocalDateTime.parse(input, formatter);
    }
}
