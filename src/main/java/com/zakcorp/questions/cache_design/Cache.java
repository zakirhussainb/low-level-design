package com.zakcorp.questions.cache_design;

import com.zakcorp.questions.cache_design.exceptions.CacheKeyNotFoundException;
import com.zakcorp.questions.cache_design.exceptions.StorageFullException;
import com.zakcorp.questions.cache_design.policies.eviction.EvictionPolicy;

public class Cache<Key, Value> {
    private EvictionPolicy<Key, Value> evictionPolicy;

    public Cache(EvictionPolicy<Key, Value> evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
    }

    public Cache() {}

    public void put(Key key, Value value) {
        try {
            this.evictionPolicy.put(key, value);
        } catch (StorageFullException storageFullException) {
            System.out.println("Storage is Full, Need to evict a key based on the eviction policy");
            Key keyToRemove = evictionPolicy.evictKey();
            if(keyToRemove == null)
                throw new RuntimeException("Unexpected State. Storage full and no key to evict.");
            put(key, value);
        }
    }

    public Value get(Key key) {
        try {
            return evictionPolicy.get(key);
        } catch (CacheKeyNotFoundException cke) {
            System.out.println("Tried to access non-existent key");
            return null;
        }
    }
}
