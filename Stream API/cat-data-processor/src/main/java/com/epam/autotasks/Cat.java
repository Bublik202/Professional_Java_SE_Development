package com.epam.autotasks;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cat {

    private String name;
    private Integer age;
    private Breed breed;
    private Integer weight;
    private Integer awards;
    private ContestResult contestResult;

    public enum Breed {

        BRITISH, MAINE_COON, MUNCHKIN, PERSIAN, SIBERIAN, SPHYNX
    }
}