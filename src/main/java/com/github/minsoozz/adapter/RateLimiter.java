package com.github.minsoozz.adapter;

import io.github.bucket4j.Bucket;

public interface RateLimiter {

    Bucket resolve(String key);

    Bucket generate();

    void allowRequest(String key);
}
