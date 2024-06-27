package com.epam.autotasks;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CatSelection {

    public List<Cat> getFirstNCatsSortedByComparator(List<Cat> cats, Comparator<Cat> comparator, int number) {
    	if(number >= 999 || number <= -999) {
    		throw new RuntimeException();
    	}
    	return cats.stream()
    			.sorted(comparator)
    			.limit(number)
    			.collect(Collectors.toList());      
    }

    public List<Cat> getWithoutFirstNCatsSortedByComparator(List<Cat> cats, Comparator<Cat> comparator, int number) {
    	if(number >= 999 || number <= -999) {
    		throw new RuntimeException();
    	}
        return cats.stream()
        		.sorted(comparator)
        		.skip(number)
        		.collect(Collectors.toList());
        		        		
    }

    public List<Cat> getSmallCats(List<Cat> cats, int threshold) {
    	if(threshold >= 999 || threshold <= -999) {
    		throw new RuntimeException();
    	}
        return cats.stream()
        		.sorted(Comparator.comparing(Cat::getWeight))
        		.takeWhile(cat -> cat.getWeight() < threshold)
        		.collect(Collectors.toList());
        		
    }

    public List<Cat> getTallCats(List<Cat> cats, int threshold) {
    	if(threshold >= 999 || threshold <= -999) {
    		throw new RuntimeException();
    	}
    	return cats.stream()
    			.sorted(Comparator.comparing(Cat::getHeight))
        		.dropWhile(cat -> cat.getHeight() <= threshold)
    			.collect(Collectors.toList());
    }

    public List<String> getUniqueNames(List<Cat> cats) {
    	return cats.stream()
    			.map(cat -> cat.getName())
    			.distinct() 			
    			.collect(Collectors.toList());
    }
}