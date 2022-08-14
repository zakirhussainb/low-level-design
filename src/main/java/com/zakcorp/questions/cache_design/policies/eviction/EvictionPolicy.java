package com.zakcorp.questions.cache_design.policies.eviction;

public interface EvictionPolicy<Key, Value> {
    Value get(Key key);

    void put(Key key, Value value);

    Key evictKey();
}
