package com.lld.logger;

import com.lld.logger.enums.LogLevel;
import com.lld.logger.handlers.*;
import com.lld.logger.model.LogMessage;

class Logger {
    private static final Logger INSTANCE = new Logger();

    private final LogHandler handlerChain;

    /**
     * Creates a new Logger instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    private Logger() {
        handlerChain = LogHandlerConfiguration.build();
    }

    /**
     * Handles get instance for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public static Logger getInstance() {
        return INSTANCE;
    }

    /**
     * Logs the log through the configured pipeline.
     * It forwards the message to the handler chain and appender stack.
     */
    public void log(LogLevel level, String message) {
        LogMessage msg = new LogMessage(level, message, System.currentTimeMillis());
        handlerChain.handle(msg);
    }

    /**
     * Handles debug for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void debug(String msg) {
        log(LogLevel.DEBUG, msg);
    }

    /**
     * Handles info for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void info(String msg) {
        log(LogLevel.INFO, msg);
    }

    /**
     * Handles warn for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void warn(String msg) {
        log(LogLevel.WARN, msg);
    }

    /**
     * Handles error for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void error(String msg) {
        log(LogLevel.ERROR, msg);
    }

    /**
     * Handles fatal for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void fatal(String msg) {
        log(LogLevel.FATAL, msg);
    }
}
