package com.zakcorp.questions.cache_design.exceptions;

public class CacheKeyNotFoundException extends RuntimeException {
    public CacheKeyNotFoundException(String key) {
        super(key + " doesn't exist in the cache");
    }
}
