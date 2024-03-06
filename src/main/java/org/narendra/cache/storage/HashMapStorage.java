package org.narendra.cache.storage;

import org.narendra.cache.exceptions.KeyNotFoundException;
import org.narendra.cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<Key, Value> implements Storage<Key, Value> {
    private final Map<Key, Value> storage;
    private final Integer capacity;

    public HashMapStorage(Integer capacity) {
        this.storage = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public void put(Key key, Value value) throws StorageFullException {
        if(isStorageFull())
            throw new StorageFullException("Key: " + key + ", Value: " + value);
        storage.put(key, value);
    }

    @Override
    public Value get(Key key) throws KeyNotFoundException {
        if(!storage.containsKey(key))
            throw new KeyNotFoundException(key + "does not exist in cache");
        return storage.get(key);
    }

    @Override
    public void remove(Key key) throws KeyNotFoundException {
        if(!storage.containsKey(key))
            throw new KeyNotFoundException(key + "does not exist in cache");
        storage.remove(key);
    }

    private Boolean isStorageFull() {
        return capacity == storage.size();
    }
}
