package com.lld.url_shortener.utils;

import java.net.URI;

public final class UrlValidator {
    private UrlValidator() {
    }

    public static boolean isValid(String url) {
        try {
            URI uri = URI.create(url);
            return uri.getScheme() != null
                    && uri.getHost() != null
                    && (uri.getScheme().equalsIgnoreCase("http") || uri.getScheme().equalsIgnoreCase("https"));
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }
}
