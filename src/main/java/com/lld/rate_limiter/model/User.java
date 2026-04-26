package com.lld.rate_limiter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.lld.rate_limiter.enums.UserTier;

@Getter
@AllArgsConstructor
public class User {
    private final String userId;
    private final UserTier tier;
}