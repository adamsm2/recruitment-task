package com.adamsm2.backend.shared.config;

import com.adamsm2.backend.shared.exception.GithubInvalidAccessTokenException;
import com.adamsm2.backend.shared.exception.GithubRateLimitExceededException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
class ClientConfig {

    @Value("${app.githubAccessToken}")
    private String githubAccessToken;

    @Bean
    WebClient webClient() {
        return WebClient.builder()
/*                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubAccessToken)*/
                .filter(errorHandler())
                .build();
    }

    private ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().isSameCodeAs(HttpStatus.FORBIDDEN)) {
                throw new GithubRateLimitExceededException();
            }
            if (clientResponse.statusCode().isSameCodeAs(HttpStatus.UNAUTHORIZED)) {
                throw new GithubInvalidAccessTokenException();
            }
            return Mono.just(clientResponse);
        });
    }
}
