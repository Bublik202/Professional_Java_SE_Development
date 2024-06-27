package com.epam.autotasks;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CatLibrary {

    public static final String EMPTY_STRING = "";

    public Map<String, Cat> mapCatsByName(List<Cat> cats) {
        return cats.stream()
        		.filter(cat -> cat != null && cat.getName() != null)
        		.collect(Collectors.groupingBy(cat -> cat.getName(), Collectors.toList()))
        		.entrySet().stream()
        		.filter(cat -> cat.getValue().size() == 1)
        		.collect(Collectors.toMap(Map.Entry::getKey, t -> t.getValue().get(0)));
        		
        //.collect(Collectors.toMap(Cat::getName, cat -> cat, (cat1, cat2) -> cat1));
        //1 аргумент это имя 
        //2 аргумент это сам объект Cat
        //3 аргумент это если объекты Cat с одинаковым именем
    }

    public Map<Cat.Breed, Set<Cat>> mapCatsByBreed(List<Cat> cats) {
        return cats.stream()	
        		.filter(cat -> cat != null && cat.getBreed() != null)
        		.collect(Collectors.groupingBy(cat -> cat.getBreed(), Collectors.toSet()));
    }

    public Map<Cat.Breed, String> mapCatNamesByBreed(List<Cat> cats) {
        return cats.stream()
        		.filter(cat -> cat.getBreed() != null && !cat.getName().isEmpty())
        		.collect(Collectors.groupingBy(Cat::getBreed,
        				Collectors.mapping(cat -> cat.getName(),
        						Collectors.joining(", ", "Cat names: ", "."))));
    }

    public Map<Cat.Breed, Double> mapAverageResultByBreed(List<Cat> cats) {
        return cats.stream()
        		.filter(cat -> cat.getBreed() != null && cat.getContestResult().getSum() != null)
        		.collect(Collectors.groupingBy(cat -> cat.getBreed(), 
        				Collectors.averagingDouble(value -> value.getContestResult().getSum())));
    }

    public SortedSet<Cat> getOrderedCatsByContestResults(List<Cat> cats) {
    	Comparator<Cat> comparator = Comparator.<Cat, Integer> comparing(
    			cat -> cat.getContestResult().getSum(), Comparator.reverseOrder())
    			.thenComparing(cat -> cat.getName());
    	
        return cats.stream()
        		.filter(cat -> cat.getContestResult() != null && cat.getContestResult().getSum() != null)
        		.sorted(comparator)
        		.collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }
}