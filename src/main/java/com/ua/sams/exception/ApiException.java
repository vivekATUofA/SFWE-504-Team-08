package com.ua.sams.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
