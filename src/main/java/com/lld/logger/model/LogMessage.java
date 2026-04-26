package com.lld.logger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.lld.logger.enums.LogLevel;

@AllArgsConstructor
@Getter
public class LogMessage {
    private LogLevel level;
    private String message;
    private long timestamp;
}
