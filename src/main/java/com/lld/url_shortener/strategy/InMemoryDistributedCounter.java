package com.lld.url_shortener.strategy;

import java.util.concurrent.atomic.AtomicLong;

public class InMemoryDistributedCounter implements DistributedCounter {
    private final AtomicLong counter;

    public InMemoryDistributedCounter() {
        this(1);
    }

    public InMemoryDistributedCounter(long initialValue) {
        if (initialValue < 0) {
            throw new IllegalArgumentException("Initial value cannot be negative");
        }
        this.counter = new AtomicLong(initialValue);
    }

    @Override
    public long nextId() {
        return counter.getAndIncrement();
    }
}
