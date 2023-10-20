package com.example.bowling.controllers.exceptionHandlers;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}