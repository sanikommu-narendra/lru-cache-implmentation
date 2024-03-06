package org.narendra.cache.factory;

import org.narendra.cache.Cache;
import org.narendra.cache.evictionpolicies.LRUEvictionPolicy;
import org.narendra.cache.storage.HashMapStorage;

public class CacheFactory<Key, Value> {
    public Cache<Key, Value> getDefaultCache(Integer capacity) {
        return new Cache<>(new HashMapStorage<>(capacity), new LRUEvictionPolicy<>());
    }
}
