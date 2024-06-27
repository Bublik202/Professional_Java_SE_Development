package edu.epam.fop.lambdas;

import static edu.epam.fop.lambdas.ComparatorCaseChain.NullsPosition.FIRST;
import static edu.epam.fop.lambdas.ComparatorCaseChain.NullsPosition.LAST;
import static edu.epam.fop.lambdas.ComparatorCaseChain.NullsPosition.UNSUPPORTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ComparatorsTest {
  private static final ComparatorCaseChain<Address> ZIP_CODE_CMP_CHAIN = new ComparatorCaseChain<>(Address::new, FIRST)
      .add(UNSUPPORTED, Address::zipCode, 58, 42);

  private static final ComparatorCaseChain<Address> STREET_CMP_CHAIN = new ComparatorCaseChain<>(Address::new, FIRST)
      .add(LAST, Address::street, "Electric Street", "Gallon Street")
      .add(LAST, Address::building, 52, 77)
      .add(LAST, Address::apartment, 556, 931);

  private static final ComparatorCaseChain<Address> REGION_CMP_CHAIN = new ComparatorCaseChain<>(Address::new, FIRST)
      .add(LAST, Address::country, "Bolivia", "Tajikistan")
      .add(LAST, Address::city, "Alytaus", "Pittsboro")
      .add(ZIP_CODE_CMP_CHAIN);

  private static final ComparatorCaseChain<Address> ADDRESS_CMP_CHAIN = new ComparatorCaseChain<>(Address::new, LAST)
      .add(REGION_CMP_CHAIN)
      .add(STREET_CMP_CHAIN);
  private static final ComparatorCaseChain<Person> FULL_NAME_CMP_CHAIN = new ComparatorCaseChain<>(Person::new, FIRST)
      .add(UNSUPPORTED, Person::name, "Jawann", "Seana")
      .add(UNSUPPORTED, Person::surname, "Majewski", "Wojtowicz");

  private static final ComparatorCaseChain<Person> BIRTHDATE_CMP_CHAIN = new ComparatorCaseChain<>(Person::new, FIRST)
      .add(LAST, Person::birthdate, LocalDate.of(1990, 6, 22), LocalDate.of(1990, 1, 1));

  private static final ComparatorCaseChain<Person> PERSON_CMP_CHAIN = new ComparatorCaseChain<>(Person::new, LAST)
      .add(FULL_NAME_CMP_CHAIN)
      .add(BIRTHDATE_CMP_CHAIN)
      .add(ADDRESS_CMP_CHAIN, Person::address, Person::address, Address::new);

  private static final ComparatorCaseChain<Company> REGISTRATION_ID_CMP_CHAIN
      = new ComparatorCaseChain<>(Company::new, LAST)
      .add(UNSUPPORTED, Company::registrationUuid,
          "5d12f005-eb98-43b4-b39f-8a16bdf7c092",
          "9a6a74a7-4e78-476f-805e-3d9bdbaea1a6");

  private static final ComparatorCaseChain<Company> COMPANY_COMPARATOR_CASE_CHAIN
      = new ComparatorCaseChain<>(Company::new, LAST)
      .add(LAST, Company::name, "Court Studio", "Olympus Challenge")
      .add(REGISTRATION_ID_CMP_CHAIN)
      .add(PERSON_CMP_CHAIN, Company::director, Company::director, Person::new)
      .add(ADDRESS_CMP_CHAIN, Company::officeAddress, Company::officeAddress, Address::new);

  @DisplayName("Comparators test")
  @ParameterizedTest(name = "{0}")
  @MethodSource
  <T> void comparatorsTest(String name, Comparator<T> comparator, T left, T right,
      int expected, Class<Throwable> expectedErrorType) {
    try {
      var result = comparator.compare(left, right);
      if (expectedErrorType != null) {
        fail("An exception was not thrown, while was expected an exception of type " + expectedErrorType);
      }
      assertEquals((int) Math.signum(expected), (int) Math.signum(result));
    } catch (AssertionError e) {
      throw e;
    } catch (Throwable error) {
      if (expectedErrorType == null) {
        fail("An exception was thrown, while it was not expected", error);
      }
      if (!expectedErrorType.isInstance(error)) {
        throw error;
      }
    }
  }

  static Stream<Arguments> comparatorsTest() {
    return Stream.of(
        ZIP_CODE_CMP_CHAIN.build(Comparators.ZIP_CODE_COMPARATOR, "ZIP_CODE_COMPARATOR"),
        STREET_CMP_CHAIN.build(Comparators.STREET_COMPARATOR, "STREET_COMPARATOR"),
        REGION_CMP_CHAIN.build(Comparators.REGION_COMPARATOR, "REGION_COMPARATOR"),
        ADDRESS_CMP_CHAIN.build(Comparators.ADDRESS_COMPARATOR, "ADDRESS_COMPARATOR"),
        FULL_NAME_CMP_CHAIN.build(Comparators.FULL_NAME_COMPARATOR, "FULL_NAME_COMPARATOR"),
        BIRTHDATE_CMP_CHAIN.build(Comparators.BIRTHDATE_COMPARATOR, "BIRTHDATE_COMPARATOR"),
        PERSON_CMP_CHAIN.build(Comparators.PERSON_COMPARATOR, "PERSON_COMPARATOR"),
        REGISTRATION_ID_CMP_CHAIN.build(Comparators.REGISTRATION_ID_COMPARATOR, "REGISTRATION_ID_COMPARATOR"),
        COMPANY_COMPARATOR_CASE_CHAIN.build(Comparators.COMPANY_COMPARATOR, "COMPANY_COMPARATOR")
    ).flatMap(Function.identity());
  }
}
