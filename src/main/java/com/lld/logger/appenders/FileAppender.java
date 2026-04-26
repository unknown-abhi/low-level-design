package com.lld.logger.appenders;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import com.lld.logger.model.LogMessage;
import com.lld.logger.formatter.LogFormatter;

public class FileAppender implements LogAppender {
    private final LogFormatter formatter;
    private final BufferedWriter writer;

    /**
     * Creates a new FileAppender instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public FileAppender(LogFormatter formatter, String fileName) {
        this.formatter = formatter;

        try {
            this.writer = new BufferedWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            throw new RuntimeException("Failed to open log file", e);
        }
    }

    // t1, t2, t3,
    @Override
    /**
     * Handles append for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public synchronized void append(LogMessage message) {
        // t4, t5

        // blocking queue -> worker threads
        try {
            writer.write(formatter.format(message));
            writer.newLine();
            writer.flush(); // flush can be batched or delayed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // t1 t2 t3

    // blocking queue of cap = 3
    // 3 worker threads

    /**
     * Handles close for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public synchronized void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
