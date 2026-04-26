package com.lld.rate_limiter.service;

import com.lld.rate_limiter.enums.RateLimitType;
import com.lld.rate_limiter.enums.UserTier;
import com.lld.rate_limiter.factory.RateLimiterFactory;
import com.lld.rate_limiter.limiter.RateLimiter;
import com.lld.rate_limiter.model.RateLimitConfig;
import com.lld.rate_limiter.model.User;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {
    private final Map<UserTier, RateLimiter> rateLimiters = new HashMap<>();

    /**
     * Creates a new RateLimiterService instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public RateLimiterService() {
        // Configure per-tier limits + algorithms
        rateLimiters.put(
                UserTier.FREE,
                RateLimiterFactory.createRateLimiter(
                        RateLimitType.TOKEN_BUCKET,
                        new RateLimitConfig(10, 60) // 10 req/min
                ));

        rateLimiters.put(
                UserTier.PREMIUM,
                RateLimiterFactory.createRateLimiter(
                        RateLimitType.FIXED_WINDOW,
                        new RateLimitConfig(100, 60) // 100 req/min
                ));
    }

    /**
     * Handles allow request for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public boolean allowRequest(User user) {
        RateLimiter limiter = rateLimiters.get(user.getTier());
        if (limiter == null) {
            throw new IllegalArgumentException("No limiter configured for tier: " + user.getTier());
        }
        return limiter.allowRequest(user.getUserId());
    }
}
