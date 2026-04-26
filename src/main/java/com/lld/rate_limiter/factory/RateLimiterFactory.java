package com.lld.rate_limiter.factory;

import com.lld.rate_limiter.enums.RateLimitType;
import com.lld.rate_limiter.limiter.*;
import com.lld.rate_limiter.model.RateLimitConfig;

public class RateLimiterFactory {
    /**
     * Handles create rate limiter for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public static RateLimiter createRateLimiter(RateLimitType algo, RateLimitConfig config) {
        return switch (algo) {
            case TOKEN_BUCKET -> new TokenBucketRateLimiter(config);
            case FIXED_WINDOW -> new FixedWindowRateLimiter(config);
            case SLIDING_WINDOW_LOG -> new SlidingWindowLogRateLimiter(config);
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algo);
        };
    }
}
