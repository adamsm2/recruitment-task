package com.adamsm2.backend.dto;

public record ExceptionResource(
        int status,
        String message
) {
}
