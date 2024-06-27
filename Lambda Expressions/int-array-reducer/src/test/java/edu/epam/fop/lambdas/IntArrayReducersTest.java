package edu.epam.fop.lambdas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Tests for IntArrayReducer implementations")
class IntArrayReducersTest {

  @MethodSource
  @DisplayName("Test for SUMMARIZER")
  @ParameterizedTest(name = "Sum of [{0}] must be equal to {1}")
  void shouldSumCorrectly(int[] array, int expected) {
    test(IntArrayReducers.SUMMARIZER, array, expected);
  }

  static Stream<Arguments> shouldSumCorrectly() {
    return Stream.of(
        arguments(ints(0), 0),
        arguments(ints(-1, 1), 0),
        arguments(ints(1, 2, 3, 4, 5), 15)
    );
  }

  @MethodSource
  @DisplayName("Test for MULTIPLIER")
  @ParameterizedTest(name = "Product of [{0}] must be equal to {1}")
  void shouldMultipleCorrectly(int[] array, int expected) {
    test(IntArrayReducers.MULTIPLIER, array, expected);
  }

  static Stream<Arguments> shouldMultipleCorrectly() {
    return Stream.of(
        arguments(ints(0), 0),
        arguments(ints(1, 2, 1234412, 0), 0),
        arguments(ints(-1, -1), 1),
        arguments(ints(-1, 1), -1),
        arguments(ints(1, 2, 3, 4, 5), 120)
    );
  }

  @MethodSource
  @DisplayName("Test for MIN_FINDER")
  @ParameterizedTest(name = "Min of [{0}] must be equal to {1}")
  void shouldFindMinCorrectly(int[] array, int expected) {
    test(IntArrayReducers.MIN_FINDER, array, expected);
  }

  static Stream<Arguments> shouldFindMinCorrectly() {
    return Stream.of(
        arguments(ints(0), 0),
        arguments(ints(1, 1, 1), 1),
        arguments(ints(-1, 0, 1), -1)
    );
  }

  @MethodSource
  @DisplayName("Test for MAX_FINDER")
  @ParameterizedTest(name = "Max of [{0}] must be equal to {1}")
  void shouldFindMaxCorrectly(int[] array, int expected) {
    test(IntArrayReducers.MAX_FINDER, array, expected);
  }

  static Stream<Arguments> shouldFindMaxCorrectly() {
    return Stream.of(
        arguments(ints(0), 0),
        arguments(ints(1, 1, 1), 1),
        arguments(ints(-1, 0, 1), 1)
    );
  }

  @MethodSource
  @DisplayName("Test for AVERAGE_CALCULATOR")
  @ParameterizedTest(name = "Average of [{0}] must be equal to {1}")
  void shouldCalculateAverageCorrectly(int[] array, int expected) {
    test(IntArrayReducers.AVERAGE_CALCULATOR, array, expected);
  }

  static Stream<Arguments> shouldCalculateAverageCorrectly() {
    return Stream.of(
        arguments(ints(0), 0),
        arguments(ints(1, 1, 1, 1, 1), 1),
        arguments(ints(1, 2, 3, 4, 5), 3),
        arguments(ints(1, 2), 2),
        arguments(ints(1, 2, 1), 1)
    );
  }

  @MethodSource
  @DisplayName("Test for UNIQUE_COUNTER")
  @ParameterizedTest(name = "Total unique elements of [{0}] must be equal to {1}")
  void shouldCountUniquesCorrectly(int[] array, int expected) {
    test(IntArrayReducers.UNIQUE_COUNTER, array, expected);
  }

  static Stream<Arguments> shouldCountUniquesCorrectly() {
    return Stream.of(
        arguments(ints(0), 1),
        arguments(ints(0, 0, 0), 1),
        arguments(ints(1, 2, 3), 3),
        arguments(ints(1, 2, 2, 3, 3, 3), 3)
    );
  }

  @MethodSource
  @DisplayName("Test for SORT_DIRECTION_DEFINER")
  @ParameterizedTest(name = "Sort direction of [{0}] must be equal to {1}")
  void shouldDefineSortDirectionCorrectly(int[] array, int expected) {
    test(IntArrayReducers.SORT_DIRECTION_DEFINER, array, expected);
  }

  static Stream<Arguments> shouldDefineSortDirectionCorrectly() {
    return Stream.of(
        arguments(ints(0), 0),
        arguments(ints(0, 0), 0),
        arguments(ints(0, 1), 1),
        arguments(ints(1, 0), -1),
        arguments(ints(1, 0, 1), 0),
        arguments(ints(0, 0, 1), 1),
        arguments(ints(0, 0, -1), -1)
    );
  }

  private static void test(IntArrayReducer reducer, int[] array, int expected) {
    int actual = reducer.reduce(array);
    assertEquals(expected, actual);
  }

  private static int[] ints(int... array) {
    return array;
  }
}
