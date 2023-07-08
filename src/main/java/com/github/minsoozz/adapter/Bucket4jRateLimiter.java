package com.github.minsoozz.adapter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class Bucket4jRateLimiter implements RateLimiter {

    private final Cache cache;

    public Bucket4jRateLimiter(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("rate-limit-buckets");
    }

    @Override
    public Bucket resolve(String key) {
        Bucket bucket = cache.get(key, Bucket.class);
        if (bucket == null) {
            bucket = generate();
            cache.put(key, bucket);
        }
        return bucket;
    }

    @Override
    public Bucket generate() {
        Bandwidth limit = Bandwidth.classic(5, Refill.intervally(1, Duration.ofSeconds(10))); // 10초에 1번씩 5개의 토큰 생성
        return Bucket4j.builder().addLimit(limit).build();
    }

    @Override
    public void allowRequest(String key) {
        Bucket bucket = resolve(key);
        bucket.tryConsume(1);
    }
}
