package edu.epam.fop.lambdas;

import edu.epam.fop.lambdas.Token.Type;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class TextStatistics {

  private TextStatistics() {
    throw new UnsupportedOperationException();
  }

  public static TokenStatisticsCalculator<Token, Long> countTokens() {
	  return (statistics, tokens) -> {	
		  while (tokens.hasNext()) {
			Token token = tokens.next();
			statistics.compute(token, (key, val) -> val == null? 1L : ++val);
			
//			statistics.computeIfAbsent(token, t -> 0L);
//			statistics.computeIfPresent(token, (key, val) -> ++val);			
		  }
	  };
  }

  public static TokenStatisticsCalculator<Token, Long> countKnownTokensWithMaxLimit(int maxLimit) {
	  return (statistics, tokens) -> {
		  while (tokens.hasNext()) {
			  Token token = tokens.next();
			  statistics.computeIfPresent(token, (key, val) -> val >= maxLimit ? val : val + 1);
		  } 			  
	  };
  }

  public static TokenStatisticsCalculator<Token, Boolean> findUnknownTokensOfTypes(Set<Type> types) {
	  return (statistics, tokens) -> {	  		  
		  while (tokens.hasNext()) {
			  Token token = tokens.next();
			  statistics.computeIfAbsent(token, t -> types.contains(t.type()) ? true : null);			  
		  }

	  };
  }

  public static TokenStatisticsCalculator<Token, Integer> combinedSearch(int maxLimit, Set<Type> types) {
	  return (statistics, tokens) -> {
		  while (tokens.hasNext()) {
			  Token token = tokens.next();		
			  if(types.contains(token.type())) {
				  statistics.computeIfPresent(token, (key, val) -> val < maxLimit ? 0 : 1);
			  }else {
				  statistics.computeIfPresent(token, (key, val) -> val < maxLimit ? 2 : 3);	
			  }
			  
			  statistics.computeIfAbsent(token, t -> -1);
		  }
	  };
  }

}
