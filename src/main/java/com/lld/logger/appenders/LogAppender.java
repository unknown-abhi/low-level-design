package com.lld.logger.appenders;

import com.lld.logger.model.LogMessage;

public interface LogAppender {
    void append(LogMessage message);
}
