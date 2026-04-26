package com.lld.logger.handlers;

import com.lld.logger.enums.LogLevel;

public class FatalHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.FATAL;
    }
}
