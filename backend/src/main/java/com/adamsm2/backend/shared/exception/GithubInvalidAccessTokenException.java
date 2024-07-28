package com.adamsm2.backend.shared.exception;

public class GithubInvalidAccessTokenException extends RuntimeException {

    public GithubInvalidAccessTokenException() {
        super("Provided Github access token is invalid");
    }
}
