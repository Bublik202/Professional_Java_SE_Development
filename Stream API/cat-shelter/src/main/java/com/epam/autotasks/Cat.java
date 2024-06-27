package com.epam.autotasks;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cat {

    private String name;
    private Integer age;
    private Breed breed;
    private Staff attendant;
    private LocalDate lastCheckUpDate;

    public enum Breed {

        BRITISH, MAINE_COON, MUNCHKIN, PERSIAN, SIBERIAN
    }

    public enum Staff {

        NANCY, CATHERINE, BOB, JACKSON, HELEN, JOHN, EDWARD
    }
}
