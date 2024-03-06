package org.narendra.cache.exceptions;

public class KeyNotFoundException extends Exception{
    public KeyNotFoundException(String msg) {
        super(msg);
    }

    public KeyNotFoundException(String msg, Exception cause) {
        super(msg, cause);
    }
}
