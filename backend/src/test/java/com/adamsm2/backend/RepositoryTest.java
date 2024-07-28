package com.adamsm2.backend;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.adamsm2.backend.ExpectedJsonProvider.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockTest(httpPort = 8081)
@ActiveProfiles("test")
class RepositoryTest {

    @Autowired
    WebTestClient client;

    @Test
    void givenExistingUser_whenGetNotForkedUserRepositories_thenReturnOkAndCorrectJson() {
        final var expectedJson = GetNotForkedUserRepositories_okStatusExpectedJson;
        client.get()
                .uri("/v1/repositories/github/not-forked/adamsm2?size=2&page=2")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json(expectedJson);
    }

    @Test
    void givenNonExistingUser_whenGetNotForkedUserRepositories_thenReturnNotFoundAndCorrectJson() {
        final var expectedJson = GetNotForkedUserRepositories_notFoundStatusExpectedJson;
        client.get()
                .uri("/v1/repositories/github/not-forked/adamsm22")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .json(expectedJson);
    }

    @Test
    void givenExistingUser_whenGetNotForkedUserRepositories_andWhileFetchingEachRepositoryBranchesRepositoryNoLongerExists_thenReturnNotFoundAndCorrectJson() {
        final var expectedJson = GetNotForkedUserRepositories_andWhileFetchingEachRepositoryBranchesRepositoryNoLongerExists_notFoundStatusExpectedJson;
        client.get()
                .uri("/v1/repositories/github/not-forked/adamsm2?size=1&page=1")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .json(expectedJson);
    }

    @Test
    void givenGithubServiceUnavailable_whenGetNotForkedUserRepositories_thenReturnServiceUnavailableAndCorrectJson() {
        final var expectedJson = GetNotForkedUserRepositories_githubServiceUnavailableStatusExpectedJson;
        client.get()
                .uri("/v1/repositories/github/not-forked/serviceUnavailable")
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody()
                .json(expectedJson);
    }

    @Test
    void givenGithubRateLimitExceeded_whenGetNotForkedUserRepositories_thenReturnTooManyRequestsAndCorrectJson() {
        final var expectedJson = GetNotForkedUserRepositories_tooManyRequestStatusExpectedJson;
        client.get()
                .uri("/v1/repositories/github/not-forked/rateLimitExceeded")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.TOO_MANY_REQUESTS)
                .expectBody()
                .json(expectedJson);
    }

    @Test
    void givenInvalidGithubAccessToken_whenGetNotForkedUserRepositories_thenReturnUnauthorizedAndCorrectJson() {
        final var expectedJson = GetNotForkedUserRepositories_unauthorizedStatusExpectedJson;
        client.get()
                .uri("/v1/repositories/github/not-forked/unauthorized")
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody()
                .json(expectedJson);
    }

}
