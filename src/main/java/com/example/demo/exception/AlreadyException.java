package com.example.demo.exception;

public class AlreadyException extends Exception {
    public  AlreadyException(String message) {
        super(message);
    }
    public  AlreadyException(String message, Exception exception) {
        super(message, exception);
    }
}
