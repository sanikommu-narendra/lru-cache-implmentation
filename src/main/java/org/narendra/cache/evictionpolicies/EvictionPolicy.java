package org.narendra.cache.evictionpolicies;

public interface EvictionPolicy<Key> {
    void keyAccessed(Key key);

    Key evictKey();
}
