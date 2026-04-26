package com.lld.logger.formatter;

import com.lld.logger.model.LogMessage;

public interface LogFormatter {
    String format(LogMessage message);
}
