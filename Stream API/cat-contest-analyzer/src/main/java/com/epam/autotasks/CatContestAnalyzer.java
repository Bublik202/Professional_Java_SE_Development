package com.epam.autotasks;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class CatContestAnalyzer {

    public static final Integer DEFAULT_VALUE = -1;

    public Integer getMaxResult(List<Cat> cats) {
        return cats.stream()
    		.filter(cat -> cat.getContestResult().getSum() != null)
			.map(cat -> cat.getContestResult().getSum())			
			.max(Integer::compare)			
			.orElse(DEFAULT_VALUE);
    }

    public Integer getMinResult(List<Cat> cats) {
    	return cats.stream()
        		.filter(cat -> cat.getContestResult().getSum() != null)
    			.map(cat -> cat.getContestResult().getSum())	
    			.filter(value -> value != null && value > 0)
    			.min(Comparator.naturalOrder())			
    			.orElse(DEFAULT_VALUE);
    }

    public OptionalDouble getAverageResultByBreed(List<Cat> cats, Cat.Breed breed) {
    	OptionalDouble average = cats.stream()
        		.filter(cat -> cat.getBreed() != null && cat.getBreed().equals(breed))
        		.mapToDouble(cat -> cat.getContestResult().getSum())
        		.average();
    	
    	if(average.isPresent()) {
        	average = OptionalDouble.of(new BigDecimal(average
        			.getAsDouble())
        			.setScale(2, RoundingMode.HALF_UP)
        			.doubleValue());
    	}
    	
        return average;
        		
    }

    public Optional<Cat> getWinner(List<Cat> cats) {
        return cats.stream()
        		.filter(cat -> cat.getContestResult().getSum() != null)
        		.max(Comparator.comparing(cat -> cat.getContestResult().getSum()));
    }

    public List<Cat> getThreeLeaders(List<Cat> cats) {
        return cats.stream()
        		.sorted(Comparator.comparing(cat -> cat.getContestResult().getSum(), 
        				Comparator.reverseOrder()))        		
        		.limit(3)  
        		.collect(Collectors.toList());
    }

    public boolean validateResultSumNotNull(List<Cat> cats) {
        return cats.stream()
        		.filter(cat -> cat.getContestResult().getSum() != null)
        		.allMatch(cat -> cat.getContestResult().getSum() > 0);
    }

    public boolean validateAllResultsSet(List<Cat> cats) {
        return cats.stream()
        		.filter(cat -> cat.getContestResult().getSum() != null)
        		.noneMatch(cat -> cat.getContestResult().getSum() == 0);
    }

    public Optional<Cat> findAnyWithAboveAverageResultByBreed(List<Cat> cats, Cat.Breed breed) {
    	OptionalDouble average = cats.stream()
        		.filter(cat -> cat.getBreed() != null && cat.getBreed().equals(breed))
        		.mapToDouble(cat -> cat.getContestResult().getSum())
        		.average();  
    	
        return average.isPresent() ? 
        		cats.stream()
	    		.filter(cat -> cat.getBreed() != null 
	    				&& cat.getBreed().equals(breed) 
	    				&& cat.getContestResult().getSum() != null
	    				&& cat.getContestResult().getSum() > average.getAsDouble())        		
	    		.findFirst()
        		: Optional.empty();
    }
}