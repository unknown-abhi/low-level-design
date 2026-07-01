package com.lld.url_shortener.service;

import com.lld.url_shortener.model.ClickEvent;
import com.lld.url_shortener.repository.ClickEventRepository;

import java.util.List;

public class AnalyticsService {
    private final ClickEventRepository clickEventRepository;

    public AnalyticsService(ClickEventRepository clickEventRepository) {
        this.clickEventRepository = clickEventRepository;
    }

    public void recordClick(String shortCode, String userAgent, String ipAddress) {
        clickEventRepository.save(new ClickEvent(shortCode, userAgent, ipAddress));
    }

    public List<ClickEvent> getClickEvents(String shortCode) {
        return clickEventRepository.findByShortCode(shortCode);
    }

    public int getTotalClicks(String shortCode) {
        return getClickEvents(shortCode).size();
    }
}
