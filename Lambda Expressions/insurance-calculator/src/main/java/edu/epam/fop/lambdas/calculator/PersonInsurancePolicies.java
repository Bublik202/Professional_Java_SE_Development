package edu.epam.fop.lambdas.calculator;

import edu.epam.fop.lambdas.insurance.Accommodation;
import edu.epam.fop.lambdas.insurance.Accommodation.EmergencyStatus;
import edu.epam.fop.lambdas.insurance.Currency;
import edu.epam.fop.lambdas.insurance.Employment;
import edu.epam.fop.lambdas.insurance.Family;
import edu.epam.fop.lambdas.insurance.Injury;
import edu.epam.fop.lambdas.insurance.Person;
import edu.epam.fop.lambdas.insurance.RepeatablePayment;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

public final class PersonInsurancePolicies {

  private PersonInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InsuranceCalculator<Person> childrenDependent(int childrenCountThreshold) {
	  return entity -> {
		  if(entity == null || entity.family().isEmpty()) {
			  return Optional.of(InsuranceCoefficient.MIN);
		  }
		  Family family = entity.family().orElse(new Family());
		  Set<Person> kid = family.children();
		  if(kid == null ) {
			  return Optional.of(InsuranceCoefficient.MIN);
		  }
		  
		  int kidsCount = kid.size();
		  if(kidsCount == 0) {
			  return Optional.of(InsuranceCoefficient.MIN);
	      }
		  int coefficient = Math.min(100, kidsCount * 100 / childrenCountThreshold);
		  return Optional.of(InsuranceCoefficient.of(coefficient));
	  };
  }

  public static InsuranceCalculator<Person> employmentDependentInsurance(BigInteger salaryThreshold,
      Set<Currency> currencies) {
	  return entity -> {
		  if(entity == null) {
			  return Optional.empty();
		  }
		  
		  SortedSet<Employment> employmentHistory = entity.employmentHistory();
		  if(employmentHistory == null || employmentHistory.size() < 4) {
			  return Optional.empty();
		  }
		  
		  Map<Currency, BigInteger> account = entity.account();
		  if(account == null || account.size() < 2) {
			  return Optional.empty();
		  }
		  
		  SortedSet<Injury> injuries = entity.injuries();
		  if(injuries != null && !injuries.isEmpty()) {
			  return Optional.empty();
		  }
		  
		  SortedSet<Accommodation> accommodations = entity.accommodations();
		  if(accommodations.isEmpty()) {
			  return Optional.empty();
		  }
		  
		  Employment lastRecord = employmentHistory.last(); 
		  if(lastRecord.endDate().isPresent()) {
			  return Optional.empty();
		  }
	 	  
		  Optional<RepeatablePayment> salary = lastRecord.salary();
		  if(!salary.isPresent()
				  || !currencies.contains(salary.get().currency())
				  || salary.get().amount().compareTo(salaryThreshold) < 0) {
			  return Optional.empty();
		  }
		  
		  return Optional.of(InsuranceCoefficient.MED);
	  };
  }

  public static InsuranceCalculator<Person> accommodationEmergencyInsurance(Set<EmergencyStatus> statuses) {
	  return entity -> {
		  if(entity == null) {
			  return Optional.empty();				  
		  }
		  SortedSet<Accommodation> accommodations = entity.accommodations();
		  if(accommodations == null || accommodations.isEmpty()) {
			  return Optional.empty();
		  }
		  
		  BigInteger check = BigInteger.valueOf(Long.MAX_VALUE);
		  Optional<Accommodation> smallestAccomodation = Optional.empty();
		  
		  for (Accommodation accomodation : accommodations) {
			  if(accomodation.emergencyStatus().isPresent() 
					  && statuses.contains(accomodation.emergencyStatus().get())) {
				  if(accomodation.area().compareTo(check) < 0) {
					  smallestAccomodation = Optional.of(accomodation);
					  check = accomodation.area();
				  }
			  }
			  
		  }
		  
		  if(smallestAccomodation.isPresent()) {
			  int ordinal = smallestAccomodation.get().emergencyStatus().get().ordinal();
			  int coefficient = (int) Math.round(
					  100 * (1 - (double) ordinal / (statuses.size())));
			  
/* ------> */ coefficient = (ordinal == 1 && coefficient < 100 ) ? 80 : 100;   
			  return Optional.of(InsuranceCoefficient.of(coefficient));
		  }
		  
		  return Optional.empty();
	  };
  }

  public static InsuranceCalculator<Person> injuryAndRentDependentInsurance(BigInteger rentThreshold) {
	  return entity -> {
		  if(entity == null) {
			  return Optional.empty();  
		  }
		  SortedSet<Injury> injures = entity.injuries();
		  if(injures == null || injures.isEmpty()) {
			  return Optional.empty();
		  }
		  for (Injury injury : injures) {
			  if(injury.culprit().isEmpty() || !injury.culprit().isPresent()) {
				  return Optional.empty();
			  }		
		  }
		  SortedSet<Accommodation> accommodations = entity.accommodations();
		  
		  Optional<BigInteger> maxRent = maxRent(accommodations);
	  		  
		  if(maxRent.isPresent()) {
			  int coefficient = Math.min(100, (maxRent.get().multiply(BigInteger.valueOf(100)).divide(rentThreshold)).intValue());
	  		  return Optional.of(InsuranceCoefficient.of(coefficient));
		  }
		  
		  return Optional.empty();
	  };
  }
  private static Optional<BigInteger> maxRent(SortedSet<Accommodation> accommodations) {
	  Optional<BigInteger> max = Optional.empty();
		if(accommodations != null && !accommodations.isEmpty()) {			  
			  for (Accommodation accommodation : accommodations) {
				  Optional<RepeatablePayment> rent = accommodation.rent();
				  if(rent.isPresent()) {
					  Currency currency = rent.get().currency();
					  BigInteger amount = rent.get().amount();
					  if(max.isEmpty() || amount.compareTo(max.get()) > 0) {
						  if(currency.equals(Currency.GBP)) {
							  max = Optional.ofNullable(amount);
						  }
						  
					  }					  
					  
				  }
			  }	
			  return max;
		}
		return Optional.empty();
		
	}

}
