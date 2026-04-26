package com.lld.rate_limiter.limiter;

import com.lld.rate_limiter.enums.RateLimitType;
import com.lld.rate_limiter.model.RateLimitConfig;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SlidingWindowLogRateLimiter extends RateLimiter {
    private final Map<String, Queue<Long>> requestLog = new ConcurrentHashMap<>();

    /**
     * Creates a new SlidingWindowLogRateLimiter instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public SlidingWindowLogRateLimiter(RateLimitConfig config) {
        super(config, RateLimitType.SLIDING_WINDOW_LOG);
    }

    @Override
    /**
     * Handles allow request for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis() / 1000;

        requestLog.compute(userId, (id, log) -> {
            if (log == null)
                log = new ArrayDeque<>();

            while (!log.isEmpty() && (now - log.peek()) >= config.getWindowInSeconds()) {
                log.poll();
            }

            if (log.size() < config.getMaxRequests()) {
                log.add(now); // record this request
                allowed.set(true); // mark allowed
            }

            return log;
        });

        return allowed.get();
    }
}
