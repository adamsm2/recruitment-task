package com.adamsm2.backend.service.usecase.internal;

import com.adamsm2.backend.dto.RepositoryResource;
import com.adamsm2.backend.dto.SearchRepositoriesResponse;
import com.adamsm2.backend.model.Branch;
import com.adamsm2.backend.model.Repository;
import com.adamsm2.backend.service.mapper.RepositoryMapper;
import com.adamsm2.backend.service.usecase.RepositoryUseCases;
import com.adamsm2.backend.shared.exception.GithubServiceUnavailableException;
import com.adamsm2.backend.shared.exception.ItemNotFoundException;
import com.adamsm2.backend.shared.utils.DeserializationValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class RepositoryService implements RepositoryUseCases {

    private static final String GITHUB_API_URL = "https://api.github.com/";
    private final RepositoryMapper repositoryMapper;
    private final RestClient restClient;

    @Override
    public List<RepositoryResource> getNotForkedUserRepositoriesFromGithub(
            final String username, final int page, final int size) {
        final var repositories = fetchNotForkedRepositoriesFromGithub(username, page, size);
        repositories.forEach(repository -> {
            final var branches = fetchRepositoryBranches(username, repository.getName());
            repository.setBranches(branches);
        });
        return repositories.stream()
                .map(repositoryMapper::mapRepositoryToRepositoryResource)
                .toList();
    }

    private List<Repository> fetchNotForkedRepositoriesFromGithub(final String username, final int page, final int size) {
        final var uri = getSearchNotForkedRepositoriesUri(username, page, size);
        final var result = restClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ItemNotFoundException("User with given username doesn't exist");
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new GithubServiceUnavailableException();
                })
                .body(SearchRepositoriesResponse.class);

        DeserializationValidator.validateDeserializationOrThrow(result);
        return result.items();
    }

    private List<Branch> fetchRepositoryBranches(final String username, final String repositoryName) {
        final var uri = getRepositoryBranchesUri(username, repositoryName);
        final var result = restClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ItemNotFoundException("Try again, cannot access a repository with given name");
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new GithubServiceUnavailableException();
                })
                .body(Branch[].class);

        DeserializationValidator.validateDeserializationOrThrow(result);
        return Arrays.asList(result);
    }

    private String getSearchNotForkedRepositoriesUri(final String username, final int page, final int size) {
        return UriComponentsBuilder.fromUriString(GITHUB_API_URL)
                .path("/search/repositories")
                .queryParam("q", "user:" + username + "+fork:false")
                .queryParam("page", page)
                .queryParam("per_page", size)
                .toUriString();
    }

    private String getRepositoryBranchesUri(final String username, final String repositoryName) {
        return UriComponentsBuilder.fromUriString(GITHUB_API_URL)
                .path("/repos/" + username + "/" + repositoryName + "/branches").toUriString();
    }

}
