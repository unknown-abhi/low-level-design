package com.lld.url_shortener.strategy;

import org.sqids.Sqids;

import java.util.Arrays;

public class SqidsCounterShortCodeGenerator implements ShortCodeGenerator {
    private final DistributedCounter distributedCounter;
    private final Sqids sqids;

    public SqidsCounterShortCodeGenerator(DistributedCounter distributedCounter) {
        this(distributedCounter, 7);
    }

    public SqidsCounterShortCodeGenerator(DistributedCounter distributedCounter, int minCodeLength) {
        if (distributedCounter == null) {
            throw new IllegalArgumentException("Distributed counter is required");
        }
        if (minCodeLength < 1) {
            throw new IllegalArgumentException("Minimum code length must be positive");
        }
        this.distributedCounter = distributedCounter;
        this.sqids = Sqids.builder()
                .minLength(minCodeLength)
                .build();
    }

    @Override
    public String generate(String originalUrl) {
        long id = distributedCounter.nextId();
        return sqids.encode(Arrays.asList(id));
    }
}
