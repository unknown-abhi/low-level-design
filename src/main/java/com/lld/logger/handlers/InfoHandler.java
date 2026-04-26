package com.lld.logger.handlers;

import com.lld.logger.enums.LogLevel;

public class InfoHandler extends LogHandler {
    @Override
    /**
     * Handles can handle for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.INFO;
    }
}
