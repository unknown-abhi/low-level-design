package com.lld.url_shortener.repository;

import com.lld.url_shortener.model.ShortUrl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UrlRepository {
    private final Map<String, ShortUrl> urlsByCode = new HashMap<>();
    private final Map<String, ShortUrl> urlsById = new HashMap<>();

    public void save(ShortUrl shortUrl) {
        urlsByCode.put(shortUrl.getShortCode(), shortUrl);
        urlsById.put(shortUrl.getId(), shortUrl);
    }

    public Optional<ShortUrl> findByShortCode(String shortCode) {
        return Optional.ofNullable(urlsByCode.get(shortCode));
    }

    public boolean existsByShortCode(String shortCode) {
        return urlsByCode.containsKey(shortCode);
    }

    public Collection<ShortUrl> findAll() {
        return urlsById.values();
    }
}
