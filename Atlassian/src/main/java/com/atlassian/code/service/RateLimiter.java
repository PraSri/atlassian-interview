package com.atlassian.code.service;

public interface RateLimiter {

    Boolean rateLimit(int customerId);
}
