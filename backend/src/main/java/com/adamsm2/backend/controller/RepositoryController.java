package com.adamsm2.backend.controller;

import com.adamsm2.backend.dto.RepositoryResource;
import com.adamsm2.backend.service.usecase.RepositoryUseCases;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/repositories")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class RepositoryController {

    private final RepositoryUseCases repositoryUseCases;

    @GetMapping("/github/not-forked/{username}")
    ResponseEntity<List<RepositoryResource>> getNotForkedUserRepositoriesFromGithub(
            @PathVariable final String username,
            @RequestParam(defaultValue = "1") final int page,
            @RequestParam(defaultValue = "10") final int size
    ) {
        return ResponseEntity.ok(repositoryUseCases.getNotForkedUserRepositoriesFromGithub(
                username, page, size));
    }
}
