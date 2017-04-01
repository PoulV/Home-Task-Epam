package com.tests.rtlabs.exceptions;

/**
 * Created by 1 on 01.04.2017.
 */
public class CustomIOException extends RuntimeException {
    public CustomIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
