package com.lld.url_shortener.repository;

import com.lld.url_shortener.model.ClickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClickEventRepository {
    private final Map<String, List<ClickEvent>> clickEventsByCode = new HashMap<>();

    public void save(ClickEvent clickEvent) {
        clickEventsByCode
                .computeIfAbsent(clickEvent.getShortCode(), code -> new ArrayList<>())
                .add(clickEvent);
    }

    public List<ClickEvent> findByShortCode(String shortCode) {
        return new ArrayList<>(clickEventsByCode.getOrDefault(shortCode, new ArrayList<>()));
    }
}
