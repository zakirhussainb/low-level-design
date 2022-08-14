package com.zakcorp.questions.cache_design.exceptions;

public class StorageFullException extends RuntimeException {
    public StorageFullException(String message) {
        super(message);
    }

    public StorageFullException() {
        super("Capacity Full....");
    }
}
