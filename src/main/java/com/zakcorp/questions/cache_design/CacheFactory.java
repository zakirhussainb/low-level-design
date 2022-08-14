package com.zakcorp.questions.cache_design;

import com.zakcorp.questions.cache_design.policies.eviction.LRUEvictionPolicy;

public class CacheFactory<Key, Value> extends Cache<Key, Value> {

    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<>(new LRUEvictionPolicy<>(capacity));
    }
}
