package org.narendra.cache.storage;

import org.narendra.cache.exceptions.KeyNotFoundException;
import org.narendra.cache.exceptions.StorageFullException;

public interface Storage<Key, Value> {
    void put(Key key, Value value) throws StorageFullException;

    Value get(Key key) throws KeyNotFoundException;

    void remove(Key key) throws KeyNotFoundException;
}
