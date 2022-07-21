package com.atlassian.code.service;

public class CustomerTokenBucket {

  private long customerId;
  private long capacity;
  private long windowTimeInSec;
  private long lastRefillTime;
  private double refillRatePerSec;
  private long availableTokens;

  public CustomerTokenBucket(int customerId, long capacity, long windowTimeInSec) {
    this.customerId = customerId;
    this.capacity = capacity;
    this.windowTimeInSec = windowTimeInSec;
    this.lastRefillTime = System.currentTimeMillis();
    this.refillRatePerSec = capacity/windowTimeInSec;
    this.availableTokens = 0;
  }

  public void refill() {
    long now = System.currentTimeMillis();
    if (now > lastRefillTime) {
      long timeDiff = now - lastRefillTime;
      double tokensToBeAdded = (timeDiff / 1000.0) * refillRatePerSec;
      if (tokensToBeAdded > 0.0) {
        availableTokens = Math.min(capacity, (long) tokensToBeAdded + this.availableTokens);
      }
    }
    lastRefillTime = now;
  }

  public long getAvailableTokens() {
    return this.availableTokens;
  }

  public void updateAvailableTokens(long newAvailableTokens) {
    this.availableTokens = newAvailableTokens;
  }
}
