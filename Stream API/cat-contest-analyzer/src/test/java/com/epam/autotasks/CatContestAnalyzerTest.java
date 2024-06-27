package com.epam.autotasks;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CatContestAnalyzerTest {

    public CatContestAnalyzer catContestAnalyzer;
    private static List<Cat> cats;
    private static List<Cat> nullableCats;

    @BeforeEach
    public void setUp() {
        catContestAnalyzer = new CatContestAnalyzer();
        cats = CatTestUtils.readCSVFile(CatTestUtils.CATS_CSV_PATH);
        nullableCats = CatTestUtils.readCSVFile(CatTestUtils.NULLABLE_CATS_CSV_PATH);
    }

    @Test
    @DisplayName("Should return the maximum result among cats when non-nullable values")
    void shouldReturnMaxResultWhenNonNullableValues() {
        // given
        Integer expectedResult = 287;

        // when
        Integer result = catContestAnalyzer.getMaxResult(cats);

        // then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Should return the maximum result among cats when nullable values")
    void shouldReturnMaxResultWhenNullableValues() {
        // given
        Integer expectedResult = 267;

        // when
        Integer result = catContestAnalyzer.getMaxResult(nullableCats);

        // then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Should return a default value when given an empty list")
    void shouldReturnDefaultValueForMaxResultWhenEmptyList() {
        // given
        Integer expectedResult = CatContestAnalyzer.DEFAULT_VALUE;

        // when
        Integer result = catContestAnalyzer.getMaxResult(new ArrayList<>());

        // then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Should return the minimum result among cats when non-nullable values")
    void shouldReturnMinResultWhenNonNullableValues() {
        // given
        Integer expectedResult = 17;

        // when
        Integer result = catContestAnalyzer.getMinResult(cats);

        // then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Should return the minimum result among cats when nullable values")
    void shouldReturnMinResultWhenNullableValues() {
        // given
        Integer expectedResult = 2;

        // when
        Integer result = catContestAnalyzer.getMinResult(nullableCats);

        // then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Should return a default value when given an empty list")
    void shouldReturnDefaultValueForMinResultWhenEmptyList() {
        // given
        Integer expectedResult = CatContestAnalyzer.DEFAULT_VALUE;

        // when
        Integer result = catContestAnalyzer.getMinResult(new ArrayList<>());

        // then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Should return the average result among Siberian cats when non-nullable values")
    void shouldReturnSiberianAverageResult() {
        // given
        double expectedResult = 153.2;

        // when
        OptionalDouble result = catContestAnalyzer.getAverageResultByBreed(cats, Cat.Breed.SIBERIAN);

        // then
        Assertions.assertEquals(expectedResult, Double.valueOf(new DecimalFormat("#.00").format(result.orElse(0))));
    }

    @Test
    @DisplayName("Should return the average result among Munchkin cats when nullable values")
    void shouldReturnNullableMunchkinAverageResult() {
        // given
        double expectedResult = 96.07;

        // when
        OptionalDouble result = catContestAnalyzer.getAverageResultByBreed(nullableCats, Cat.Breed.MUNCHKIN);

        // then
        Assertions.assertEquals(expectedResult, Double.valueOf(new DecimalFormat("#.00").format(result.orElse(0))));
    }

    @Test
    @DisplayName("Should return an empty optional when given an empty list")
    void shouldReturnEmptyOptionalAverageResultWhenEmptyList() {
        // when
        OptionalDouble result = catContestAnalyzer.getAverageResultByBreed(new ArrayList<>(), Cat.Breed.MUNCHKIN);

        // then
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return a cat-winner among given cats when non-nullable values")
    void shouldReturnWinnerWhenNonNullableValues() {
        // given
        Cat expectedCat = Cat.builder()
                .name("OTQTc")
                .age(7)
                .breed(Cat.Breed.PERSIAN)
                .contestResult(new ContestResult(93, 95, 99))
                .build();

        // when
        Cat resultCat = catContestAnalyzer.getWinner(cats).orElse(new Cat(null, null, null, null));

        // then
        Assertions.assertEquals(expectedCat.getName(), resultCat.getName());
        Assertions.assertEquals(expectedCat.getAge(), resultCat.getAge());
        Assertions.assertEquals(expectedCat.getBreed(), resultCat.getBreed());
        Assertions.assertEquals(expectedCat.getContestResult().getRunning(), resultCat.getContestResult().getRunning());
        Assertions.assertEquals(expectedCat.getContestResult().getJumping(), resultCat.getContestResult().getJumping());
        Assertions.assertEquals(expectedCat.getContestResult().getPurring(), resultCat.getContestResult().getPurring());
        Assertions.assertEquals(expectedCat.getContestResult().getSum(), resultCat.getContestResult().getSum());
    }

    @Test
    @DisplayName("Should return a cat-winner among given cats when nullable values")
    void shouldReturnWinnerWhenNullableValues() {
        // given
        Cat expectedCat = Cat.builder()
                .name("GJZwK")
                .age(11)
                .breed(Cat.Breed.MAINE_COON)
                .contestResult(new ContestResult(94, 74, 99))
                .build();

        // when
        Cat resultCat = catContestAnalyzer.getWinner(nullableCats).orElse(new Cat(null, null, null, null));

        // then
        Assertions.assertEquals(expectedCat.getName(), resultCat.getName());
        Assertions.assertEquals(expectedCat.getAge(), resultCat.getAge());
        Assertions.assertEquals(expectedCat.getBreed(), resultCat.getBreed());
        Assertions.assertEquals(expectedCat.getContestResult().getRunning(), resultCat.getContestResult().getRunning());
        Assertions.assertEquals(expectedCat.getContestResult().getJumping(), resultCat.getContestResult().getJumping());
        Assertions.assertEquals(expectedCat.getContestResult().getPurring(), resultCat.getContestResult().getPurring());
        Assertions.assertEquals(expectedCat.getContestResult().getSum(), resultCat.getContestResult().getSum());
    }

    @Test
    @DisplayName("Should return an empty optional when given an empty list")
    void shouldReturnEmptyOptionalCatWhenEmptyList() {
        // when
        Optional<Cat> result = catContestAnalyzer.getWinner(new ArrayList<>());

        // then
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return three cat-leaders among given cats when non-nullable values")
    void shouldReturnLeadersWhenNonNullableValues() {
        // given
        List<Cat> expectedCatList = new ArrayList<>();
        expectedCatList.add(Cat.builder()
                .name("OTQTc")
                .age(7)
                .breed(Cat.Breed.PERSIAN)
                .contestResult(new ContestResult(93, 95, 99))
                .build());
        expectedCatList.add(Cat.builder()
                .name("XdGJa")
                .age(1)
                .breed(Cat.Breed.BRITISH)
                .contestResult(new ContestResult(96, 93, 96))
                .build());
        expectedCatList.add(Cat.builder()
                .name("CKjqQ")
                .age(1)
                .breed(Cat.Breed.SIBERIAN)
                .contestResult(new ContestResult(97, 88, 95))
                .build());

        // when
        List<Cat> resultCatList = catContestAnalyzer.getThreeLeaders(cats);

        // then
        Assertions.assertTrue(expectedCatList.containsAll(resultCatList));
        Assertions.assertTrue(resultCatList.containsAll(expectedCatList));
    }

    @Test
    @DisplayName("Should return three cat-leaders among given cats when nullable values")
    void shouldReturnLeadersWhenNullableValues() {
        // given
        List<Cat> expectedCatList = new ArrayList<>();
        expectedCatList.add(Cat.builder()
                .name("GJZwK")
                .age(11)
                .breed(Cat.Breed.MAINE_COON)
                .contestResult(new ContestResult(94, 74, 99))
                .build());
        expectedCatList.add(Cat.builder()
                .name("zqosj")
                .age(0)
                .breed(Cat.Breed.SIBERIAN)
                .contestResult(new ContestResult(96, 91, 76))
                .build());
        expectedCatList.add(Cat.builder()
                .name("wvdxl")
                .age(10)
                .contestResult(new ContestResult(96, 97, 64))
                .build());

        // when
        List<Cat> resultCatList = catContestAnalyzer.getThreeLeaders(nullableCats);

        // then
        Assertions.assertTrue(expectedCatList.containsAll(resultCatList));
        Assertions.assertTrue(resultCatList.containsAll(expectedCatList));
    }

    @Test
    @DisplayName("Should return an empty list when given an empty list")
    void shouldReturnEmptyCatListWhenEmptyList() {
        // when
        List<Cat> result = catContestAnalyzer.getThreeLeaders(new ArrayList<>());

        // then
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return true when all result sums are greater than 0")
    void shouldReturnTrueWhenNonNullableSum() {
        // when-then
        Assertions.assertTrue(catContestAnalyzer.validateResultSumNotNull(cats));
    }

    @Test
    @DisplayName("Should return false when there is any result sum of 0")
    void shouldReturnFalseWhenNullableSum() {
        // when-then
        Assertions.assertFalse(catContestAnalyzer.validateResultSumNotNull(nullableCats));
    }

    @Test
    @DisplayName("Should return true when given an empty list")
    void shouldReturnTrueWhenEmptyListSum() {
        // when-then
        Assertions.assertTrue(catContestAnalyzer.validateResultSumNotNull(new ArrayList<>()));
    }

    @Test
    @DisplayName("Should return true when all results are greater than 0")
    void shouldReturnTrueWhenNonNullableResults() {
        // when-then
        Assertions.assertTrue(catContestAnalyzer.validateAllResultsSet(cats));
    }

    @Test
    @DisplayName("Should return false when there is any result of 0")
    void shouldReturnFalseWhenNullableResults() {
        // when-then
        Assertions.assertFalse(catContestAnalyzer.validateAllResultsSet(nullableCats));
    }

    @Test
    @DisplayName("Should return true when given an empty list")
    void shouldReturnTrueWhenEmptyListResults() {
        // when-then
        Assertions.assertTrue(catContestAnalyzer.validateAllResultsSet(new ArrayList<>()));
    }

    @Test
    @DisplayName("Should return a Main Coon cat whose result sum is greater than the average result when non-nullable values")
    void shouldReturnMainCoonCatWithHigherThanAverageResult() {
        // given
        double expectedAverageResult = 15.0;

        // when
        Cat result = catContestAnalyzer.findAnyWithAboveAverageResultByBreed(cats, Cat.Breed.MAINE_COON)
                .orElse(new Cat(null, null, null, null));

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(Cat.Breed.MAINE_COON, result.getBreed());
        Assertions.assertTrue(expectedAverageResult <
                Double.parseDouble(new DecimalFormat("#.00").format(result.getContestResult().getSum())));
    }

    @Test
    @DisplayName("Should return a Persian cat whose result sum is greater than the average result when nullable values")
    void shouldReturnPersianCatWithHigherThanAverageResult() {
        // given
        double expectedAverageResult = 15.0;

        // when
        Cat result = catContestAnalyzer.findAnyWithAboveAverageResultByBreed(nullableCats, Cat.Breed.PERSIAN)
                .orElse(new Cat(null, null, null, null));

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(Cat.Breed.PERSIAN, result.getBreed());
        Assertions.assertTrue(expectedAverageResult <
                Double.parseDouble(new DecimalFormat("#.00").format(result.getContestResult().getSum())));
    }

    @Test
    @DisplayName("Should return an empty optional when given an empty list")
    void shouldReturnEmptyOptionalWhenEmptyList() {
        // when
        Optional<Cat> result = catContestAnalyzer.findAnyWithAboveAverageResultByBreed(new ArrayList<>(), Cat.Breed.PERSIAN);

        // then
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should check the contract for equals and hashcode")
    void equalsHashCodeContracts() {
        // when-then
        EqualsVerifier.forClass(ContestResult.class).verify();
    }
}
