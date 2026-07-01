package com.lld.url_shortener.model;

import com.lld.url_shortener.enums.UrlStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ShortUrl {
    private final String id;
    private final String originalUrl;
    private final String shortCode;
    private final String createdBy;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiresAt;
    private UrlStatus status;
    private long clickCount;

    public ShortUrl(String id,
            String originalUrl,
            String shortCode,
            String createdBy,
            LocalDateTime expiresAt) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.expiresAt = expiresAt;
        this.status = UrlStatus.ACTIVE;
    }

    public boolean isExpired() {
        return expiresAt != null && LocalDateTime.now().isAfter(expiresAt);
    }

    public void incrementClickCount() {
        this.clickCount++;
    }
}
