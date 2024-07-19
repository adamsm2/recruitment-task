package com.adamsm2.backend.dto;

public record BranchResource(
        String name,
        String lastCommitSha
) {
}
