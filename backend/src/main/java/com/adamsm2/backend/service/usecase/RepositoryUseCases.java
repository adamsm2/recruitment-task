package com.adamsm2.backend.service.usecase;

import com.adamsm2.backend.dto.RepositoryResource;

import java.util.List;

public interface RepositoryUseCases {

    List<RepositoryResource> getNotForkedUserRepositoriesFromGithub(String username, int page, int size);
}
