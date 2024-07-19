package com.adamsm2.backend.shared.exception;

public class GithubServiceUnavailableException extends RuntimeException {

    public GithubServiceUnavailableException() {
        super("Github service is currently unavailable");
    }
}
