package com.adamsm2.backend.service.mapper.internal;

import com.adamsm2.backend.dto.BranchResource;
import com.adamsm2.backend.model.Branch;
import com.adamsm2.backend.service.mapper.BranchMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
class BranchMapperService implements BranchMapper {

    @Override
    public BranchResource mapBranchToBranchResource(@NonNull final Branch branch) {
        return new BranchResource(
                branch.getName(),
                branch.getCommit().getSha()
        );
    }
}
