package com.adamsm2.backend.service.mapper.internal;

import com.adamsm2.backend.dto.RepositoryResource;
import com.adamsm2.backend.model.Repository;
import com.adamsm2.backend.service.mapper.BranchMapper;
import com.adamsm2.backend.service.mapper.RepositoryMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class RepositoryMapperService implements RepositoryMapper {

    private final BranchMapper branchMapper;

    @Override
    public RepositoryResource mapRepositoryToRepositoryResource(@NonNull final Repository repository) {
        return new RepositoryResource(
                repository.getName(),
                repository.getOwner().getLogin(),
                repository.getBranches().stream()
                        .map(branchMapper::mapBranchToBranchResource)
                        .toList()

        );
    }
}
