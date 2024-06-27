package com.epam.autotasks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class CatGenerator {

    public static List<Cat> generateCats(int count) { 		
        return Stream.generate(() -> new Cat("Leon", 16, Cat.Breed.BRITISH))
    			.limit(count)
    			.collect(Collectors.toList());
    }

    public static long generateFood(int familySize, int skip) {
    	final int MIN_FOOD = 4;
    	
    	if(familySize < 0 || skip < 0) {
    		throw new IllegalArgumentException("Input arguments cannot be negative");
    	}  		
    	
    	if(skip >= familySize) {
    		return 0;
    	}   
    	
//    	return LongStream.range(0, familySize)
//    			.skip(skip)
//    			.map(value -> {
//   			long result = (long) (MIN_FOOD * Math.pow(2, value));
//    				
//    				if(result > Long.MAX_VALUE - MIN_FOOD) {
//    					throw new ArithmeticException();
//    				}
//    				return result;
//    			})
//    			.sum();
    	
    	return LongStream.iterate(MIN_FOOD, x -> {
    		long next = x * 2;
    		if(next > Long.MAX_VALUE / 2) {
				throw new ArithmeticException();
			}
    		return next;
    	})
    			.skip(skip)
    			.limit(familySize - skip)
    			.sum();
    	
    }	
}
