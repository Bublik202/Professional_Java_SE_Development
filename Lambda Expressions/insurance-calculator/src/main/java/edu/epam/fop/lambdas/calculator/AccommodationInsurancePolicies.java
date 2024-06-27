package edu.epam.fop.lambdas.calculator;

import edu.epam.fop.lambdas.insurance.Accommodation;
import edu.epam.fop.lambdas.insurance.Currency;
import edu.epam.fop.lambdas.insurance.RepeatablePayment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Period;
import java.util.Optional;

public final class AccommodationInsurancePolicies {

  private AccommodationInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  static InsuranceCalculator<Accommodation> rentDependentInsurance(BigInteger divider) {
    return entity -> {
    	if(entity == null) {
    		return Optional.empty();
    	}
    	Accommodation accommodation = entity;
    	Optional<RepeatablePayment> rent = accommodation.rent();
    	
    	if(rent.isPresent() 
    			&& rent.get().unit().equals(Period.ofMonths(1))
    			&& rent.get().currency().equals(Currency.USD)
    			&& rent.get().amount().compareTo(BigInteger.ZERO) > 0) {
    		BigDecimal rentAmount = new BigDecimal(rent.get().amount());  
    		BigDecimal coefficient = rentAmount
    				.divide(new BigDecimal(divider))
    				.multiply(BigDecimal.valueOf(100));
    		int result = Math.min(coefficient.intValue(), InsuranceCoefficient.MAX.coefficient());
    		return Optional.of(InsuranceCoefficient.of(result));
    	}
    	
    	return Optional.empty();
    };
  }

  static InsuranceCalculator<Accommodation> priceAndRoomsAndAreaDependentInsurance(BigInteger priceThreshold,
      int roomsThreshold, BigInteger areaThreshold) {
	  return entity -> {
		  if(entity == null) {
			  return Optional.of(InsuranceCoefficient.MIN);
		  }
		  Accommodation accommodation = entity;
		  Optional<BigInteger> price = Optional.ofNullable(accommodation.price());
		  Optional<Integer> rooms = Optional.ofNullable(accommodation.rooms());
		  Optional<BigInteger> area = Optional.ofNullable(accommodation.area());
		  
		  if(price.isPresent() && rooms.isPresent() && area.isPresent()
				  && price.get().compareTo(priceThreshold) >= 0
				  && rooms.get() >= roomsThreshold
				  && area.get().compareTo(areaThreshold) >= 0) {
			  return Optional.of(InsuranceCoefficient.MAX);
		  }
		  return Optional.of(InsuranceCoefficient.MIN);
	  };
    
  }
}
