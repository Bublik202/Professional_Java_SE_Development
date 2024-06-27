package com.epam.autotasks;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CatContestHelper {

    public static final Integer CARRIER_THRESHOLD = 30;

    public Integer getCarrierNumber(List<Cat> cats) {
    	Optional<Integer> result = cats.stream()
    			.map(cat -> Math.max(cat.getWeight(), 1))    			
    	    	.reduce(Integer::sum);
    	
    	return result.isEmpty() ? 0 : result.get() / 30 + 1;


    }

    public String getCarrierId(List<Cat> cats) {
        return "CF" + cats.stream()
    			.filter(cat -> cat.getBreed() != null && cat.getName() != null)
    			.map(cat -> cat.getName().substring(0, 3)
    					+ cat.getBreed().toString().substring(0, 3))
    			.collect(Collectors.joining()).toUpperCase();
    }

    public Integer countTeamAwards(List<Cat> cats) {
        return cats.stream()    	    	
    	    	.map(cat -> cat.getAwards())
    	    	.reduce(Integer::sum)
    	    	.orElse(0);
    }
}