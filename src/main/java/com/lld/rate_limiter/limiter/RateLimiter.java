package com.lld.rate_limiter.limiter;

import lombok.AllArgsConstructor;
import com.lld.rate_limiter.enums.RateLimitType;
import com.lld.rate_limiter.model.RateLimitConfig;

@AllArgsConstructor
public abstract class RateLimiter {
    protected final RateLimitConfig config;
    protected final RateLimitType type;

    /**
     * Handles allow request for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public abstract boolean allowRequest(String userId);
}
