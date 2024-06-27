package edu.epam.fop.lambdas;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RadianComparatorTest {

  @DisplayName("RADIAN_COMPARATOR test")
  @ParameterizedTest(name = "cmp({0}, {1}) == {2}")
  @MethodSource
  void radianComparatorTest(Double angle1, Double angle2, int resultSign) {
    var cmp = RadianComparator.get();
    var result = cmp.compare(angle1, angle2);
    assertEquals(Math.signum(resultSign), Math.signum(result));
  }

  static Stream<Arguments> radianComparatorTest() {
    return Stream.of(
        arguments(0.0, 0.0, 0),
        arguments(0.0, 2 * PI, 0),
        arguments(0.0, PI, -1),
        arguments(PI, 0.0, 1),
        arguments(2.3, Math.fma(16, PI, 2.3), 0),
        arguments(null, 0.0, -1),
        arguments(0.0, null, 1),
        arguments(null, null, 0),
        arguments(0.0, 0.0005, 0),
        arguments(0.001, 0.0, 1),
        arguments(0.0, 0.001, -1)
    );
  }

}
