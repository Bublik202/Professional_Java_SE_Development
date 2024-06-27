package edu.epam.fop.lambdas;

import java.util.HashSet;
import java.util.Set;

// TODO write your implementation here
public interface IntArrayReducers {

  static IntArrayReducer SUMMARIZER = array -> {
	  int sum = 0;
	  for (int i : array) {
		  sum += i;
	  }
	  return sum;
  };

  static IntArrayReducer MULTIPLIER = array -> {
	  int sum = 1;
	  for (int i : array) {
		  sum *= i;
	  }
	  return sum;
  };

  static IntArrayReducer MIN_FINDER = array -> {
	  Integer min = Integer.MAX_VALUE;
	  for (int i : array) {
		  if(i < min) {
			  min = i;
		  }
	  }
	  return min;
	  
  };

  static IntArrayReducer MAX_FINDER = array -> {
	  Integer max = Integer.MIN_VALUE;
	  for (int i : array) {
		  if(i > max) {
			  max = i;
		  }
	  }
	  return max;
  };

  static IntArrayReducer AVERAGE_CALCULATOR = array -> {
	  int avg = 0;
	  for (int i : array) {
		  avg += i;		  		
	  }
	  return (int) Math.round((double) avg / array.length);
  };

  static IntArrayReducer UNIQUE_COUNTER = array -> {
	  Set<Integer> set = new HashSet<>();
	  for (int i : array) {
		  set.add(i);		
      }
	  return set.size();
  };

  static IntArrayReducer SORT_DIRECTION_DEFINER = array -> {
	  int len = array.length;
	  
	  if(len < 2) return 0;
	  
	  if(array[0] < array[len - 1]) return 1;
	  
	  if(array[0] > array[len - 1]) return -1;
	  
	  return 0;
  };

}
