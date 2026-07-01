package com.lld.url_shortener.strategy;

public interface ShortCodeGenerator {
    String generate(String originalUrl);
}
