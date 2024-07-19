package com.adamsm2.backend.shared.exception;

public class DeserializationFailedException extends RuntimeException {

    public DeserializationFailedException() {
        super("Deserialization failed");
    }
}
