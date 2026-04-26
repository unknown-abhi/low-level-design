package com.lld.logger.appenders;

import lombok.RequiredArgsConstructor;
import com.lld.logger.model.LogMessage;
import com.lld.logger.formatter.LogFormatter;

@RequiredArgsConstructor
public class ConsoleAppender implements LogAppender {
    private final LogFormatter formatter;

    @Override
    /**
     * Handles append for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void append(LogMessage message) {
        System.out.println(formatter.format(message));
    }
}
