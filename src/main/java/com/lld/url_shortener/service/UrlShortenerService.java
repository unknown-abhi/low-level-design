package com.lld.url_shortener.service;

import com.lld.url_shortener.enums.UrlStatus;
import com.lld.url_shortener.model.ShortUrl;
import com.lld.url_shortener.repository.UrlRepository;
import com.lld.url_shortener.strategy.ShortCodeGenerator;
import com.lld.url_shortener.utils.UrlValidator;

import java.time.LocalDateTime;
import java.util.UUID;

public class UrlShortenerService {
    private static final int MAX_CODE_GENERATION_ATTEMPTS = 5;

    private final String domain;
    private final UrlRepository urlRepository;
    private final AnalyticsService analyticsService;
    private final ShortCodeGenerator shortCodeGenerator;

    public UrlShortenerService(String domain,
            UrlRepository urlRepository,
            AnalyticsService analyticsService,
            ShortCodeGenerator shortCodeGenerator) {
        this.domain = domain;
        this.urlRepository = urlRepository;
        this.analyticsService = analyticsService;
        this.shortCodeGenerator = shortCodeGenerator;
    }

    public ShortUrl createShortUrl(String originalUrl, String createdBy, LocalDateTime expiresAt) {
        if (!UrlValidator.isValid(originalUrl)) {
            throw new IllegalArgumentException("Invalid URL");
        }

        String shortCode = generateUniqueShortCode(originalUrl);
        ShortUrl shortUrl = new ShortUrl(
                UUID.randomUUID().toString(),
                originalUrl,
                shortCode,
                createdBy,
                expiresAt);

        urlRepository.save(shortUrl);
        System.out.println(">>> Short URL created: " + buildShortUrl(shortCode));
        return shortUrl;
    }

    public String redirect(String shortCode, String userAgent, String ipAddress) {
        ShortUrl shortUrl = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new IllegalArgumentException("Short URL not found"));

        if (shortUrl.getStatus() == UrlStatus.DISABLED) {
            throw new IllegalStateException("Short URL is disabled");
        }

        if (shortUrl.isExpired()) {
            shortUrl.setStatus(UrlStatus.EXPIRED);
            throw new IllegalStateException("Short URL is expired");
        }

        shortUrl.incrementClickCount();
        analyticsService.recordClick(shortCode, userAgent, ipAddress);
        return shortUrl.getOriginalUrl();
    }

    public void disableUrl(String shortCode) {
        ShortUrl shortUrl = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new IllegalArgumentException("Short URL not found"));
        shortUrl.setStatus(UrlStatus.DISABLED);
    }

    public String buildShortUrl(String shortCode) {
        return domain + "/" + shortCode;
    }

    private String generateUniqueShortCode(String originalUrl) {
        for (int attempt = 0; attempt < MAX_CODE_GENERATION_ATTEMPTS; attempt++) {
            String shortCode = shortCodeGenerator.generate(originalUrl);
            if (!urlRepository.existsByShortCode(shortCode)) {
                return shortCode;
            }
        }
        throw new IllegalStateException("Unable to generate unique short code");
    }
}
