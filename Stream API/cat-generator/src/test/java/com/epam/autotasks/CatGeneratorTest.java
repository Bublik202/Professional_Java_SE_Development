package com.epam.autotasks;


import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CatGeneratorTest {

    @Test
    @DisplayName("Should return a list of cats, where all fields are set.")
    void shouldGenerateNonNullableCats() {
        // given
        int catCount = 10;

        // when
        List<Cat> cats = CatGenerator.generateCats(catCount);

        // then
        Assertions.assertNotNull(cats);
        Assertions.assertEquals(catCount, cats.size());
        for (Cat cat : cats) {
            Assertions.assertNotNull(cat.getName());
            Assertions.assertNotNull(cat.getAge());
            Assertions.assertNotNull(cat.getBreed());
        }
    }

    @Test
    @DisplayName("Should return an empty list, when zero cats are expected.")
    void shouldReturnEmptyListWhenZeroCount() {
        //w when
        List<Cat> cats = CatGenerator.generateCats(0);

        // then
        Assertions.assertNotNull(cats);
        Assertions.assertTrue(cats.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("catFamilies")
    @DisplayName("Should return the amount of food for a family.")
    void shouldReturnFoodAmount(int familySize, int skip, long expected) {
        // when
        long foodAmount = CatGenerator.generateFood(familySize, skip);

        // then
        Assertions.assertEquals(expected, foodAmount);

    }

    @ParameterizedTest
    @MethodSource("catFamiliesException")
    @DisplayName("Should throw an arithmetic exception, when a long value overflows.")
    void shouldThrowArithmeticException(int familySize, int skip) {
        // when - then
        Assertions.assertThrows(ArithmeticException.class, () -> CatGenerator.generateFood(familySize, skip));
    }

    @ParameterizedTest
    @MethodSource("inputException")
    @DisplayName("Should throw an illegal argument exception, when an input value is negative.")
    void shouldThrowIllegalArgumentException(int familySize, int skip) {
        // when - then
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> CatGenerator.generateFood(familySize, skip));
        Assertions.assertEquals("Input arguments cannot be negative", illegalArgumentException.getMessage());
    }

    private static Stream<Arguments> catFamilies() {
        return Stream.of(

                Arguments.of(0, 0, 0),
                Arguments.of(4, 0, 60),
                Arguments.of(8, 0, 1020),
                Arguments.of(20, 0, 4194300),
                Arguments.of(39, 0, 2199023255548L),
                Arguments.of(43, 13, 35184372056064L),
                Arguments.of(6, 5, 128),
                Arguments.of(9, 10, 0)
        );
    }

    private static Stream<Arguments> catFamiliesException() {
        return Stream.of(
                Arguments.of(19563, 0),
                Arguments.of(89, 40),
                Arguments.of(19563, 19562)
        );
    }

    private static Stream<Arguments> inputException() {
        return Stream.of(
                Arguments.of(-100, -0, 0),
                Arguments.of(0, -100, 0),
                Arguments.of(-100, -100, 0)
        );
    }
}