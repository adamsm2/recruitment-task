package com.adamsm2.backend.service.mapper;

import com.adamsm2.backend.dto.BranchResource;
import com.adamsm2.backend.model.Branch;
import org.springframework.lang.NonNull;

public interface BranchMapper {

    BranchResource mapBranchToBranchResource(@NonNull Branch branch);
}
