package com.lld.logger.handlers;

import lombok.Setter;
import com.lld.logger.model.LogMessage;
import com.lld.logger.enums.LogLevel;
import com.lld.logger.appenders.LogAppender;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class LogHandler {
    @Setter
    protected LogHandler next;
    protected final List<LogAppender> appenders = new CopyOnWriteArrayList<>();

    /**
     * Subscribes the subscribe to this publisher.
     * It records the observer so future events are delivered to it.
     */
    public void subscribe(LogAppender observer) {
        appenders.add(observer);
    }

    /**
     * Handles notify observers for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void notifyObservers(LogMessage message) {
        for (LogAppender appender : appenders) {
            appender.append(message);
        }
    }

    /**
     * Handles the handle at this stage of the pipeline.
     * It performs its check and either finishes the work or passes control onward.
     */
    public void handle(LogMessage message) {
        if (canHandle(message.getLevel())) {
            notifyObservers(message);
        } else if (next != null) {
            next.handle(message);
        }
    }

    /**
     * Handles can handle for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    protected abstract boolean canHandle(LogLevel level);
}

// [console, file]

// removes the file
