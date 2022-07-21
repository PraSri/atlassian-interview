package com.atlassian.code.service;

import java.util.HashMap;
import java.util.Map;

import static com.atlassian.code.service.TokenBucketRateLimiter.CAPACITY;
import static com.atlassian.code.service.TokenBucketRateLimiter.WINDOW_IN_SEC;

public class RulesRetriever {

    private Map<Integer, CustomerTokenBucket> customerTokensMap;

    public RulesRetriever() {
        customerTokensMap = new HashMap<>();
    }

    public long getAvailableTokens(int customerId){
        return customerTokensMap.get(customerId).getAvailableTokens();
    }

    public void refillBucket(int customerId) {
        customerTokensMap.get(customerId).refill();
    }

    public void createCustomerBucket(int customerId) {
        CustomerTokenBucket tokenBucket = new CustomerTokenBucket(customerId, CAPACITY, WINDOW_IN_SEC);
        customerTokensMap.put(customerId, tokenBucket);
    }

    public CustomerTokenBucket getCustomerTokenBucket(int customerId) {
        return customerTokensMap.get(customerId);
    }

}
