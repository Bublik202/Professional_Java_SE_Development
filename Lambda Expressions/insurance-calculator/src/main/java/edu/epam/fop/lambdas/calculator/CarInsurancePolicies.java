package edu.epam.fop.lambdas.calculator;

import edu.epam.fop.lambdas.insurance.Car;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public final class CarInsurancePolicies {

  private CarInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InsuranceCalculator<Car> ageDependInsurance(LocalDate baseDate) {
	  return entity -> {
		  if(entity == null) {
			  return Optional.empty();
		  }
		  Car car = entity;
		  LocalDate manufactureDate = car.manufactureDate();
		  if(manufactureDate == null) {
			  return Optional.empty();
		  }
		  
		  long age = ChronoUnit.YEARS.between(manufactureDate, baseDate);
		  //https://urvanov.ru/2016/06/16/java-8-дата-и-время/
		  
		  if(age <= 1) {
	    		return Optional.of(InsuranceCoefficient.MAX);
	    	} else if(age <= 5) {
	    		return Optional.of(InsuranceCoefficient.of(70));
	    	} else if(age <= 10) {
	    		return Optional.of(InsuranceCoefficient.of(30));
	    	} else { 
	    		return Optional.of(InsuranceCoefficient.MIN);
	    	}

	  };
  }

  public static InsuranceCalculator<Car> priceAndOwningOfFreshCarInsurance(LocalDate baseDate,
      BigInteger priceThreshold, Period owningThreshold) {
	  return car -> {
		  if(car == null || car.soldDate().isPresent()) {
			  return Optional.empty();
		  }		  
		  
		  BigInteger price = car.price();	
		  if(price == null || price.compareTo(priceThreshold) < 0) {
			  return Optional.empty();
		  }
		  LocalDate purchaseDate = car.purchaseDate();
		  if(purchaseDate == null || purchaseDate.plus(owningThreshold).isBefore(baseDate)) {
			  return Optional.empty();
		  }
		  
		  if(price.compareTo(priceThreshold.multiply(BigInteger.valueOf(3))) >= 0) {
			  return Optional.of(InsuranceCoefficient.MAX);
		  }else if(price.compareTo(priceThreshold.multiply(BigInteger.valueOf(2))) >= 0){
			  return Optional.of(InsuranceCoefficient.MED);
		  }else {
			  return Optional.of(InsuranceCoefficient.MIN);
		  }
		  
	  };
  }
}
