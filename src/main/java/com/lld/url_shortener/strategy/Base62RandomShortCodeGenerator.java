package com.lld.url_shortener.strategy;

import java.security.SecureRandom;

public class Base62RandomShortCodeGenerator implements ShortCodeGenerator {
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int DEFAULT_CODE_LENGTH = 7;

    private final SecureRandom random = new SecureRandom();
    private final int codeLength;

    public Base62RandomShortCodeGenerator() {
        this(DEFAULT_CODE_LENGTH);
    }

    public Base62RandomShortCodeGenerator(int codeLength) {
        this.codeLength = codeLength;
    }

    @Override
    public String generate(String originalUrl) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            code.append(BASE62.charAt(random.nextInt(BASE62.length())));
        }
        return code.toString();
    }
}
