package com.adamsm2.backend.service.mapper;

import com.adamsm2.backend.dto.RepositoryResource;
import com.adamsm2.backend.model.Repository;
import org.springframework.lang.NonNull;

public interface RepositoryMapper {

    RepositoryResource mapRepositoryToRepositoryResource(@NonNull Repository repository);
}
