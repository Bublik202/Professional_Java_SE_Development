package com.epam.autotasks;

import com.google.common.collect.ImmutableTable;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;

public class CatDataProcessor {

    public ImmutableTable<String, Cat.Breed, Integer> createCatTable(List<Cat> cats) {
        return cats.stream()
        		.filter(cat -> cat != null
        				&& cat.getContestResult().getSum() != null
        				&& cat.getBreed() != null
        				&& !cat.getName().isEmpty())
        		.collect(ImmutableTable.toImmutableTable(
        					cat -> cat.getName(), cat -> cat.getBreed(), cat -> cat.getContestResult().getSum()));
    }

    public JSONArray createCatJson(List<Cat> cats) {
        return new JSONArray(cats);
    }
}