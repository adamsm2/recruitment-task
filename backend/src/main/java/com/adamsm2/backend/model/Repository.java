package com.adamsm2.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class Repository {

    private String name;

    private Owner owner;

    @EqualsAndHashCode.Exclude
    private List<Branch> branches;
}
