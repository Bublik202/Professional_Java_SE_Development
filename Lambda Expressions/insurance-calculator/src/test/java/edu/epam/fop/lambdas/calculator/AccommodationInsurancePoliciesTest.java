package edu.epam.fop.lambdas.calculator;

import static edu.epam.fop.lambdas.calculator.InsuranceCalculatorTest.emptyConf;

import edu.epam.fop.lambdas.insurance.Accommodation;
import edu.epam.fop.lambdas.insurance.Currency;
import edu.epam.fop.lambdas.insurance.RepeatablePayment;
import java.math.BigInteger;
import java.time.Period;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AccommodationInsurancePoliciesTest implements InsuranceCalculatorTest<Accommodation> {

  @ParameterizedTest(name = "InsuranceCoefficient for {1} must be {2}")
  @DisplayName("Test for rent dependent accommodation insurance")
  @MethodSource
  public void testRentDependentInsurance(InsuranceCalculator<Accommodation> calculator, Accommodation subject,
      InsuranceCoefficient expectedInsurance) {
    testSubject(calculator, subject, expectedInsurance);
  }

  static Stream<Arguments> testRentDependentInsurance() {
    return InsuranceCalculatorTest.buildFor(
            AccommodationInsurancePolicies.rentDependentInsurance(BigInteger.valueOf(10_000L)),
            Accommodation::new)
        .addNull(null)
        .add(emptyConf(), null)
        .add(acc -> acc.rent(new RepeatablePayment(Period.ofDays(10), Currency.USD, BigInteger.valueOf(500L))), null)
        .add(acc -> acc.rent(new RepeatablePayment(Period.ofMonths(1), Currency.AUD, BigInteger.valueOf(1000L))), null)
        .add(acc -> acc.rent(new RepeatablePayment(Period.ofMonths(1), Currency.EUR, BigInteger.valueOf(10_000L))),
            null)
        .add(acc -> acc.rent(new RepeatablePayment(Period.ofMonths(1), Currency.USD, BigInteger.valueOf(10_000L))),
            InsuranceCoefficient.MAX)
        .add(acc -> acc.rent(new RepeatablePayment(Period.ofMonths(1), Currency.USD, BigInteger.valueOf(100_000L))),
            InsuranceCoefficient.MAX)
        .add(acc -> acc.rent(new RepeatablePayment(Period.ofMonths(1), Currency.USD, BigInteger.valueOf(5_000L))),
            InsuranceCoefficient.MED)
        .add(acc -> acc.rent(new RepeatablePayment(Period.ofMonths(1), Currency.USD, BigInteger.valueOf(7_500L))),
            InsuranceCoefficient.of(75))
        .add(acc -> acc.rent(new RepeatablePayment(Period.ofMonths(1), Currency.USD, BigInteger.valueOf(0))), null)
        .build();
  }

  @ParameterizedTest(name = "InsuranceCoefficient for {1} must be {2}")
  @DisplayName("Test for price, rooms & area dependent accommodation insurance")
  @MethodSource
  public void testPriceAndRoomsAndAreaDependentInsurance(InsuranceCalculator<Accommodation> calculator,
      Accommodation subject, InsuranceCoefficient expectedInsurance) {
    testSubject(calculator, subject, expectedInsurance);
  }

  static Stream<Arguments> testPriceAndRoomsAndAreaDependentInsurance() {
    return InsuranceCalculatorTest.buildFor(
            AccommodationInsurancePolicies.priceAndRoomsAndAreaDependentInsurance(
                BigInteger.valueOf(1_000_000L),
                5,
                BigInteger.valueOf(150L)
            )
            , Accommodation::new)
        .addNull(InsuranceCoefficient.MIN)
        .add(emptyConf(), InsuranceCoefficient.MIN)
        .add(acc -> acc.price(BigInteger.valueOf(500_000L)), InsuranceCoefficient.MIN)
        .add(acc -> acc.price(BigInteger.valueOf(1_000_000L)).rooms(4), InsuranceCoefficient.MIN)
        .add(acc -> acc.price(BigInteger.valueOf(1_000_000L)).rooms(5).area(BigInteger.valueOf(50L)),
            InsuranceCoefficient.MIN)
        .add(acc -> acc.price(BigInteger.valueOf(1_000_000L)).rooms(5).area(BigInteger.valueOf(150)),
            InsuranceCoefficient.MAX)
        .build();
  }
}
