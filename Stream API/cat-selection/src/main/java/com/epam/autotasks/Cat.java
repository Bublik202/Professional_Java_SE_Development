package com.epam.autotasks;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cat {

    private String name;
    private Integer age;
    private Integer weight;
    private Integer height;
    private Breed breed;

    public enum Breed {

        BRITISH(0),
        MAINE_COON(1),
        MUNCHKIN(2),
        PERSIAN(3),
        SIBERIAN(4);

        final int code;

        Breed(int code) {
            this.code = code;
        }

        public static Breed getBreedByCode(int code) {
            for (Breed breed : values()) {
                if (breed.code == code) {
                    return breed;
                }
            }
            return null;
        }
    }
}
