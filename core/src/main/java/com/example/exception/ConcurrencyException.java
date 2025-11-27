package com.example.exception;

// 409 "reserving" exception
public class ConcurrencyException extends RuntimeException {
    public ConcurrencyException(String message) {
        super(message);
    }
}
