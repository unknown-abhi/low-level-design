package com.lld.logger.formatter;

import com.lld.logger.model.LogMessage;

import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PlainTextFormatter implements LogFormatter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    /**
     * Handles format for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public String format(LogMessage message) {
        String formattedTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(message.getTimestamp()),
                ZoneId.systemDefault()).format(FORMATTER);

        return String.format("%s [%s] - %s", formattedTime, message.getLevel(), message.getMessage());
    }
}
