# URL Shortener LLD

Low-level design for a URL shortener similar to Bitly or TinyURL.

## Requirements

- Create a short URL for a valid long URL.
- Redirect a short code to the original URL.
- Support expiry and manual disable of a short URL.
- Track click analytics.
- Keep short-code generation pluggable.

## Package Structure

```text
url_shortener/
|-- enums/          # URL lifecycle states
|-- model/          # ShortUrl, ClickEvent
|-- repository/     # In-memory persistence
|-- service/        # Shortening, redirect, analytics
|-- strategy/       # Short-code generation strategy
|-- utils/          # URL validation helper
`-- Main.java       # Demo runner
```

## Design Patterns Used

| Pattern | Purpose |
|---------|---------|
| Strategy | Swap short-code generation algorithms |
| Repository | Hide storage details behind simple data access classes |
| Service Layer | Keep business rules out of models and repositories |

## Core Classes

- `ShortUrl`: stores original URL, short code, owner, status, expiry, and click count.
- `UrlShortenerService`: creates short URLs, resolves redirects, disables links.
- `AnalyticsService`: records and reads click events.
- `ShortCodeGenerator`: interface for generating short codes.
- `Base62RandomShortCodeGenerator`: random base62 code generator.
- `Base62CounterShortCodeGenerator`: gets a globally unique counter value and encodes it as base62.
- `DistributedCounter`: abstraction for a Redis/ZooKeeper/database-backed sequence generator.

## Flow

```text
Create URL
Client -> UrlShortenerService -> UrlValidator -> ShortCodeGenerator -> UrlRepository

With distributed counter strategy:
Client -> UrlShortenerService -> DistributedCounter -> Base62 encoding -> UrlRepository

Redirect
Client -> UrlShortenerService -> UrlRepository -> AnalyticsService -> Original URL
```

## Example

```java
ShortUrl shortUrl = urlShortenerService.createShortUrl(
        "https://example.com/products/123",
        "user@example.com",
        LocalDateTime.now().plusDays(30));

String originalUrl = urlShortenerService.redirect(
        shortUrl.getShortCode(),
        "Chrome",
        "127.0.0.1");
```

## Extensions

- Add custom aliases.
- Add user-level quotas.
- Add distributed ID generation.
- Replace `InMemoryDistributedCounter` with Redis `INCR`, ZooKeeper sequence nodes, database sequence, or Snowflake-style ID service.
- Add cache for hot links.
- Add sharded storage for high scale.
- Add rate limiting and abuse detection.
