package com.adamsm2.backend.model;

import lombok.Data;

@Data
public class Branch {

    private String name;
    
    private Commit commit;
}
