package com.adamsm2.backend.shared.exception.handler;

import com.adamsm2.backend.dto.ExceptionResource;
import com.adamsm2.backend.shared.exception.DeserializationFailedException;
import com.adamsm2.backend.shared.exception.GithubServiceUnavailableException;
import com.adamsm2.backend.shared.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class RestResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    ResponseEntity<ExceptionResource> handleNoSuchElementException(final ItemNotFoundException exception) {
        final var exceptionResource = new ExceptionResource(404, exception.getMessage());
        return new ResponseEntity<>(exceptionResource, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GithubServiceUnavailableException.class)
    ResponseEntity<ExceptionResource> handleGithubServiceUnavailableException(final GithubServiceUnavailableException exception) {
        final var exceptionResource = new ExceptionResource(503, exception.getMessage());
        return new ResponseEntity<>(exceptionResource, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(DeserializationFailedException.class)
    ResponseEntity<ExceptionResource> handleDeserializationFailedException(final DeserializationFailedException exception) {
        final var exceptionResource = new ExceptionResource(500, exception.getMessage());
        return new ResponseEntity<>(exceptionResource, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
