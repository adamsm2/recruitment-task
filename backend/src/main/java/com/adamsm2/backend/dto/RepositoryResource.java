package com.adamsm2.backend.dto;

import java.util.List;

public record RepositoryResource(
        String name,
        String ownerLogin,
        List<BranchResource> branches
) {
}
