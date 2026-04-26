package com.lld.logger.appenders;

import lombok.RequiredArgsConstructor;
import com.lld.logger.model.LogMessage;
import com.lld.logger.formatter.LogFormatter;

@RequiredArgsConstructor
public class ConsoleAppender implements LogAppender {
    private final LogFormatter formatter;

    @Override
    public void append(LogMessage message) {
        System.out.println(formatter.format(message));
    }
}