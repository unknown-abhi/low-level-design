package com.lld.url_shortener;

import com.lld.url_shortener.model.ShortUrl;
import com.lld.url_shortener.repository.ClickEventRepository;
import com.lld.url_shortener.repository.UrlRepository;
import com.lld.url_shortener.service.AnalyticsService;
import com.lld.url_shortener.service.UrlShortenerService;
import com.lld.url_shortener.strategy.Base62CounterShortCodeGenerator;
import com.lld.url_shortener.strategy.InMemoryDistributedCounter;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        UrlRepository urlRepository = new UrlRepository();
        ClickEventRepository clickEventRepository = new ClickEventRepository();
        AnalyticsService analyticsService = new AnalyticsService(clickEventRepository);

        UrlShortenerService urlShortenerService = new UrlShortenerService(
                "https://sho.rt",
                urlRepository,
                analyticsService,
                new Base62CounterShortCodeGenerator(new InMemoryDistributedCounter()));

        ShortUrl shortUrl = urlShortenerService.createShortUrl(
                "https://example.com/products/123?source=newsletter",
                "abhishek@example.com",
                LocalDateTime.now().plusDays(30));

        String originalUrl = urlShortenerService.redirect(
                shortUrl.getShortCode(),
                "Chrome",
                "127.0.0.1");

        System.out.println("Redirecting to: " + originalUrl);
        System.out.println("Clicks: " + analyticsService.getTotalClicks(shortUrl.getShortCode()));

        urlShortenerService.disableUrl(shortUrl.getShortCode());
        System.out.println("Status after disable: " + shortUrl.getStatus());
    }
}
