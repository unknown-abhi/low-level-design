package com.lld.url_shortener.model;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class ClickEvent {
    private final String shortCode;
    private final String userAgent;
    private final String ipAddress;
    private final LocalDateTime clickedAt;

    public ClickEvent(String shortCode, String userAgent, String ipAddress) {
        this.shortCode = shortCode;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
        this.clickedAt = LocalDateTime.now();
    }
}
