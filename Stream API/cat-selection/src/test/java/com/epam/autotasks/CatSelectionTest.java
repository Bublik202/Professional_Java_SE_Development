package com.epam.autotasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CatSelectionTest {

    public CatSelection catSelection;
    private static List<Cat> cats;
    private static List<Cat> sortedByName;
    private static List<Cat> sortedByAge;
    private static List<Cat> tallCats;
    private static List<Cat> smallCats;
    private static List<String> uniqueNames;

    @BeforeEach
    public void setUp() {
        catSelection = new CatSelection();

        cats = CatTestUtils.readCSVFile(CatTestUtils.CATS_CSV_PATH);
        sortedByName = CatTestUtils.readCSVFile(CatTestUtils.CATS_SORTED_BY_NAME_CSV_PATH);
        sortedByAge = CatTestUtils.readCSVFile(CatTestUtils.CATS_SORTED_BY_AGE_CSV_PATH);
        tallCats = CatTestUtils.readCSVFile(CatTestUtils.TALL_CATS_CSV_PATH);
        smallCats = CatTestUtils.readCSVFile(CatTestUtils.SMALL_CATS_CSV_PATH);
        uniqueNames = CatTestUtils.readCSVFile(CatTestUtils.CATS_WITH_UNIQUE_NAMES_CSV_PATH).stream().map(Cat::getName)
                .toList();
    }

    @Test
    @DisplayName("Should return first 10 cats from a list, ordered by name.")
    void shouldReturnFirst10CatsSortedByName() {
        // given
        int expectedLength = 10;

        // when
        List<Cat> result =
                catSelection.getFirstNCatsSortedByComparator(cats, Comparator.comparing(Cat::getName), expectedLength);

        // then
        Assertions.assertEquals(expectedLength, result.size());
        Assertions.assertTrue(sortedByName.containsAll(result));
        Assertions.assertTrue(result.containsAll(sortedByName));
    }

    @Test
    @DisplayName("Should return a list, ordered by age, without first 100 cats.")
    void shouldReturnWithoutFirst100CatsSortedByAge() {
        // given
        int skip = 100;
        int expectedLength = 900;

        // when
        List<Cat> result =
                catSelection.getWithoutFirstNCatsSortedByComparator(cats, Comparator.comparing(Cat::getAge), skip);

        // then
        Assertions.assertEquals(expectedLength, result.size());
        Assertions.assertTrue(sortedByAge.containsAll(result));
        Assertions.assertTrue(result.containsAll(sortedByAge));
    }

    @Test
    @DisplayName("Should return a list of cats who are taller 30 cm.")
    void shouldReturnCatsTaller30() {
        // given
        int threshold = 30;

        // when
        List<Cat> result = catSelection.getTallCats(cats, threshold);

        // then
        Assertions.assertEquals(tallCats.size(), result.size());
        Assertions.assertTrue(tallCats.containsAll(result));
        Assertions.assertTrue(result.containsAll(tallCats));
    }

    @Test
    @DisplayName("Should return a list of cats who are lighter 5kg.")
    void shouldReturnCatsSmaller5() {
        // given
        int threshold = 5;

        // when
        List<Cat> result = catSelection.getSmallCats(cats, threshold);

        // then
        Assertions.assertEquals(smallCats.size(), result.size());
        Assertions.assertTrue(smallCats.containsAll(result));
        Assertions.assertTrue(result.containsAll(smallCats));
    }

    @Test
    @DisplayName("Should return a list of unique cat names.")
    void shouldReturnUniqueNames() {
        // when
        List<String> result = catSelection.getUniqueNames(cats);

        // then
        Assertions.assertEquals(uniqueNames.size(), result.size());
    }

    @Test
    @DisplayName("Should return an empty list when given an empty list.")
    void shouldReturnEmptyListWhenGivenEmptyList() {
        // given
        List<Cat> cats = new ArrayList<>();
        int expectedLength = 0;

        // when
        List<Cat> firstCatsResult =
                catSelection.getFirstNCatsSortedByComparator(cats, Comparator.comparing(Cat::getName), expectedLength);
        List<Cat> lastCatsResult =
                catSelection.getWithoutFirstNCatsSortedByComparator(cats, Comparator.comparing(Cat::getName),
                        expectedLength);
        List<Cat> smallCatsResult = catSelection.getSmallCats(cats, 10);
        List<Cat> tallCatsResult = catSelection.getTallCats(cats, 10);
        List<String> uniqueNamesResult = catSelection.getUniqueNames(cats);

        // then
        Assertions.assertEquals(expectedLength, firstCatsResult.size());
        Assertions.assertEquals(expectedLength, lastCatsResult.size());
        Assertions.assertEquals(expectedLength, smallCatsResult.size());
        Assertions.assertEquals(expectedLength, tallCatsResult.size());
        Assertions.assertEquals(expectedLength, uniqueNamesResult.size());

        Assertions.assertTrue(firstCatsResult.isEmpty());
        Assertions.assertTrue(lastCatsResult.isEmpty());
        Assertions.assertTrue(smallCatsResult.isEmpty());
        Assertions.assertTrue(tallCatsResult.isEmpty());
        Assertions.assertTrue(uniqueNamesResult.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {-999, 999})
    @DisplayName("Should throw an exception when given an unrealistic threshold.")
    void shouldReturnEmptyListWhenInvalidThreshold(int threshold) {
        // given
        List<Cat> cats = new ArrayList<>();

        // when-then
        Assertions.assertThrows(RuntimeException.class, () -> catSelection.getSmallCats(cats, threshold));
        Assertions.assertThrows(RuntimeException.class, () -> catSelection.getTallCats(cats, threshold));
    }
}