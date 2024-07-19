package com.adamsm2.backend.shared.utils;

import com.adamsm2.backend.shared.exception.DeserializationFailedException;

public class DeserializationValidator {

    private DeserializationValidator() {
    }

    public static <T> void validateDeserializationOrThrow(T object) {
        if (object == null) {
            throw new DeserializationFailedException();
        }
    }
}
