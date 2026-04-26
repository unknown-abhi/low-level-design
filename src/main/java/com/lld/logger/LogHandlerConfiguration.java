package com.lld.logger;

import com.lld.logger.appenders.LogAppender;
import com.lld.logger.enums.LogLevel;
import com.lld.logger.handlers.*;

public class LogHandlerConfiguration {

    private static final LogHandler debug = new DebugHandler();
    private static final LogHandler info = new InfoHandler();
    private static final LogHandler warn = new WarnHandler();
    private static final LogHandler error = new ErrorHandler();
    private static final LogHandler fatal = new FatalHandler();

    /**
     * Builds the build for this flow.
     * It assembles the required collaborators before returning the composed result.
     */
    public static LogHandler build() {
        debug.setNext(info);
        info.setNext(warn);
        warn.setNext(error);
        error.setNext(fatal);

        return debug;
    }

    /**
     * Handles add appender for level for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public static void addAppenderForLevel(LogLevel level, LogAppender appender) {
        switch (level) {
            case DEBUG -> debug.subscribe(appender);
            case INFO -> info.subscribe(appender);
            case WARN -> warn.subscribe(appender);
            case ERROR -> error.subscribe(appender);
            case FATAL -> fatal.subscribe(appender);
        }
    }
}
