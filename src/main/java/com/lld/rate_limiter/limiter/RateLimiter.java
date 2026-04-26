package com.lld.rate_limiter.limiter;

import lombok.AllArgsConstructor;
import com.lld.rate_limiter.enums.RateLimitType;
import com.lld.rate_limiter.model.RateLimitConfig;

@AllArgsConstructor
public abstract class RateLimiter {
    protected final RateLimitConfig config;
    protected final RateLimitType type;

    public abstract boolean allowRequest(String userId);
}
