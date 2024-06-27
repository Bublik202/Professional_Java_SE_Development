package com.epam.autotasks;

import java.util.List;
import java.util.stream.Collectors;

public class CatDatabase {

    public List<String> getCatNamesByBreed(List<Cat> cats, Cat.Breed breed) {
        return cats.stream()
        		.filter(cat -> cat.getBreed() == breed)
        		.map(cat -> cat.getName())
        		.collect(Collectors.toList());
    }

    public List<Cat> filterYoungerCatsByAge(List<Cat> cats, Integer age) {
        return cats.stream()
        		.filter(cat -> cat.getAge() <= age)
        		.collect(Collectors.toList());
    }

    public List<Cat> filterCatsByNamePrefix(List<Cat> cats, String prefix) {
        return cats.stream()
        		.filter(cat -> cat.getName().toLowerCase().startsWith(prefix.toLowerCase()))
        		.collect(Collectors.toList());
    }
}