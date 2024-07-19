package com.adamsm2.backend.shared.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(final String message) {
        super(message);
    }
}
