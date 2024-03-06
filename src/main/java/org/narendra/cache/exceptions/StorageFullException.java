package org.narendra.cache.exceptions;

public class StorageFullException extends Exception {
    public StorageFullException(String msg) {
        super(msg);
    }

    public StorageFullException(String msg, Exception exception) {
        super(msg, exception);
    }
}
