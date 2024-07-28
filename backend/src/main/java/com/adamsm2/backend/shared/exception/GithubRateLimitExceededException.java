package com.adamsm2.backend.shared.exception;

public class GithubRateLimitExceededException extends RuntimeException {

    public GithubRateLimitExceededException() {
        super("Github rate limit exceeded");
    }
}
