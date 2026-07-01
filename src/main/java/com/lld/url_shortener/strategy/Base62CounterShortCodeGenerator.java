package com.lld.url_shortener.strategy;

public class Base62CounterShortCodeGenerator implements ShortCodeGenerator {
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private final DistributedCounter distributedCounter;
    private final int minCodeLength;

    public Base62CounterShortCodeGenerator(DistributedCounter distributedCounter) {
        this(distributedCounter, 7);
    }

    public Base62CounterShortCodeGenerator(DistributedCounter distributedCounter, int minCodeLength) {
        if (distributedCounter == null) {
            throw new IllegalArgumentException("Distributed counter is required");
        }
        if (minCodeLength < 1) {
            throw new IllegalArgumentException("Minimum code length must be positive");
        }
        this.distributedCounter = distributedCounter;
        this.minCodeLength = minCodeLength;
    }

    @Override
    public String generate(String originalUrl) {
        long id = distributedCounter.nextId();
        return leftPad(encodeBase62(id));
    }

    private String encodeBase62(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        if (id == 0) {
            return String.valueOf(BASE62.charAt(0));
        }

        StringBuilder encoded = new StringBuilder();
        long current = id;
        while (current > 0) {
            int remainder = (int) (current % BASE62.length());
            encoded.append(BASE62.charAt(remainder));
            current = current / BASE62.length();
        }
        return encoded.reverse().toString();
    }

    private String leftPad(String code) {
        if (code.length() >= minCodeLength) {
            return code;
        }

        StringBuilder paddedCode = new StringBuilder();
        while (paddedCode.length() + code.length() < minCodeLength) {
            paddedCode.append(BASE62.charAt(0));
        }
        return paddedCode.append(code).toString();
    }
}
