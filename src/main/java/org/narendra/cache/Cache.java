package org.narendra.cache;

import org.narendra.cache.evictionpolicies.EvictionPolicy;
import org.narendra.cache.exceptions.KeyNotFoundException;
import org.narendra.cache.exceptions.StorageFullException;
import org.narendra.cache.storage.Storage;

public class Cache<Key, Value> {
    Storage<Key, Value> storage;
    EvictionPolicy<Key> evictionPolicy;

    public Cache(Storage<Key, Value> storage, EvictionPolicy<Key> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public void put(Key key, Value value) throws KeyNotFoundException {
        try {
            this.storage.put(key, value);
            evictionPolicy.keyAccessed(key);
        } catch (StorageFullException e) {
            System.out.println("Got storage full. Will try to evict.");
            Key keyToRemove = evictionPolicy.evictKey();
            if (keyToRemove == null) {
                throw new RuntimeException("Unexpected State. Storage full and no key to evict.");
            }
            this.storage.remove(keyToRemove);
            System.out.println("Creating space by evicting item..." + keyToRemove);
            put(key, value);
        }
    }

    public Value get(Key key) {
        try {
            Value value = this.storage.get(key);
            this.evictionPolicy.keyAccessed(key);
            return value;
        } catch (KeyNotFoundException e) {
            System.out.println("Tried to access non-existing key.");
            return null;
        }
    }
}
