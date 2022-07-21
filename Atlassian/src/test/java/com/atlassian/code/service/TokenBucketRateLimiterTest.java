package com.atlassian.code.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenBucketRateLimiterTest {

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void testTokenBucketRateLimiterRateLimit() {

    // send 10 request in 1 sec => allowed => return true
    // 11th request in 1 sec => not allowed => return false

    int customerId = 100;

    TokenBucketRateLimiter limiter = new TokenBucketRateLimiter();

    for (int i = 1; i <= 11; i++) {
      boolean isAllowed = limiter.rateLimit(customerId);
      if (i == 11) {
        assertFalse(isAllowed);
      } else {
        assertTrue(isAllowed);
      }
    }
  }
}
