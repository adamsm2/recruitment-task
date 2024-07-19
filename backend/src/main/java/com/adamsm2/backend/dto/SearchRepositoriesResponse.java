package com.adamsm2.backend.dto;

import com.adamsm2.backend.model.Repository;

import java.util.List;

public record SearchRepositoriesResponse(
        List<Repository> items
) {
}
