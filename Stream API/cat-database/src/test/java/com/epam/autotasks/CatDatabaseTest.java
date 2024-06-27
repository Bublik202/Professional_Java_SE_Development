package com.epam.autotasks;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CatDatabaseTest {

    public static CatDatabase catDatabase;
    private static List<Cat> cats;
    private static List<Cat> nullableCats;

    @BeforeAll
    static void setUp() {
        catDatabase = new CatDatabase();
        cats = CatTestUtils.readCSVFile(CatTestUtils.CATS_CSV_PATH);
        nullableCats = CatTestUtils.readCSVFile(CatTestUtils.NULLABLE_CATS_CSV_PATH);
    }

    @Test
    @DisplayName("Should return Maine Coon names when nonnull values")
    void shouldReturnMaineCoonNames() {
        // given
        int expectedLength = 187;

        // when
        List<String> result = catDatabase.getCatNamesByBreed(cats, Cat.Breed.MAINE_COON);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedLength, result.size());
    }

    @Test
    @DisplayName("Should return Siberian names when nullable values")
    void shouldReturnNullableSiberianNames() {
        // given
        int expectedLength = 165;

        // when
        List<String> result = catDatabase.getCatNamesByBreed(nullableCats, Cat.Breed.SIBERIAN);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedLength, result.size());
    }

    @Test
    @DisplayName("Should return cats under a specific age")
    void shouldReturnCatsYounger4Years() {
        // given
        int age = 4;
        int expectedLength = 343;

        // when
        List<Cat> result = catDatabase.filterYoungerCatsByAge(cats, age);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedLength, result.size());
    }

    @Test
    @DisplayName("Should return cats under a specific age when nullable values")
    void shouldReturnCatsYounger4YearsWhenNullable() {
        // given
        int age = 3;
        int expectedLength = 421;

        // when
        List<Cat> result = catDatabase.filterYoungerCatsByAge(nullableCats, age);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedLength, result.size());
    }

    @Test
    @DisplayName("Should return cats whose name starts with 'A' or 'a'")
    void shouldReturnCatsWithNameA() {
        // given
        String nameStart = "A";
        int expectedLength = 31;

        // when
        List<Cat> result = catDatabase.filterCatsByNamePrefix(cats, nameStart);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedLength, result.size());
    }

    @Test
    @DisplayName("Should return cats whose name starts with 'J' or 'j' when nullable values")
    void shouldReturnCatsWithNameJ() {
        // given
        String nameStart = "j";
        int expectedLength = 34;

        // when
        List<Cat> result = catDatabase.filterCatsByNamePrefix(nullableCats, nameStart);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedLength, result.size());
    }

    @Test
    void shouldReturnEmptyListWhenGivenEmptyList() {
        // given
        List<Cat> cats = new ArrayList<>();
        int expectedLength = 0;

        // when
        List<String> catNamesByBreedResult =
                catDatabase.getCatNamesByBreed(cats, Cat.Breed.SIBERIAN);
        List<Cat> youngCatsResult =
                catDatabase.filterYoungerCatsByAge(cats, 0);
        List<Cat> catNamesByPrefixResult =
                catDatabase.filterCatsByNamePrefix(cats, "");

        // then
        Assertions.assertEquals(expectedLength, catNamesByBreedResult.size());
        Assertions.assertEquals(expectedLength, youngCatsResult.size());
        Assertions.assertEquals(expectedLength, catNamesByPrefixResult.size());
        Assertions.assertTrue(catNamesByBreedResult.isEmpty());
        Assertions.assertTrue(youngCatsResult.isEmpty());
        Assertions.assertTrue(catNamesByPrefixResult.isEmpty());
    }
}