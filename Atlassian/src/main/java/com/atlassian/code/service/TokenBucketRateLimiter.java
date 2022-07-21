package com.atlassian.code.service;

public class TokenBucketRateLimiter implements RateLimiter {

  public static long CAPACITY = 10;
  public static long WINDOW_IN_SEC = 1;

  private final RulesRetriever retriever;

  public TokenBucketRateLimiter() {
    this.retriever = new RulesRetriever();
  }

  @Override
  public Boolean rateLimit(int customerId) {
    CustomerTokenBucket bucket = retriever.getCustomerTokenBucket(customerId);
    long availableTokensForCustomer = 0;
    if (bucket == null) {
      retriever.createCustomerBucket(customerId);
      retriever.refillBucket(customerId);
      availableTokensForCustomer = retriever.getAvailableTokens(customerId);
      if (availableTokensForCustomer > 0) {
        availableTokensForCustomer = availableTokensForCustomer - 1;
        bucket.updateAvailableTokens(availableTokensForCustomer);
        return true;
      }
    } else {
      retriever.refillBucket(customerId);
      availableTokensForCustomer = retriever.getAvailableTokens(customerId);
      if (availableTokensForCustomer > 0) {
        availableTokensForCustomer = availableTokensForCustomer - 1;
        bucket.updateAvailableTokens(availableTokensForCustomer);
        return true;
      }
    }
    return false;
  }
}
