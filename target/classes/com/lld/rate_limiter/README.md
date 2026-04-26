# Rate Limiter System

A sophisticated rate limiting system implementing token bucket and sliding window algorithms to control API request traffic.

## 📋 Overview

This module implements production-grade rate limiting with:
- Token bucket algorithm
- Sliding window counter
- Distributed rate limiting
- User-based and IP-based limiting
- Configurable time windows
- Real-time quota monitoring

## 🏗️ Architecture

### Package Structure
```
rate_limiter/
├── enums/          # TimeWindow, LimitType
├── factory/        # RateLimiterFactory
├── limiter/        # Token bucket and sliding window implementations
├── model/          # RateLimitConfig, User, Request
├── service/        # RateLimiterService
└── Main.java      # Entry point
```

## 📐 UML Class Diagram

```
┌────────────────────────────────────────────────────────────┐
│              Rate Limiter System Architecture               │
└────────────────────────────────────────────────────────────┘

    ┌──────────────────────────────┐
    │  RateLimiter (Interface)     │
    ├──────────────────────────────┤
    │ + allowRequest()             │
    │ + getRemainingQuota()        │
    │ + reset()                    │
    └──────────────────────────────┘
             △         △
             │         │
       ┌─────┴─────┬──┴──────────┐
       │           │             │
   TokenBucket  SlidingWindow  LeakyBucket
     Limiter      Counter       Limiter

┌──────────────────────────────┐
│    TokenBucketLimiter        │
├──────────────────────────────┤
│ - capacity: long             │
│ - tokens: long               │
│ - refillRate: long           │
│ - lastRefillTime: long       │
├──────────────────────────────┤
│ + allowRequest(count): bool  │
│ - refillTokens()             │
│ - getAvailableTokens()       │
└──────────────────────────────┘

┌──────────────────────────────┐
│  SlidingWindowLimiter        │
├──────────────────────────────┤
│ - windowSize: long           │
│ - maxRequests: int           │
│ - requests: Queue<Long>      │
├──────────────────────────────┤
│ + allowRequest(): bool       │
│ - cleanOldRequests()         │
│ - getRequestCount()          │
└──────────────────────────────┘

┌──────────────────────────────┐
│   RateLimiterConfig          │
├──────────────────────────────┤
│ - requestsPerSecond: int     │
│ - requestsPerMinute: int     │
│ - requestsPerHour: int       │
│ - burstSize: int             │
│ - algorithm: String          │
└──────────────────────────────┘

┌──────────────────────────────┐
│    RateLimiterService        │
├──────────────────────────────┤
│ - limiters: Map<User, RL>    │
│ - configs: Map<String, Cfg>  │
├──────────────────────────────┤
│ + isAllowed(user, request)   │
│ + createLimiter(config)      │
│ + resetUser(user)            │
│ + getStats(user)             │
└──────────────────────────────┘
```

## 🔑 Key Features

### 1. **Token Bucket Algorithm**
```
Tokens = min(capacity, tokens + rate * time_elapsed)
If tokens >= cost: Allow request, tokens -= cost
Else: Deny request
```

**Characteristics:**
- Allows burst traffic
- Smooth token refill
- Simple implementation
- Configurable capacity

### 2. **Sliding Window Counter**
```
Remove requests older than window
If current_count < limit: Allow
Else: Deny
```

**Characteristics:**
- Precise window enforcement
- No burst allowance
- Higher memory usage
- Better fairness

### 3. **Leaky Bucket Algorithm**
- Processes requests at constant rate
- Excess requests overflow (discarded)
- Queue-based approach
- Fixed output rate

### 4. **Limiting Strategies**

| Strategy | Use Case |
|----------|----------|
| **Per-User** | Limit by user ID |
| **Per-IP** | Limit by IP address |
| **Per-Endpoint** | Limit by API endpoint |
| **Global** | System-wide limit |

### 5. **Time Windows**
- Per-second limits
- Per-minute limits
- Per-hour limits
- Per-day limits
- Custom windows

## 💻 Usage Example

```java
// Create rate limiter service
RateLimiterService service = new RateLimiterService();

// Configure token bucket (100 req/sec, burst 500)
RateLimitConfig config = RateLimitConfig.builder()
    .algorithm("TOKEN_BUCKET")
    .requestsPerSecond(100)
    .burstSize(500)
    .build();

User user = new User("user123");
RateLimiter limiter = service.createLimiter(user, config);

// Check if request allowed
if (limiter.allowRequest(1)) {
    System.out.println("Request allowed");
} else {
    System.out.println("Rate limit exceeded");
    System.out.println("Remaining quota: " + limiter.getRemainingQuota());
}
```

## 🎯 Design Patterns Used

| Pattern | Purpose |
|---------|---------|
| **Strategy** | Different limiting algorithms |
| **Factory** | Create appropriate limiter |
| **Builder** | Configuration setup |
| **Singleton** | Rate limiter service |

## 📊 Algorithm Comparison

| Aspect | Token Bucket | Sliding Window | Leaky Bucket |
|--------|--------------|----------------|-------------|
| Burst Support | ✅ Yes | ❌ No | ✅ Limited |
| Memory | ⭐⭐⭐ Low | ⭐⭐ Medium | ⭐⭐ Medium |
| Precision | ⭐⭐ Good | ⭐⭐⭐ Exact | ⭐⭐ Good |
| Implementation | ⭐⭐⭐ Simple | ⭐⭐ Complex | ⭐⭐⭐ Simple |

## ✅ Core Methods

### RateLimiter Interface
- `allowRequest()` - Check if request allowed
- `allowRequest(count)` - Allow multiple units
- `getRemainingQuota()` - Get available quota
- `reset()` - Reset limiter state

### RateLimiterService
- `createLimiter(user, config)` - Create limiter
- `isAllowed(user, request)` - Check permission
- `resetUser(user)` - Reset user limit
- `getStats(user)` - Get usage statistics

## 📈 Real-World Application

```
API Rate Limiting Tiers:

Free Tier:
- 10 requests/minute
- 100 requests/hour

Standard Tier:
- 100 requests/minute
- 10,000 requests/hour

Premium Tier:
- 1000 requests/minute
- Unlimited requests/hour
```

## 🧪 Testing Scenarios

Test cases should cover:
- Single request allowed/denied
- Burst traffic handling
- Token refill timing
- Multiple users isolation
- Window boundary conditions
- Concurrent requests
- Configuration changes
- Reset functionality

## 🔍 Example Execution

```
TokenBucket (capacity=10, rate=5/sec):

t=0s: tokens=10, request arrives → allowed, tokens=9
t=0.2s: request arrives → allowed, tokens=8
t=0.4s: request arrives → allowed, tokens=7
t=0.5s: refill=2.5≈2, tokens=9, request → allowed, tokens=8
t=0.6s: request arrives → allowed, tokens=7
...
t=2s: all tokens replenished, tokens=10
```

## 🚨 Rate Limit Headers

Typical HTTP response headers:
```
X-RateLimit-Limit: 100
X-RateLimit-Remaining: 75
X-RateLimit-Reset: 1640000000
Retry-After: 30
```

---

**Back to [Parent README](../README.md)**
