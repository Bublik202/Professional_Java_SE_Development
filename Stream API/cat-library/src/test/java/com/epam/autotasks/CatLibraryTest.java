package com.epam.autotasks;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CatLibraryTest {

    public CatLibrary catLibrary;
    private static List<Cat> cats;
    private static List<Cat> nullableCats;

    @BeforeEach
    public void setUp() {
        catLibrary = new CatLibrary();
        cats = CatTestUtils.readCSVFile(CatTestUtils.CATS_CSV_PATH);
        nullableCats = CatTestUtils.readCSVFile(CatTestUtils.NULLABLE_CATS_CSV_PATH);
    }

    @Test
    @DisplayName("Should get cats mapped by name")
    void shouldMapCatsByName() {
        // given
        int mapSize = 1000;

        // when
        Map<String, Cat> catMap = catLibrary.mapCatsByName(cats);

        // then
        Assertions.assertEquals(mapSize, catMap.size());
        Assertions.assertNull(catMap.get(null));
        catMap.values().forEach(Assertions::assertNotNull);
    }

    @Test
    @DisplayName("Should get cats mapped by name when nullable values")
    void shouldMapNullableCatsByName() {
        // given
        int mapSize = 810;

        // when
        Map<String, Cat> catMap = catLibrary.mapCatsByName(nullableCats);

        // then
        Assertions.assertEquals(mapSize, catMap.size());
        Assertions.assertNull(catMap.get(null));
        catMap.values().forEach(Assertions::assertNotNull);
    }

    @Test
    @DisplayName("Should get an empty map when given an empty list")
    void shouldReturnEmptyNameMapWhenEmptyList() {
        // when
        Map<String, Cat> catMap = catLibrary.mapCatsByName(new ArrayList<>());

        // then
        Assertions.assertTrue(catMap.isEmpty());
    }

    @Test
    @DisplayName("Should get cats mapped by breed")
    void shouldMapCatsByBreed() {
        // given
        int keySetSize = 5;

        // when
        Map<Cat.Breed, Set<Cat>> catMap = catLibrary.mapCatsByBreed(cats);

        // then
        Assertions.assertEquals(keySetSize, catMap.keySet().size());
        catMap.values().forEach(catSet -> Assertions.assertTrue(catSet.size() > 0));
        Assertions.assertEquals(CatTestUtils.CAT_COUNT, catMap.values().stream().mapToInt(Collection::size).sum());
    }

    @Test
    @DisplayName("Should get cats mapped by breed with nullable values")
    void shouldMapNullableCatsByBreed() {
        // given
        int keySetSize = 5;
        int nonNullCatNumber = 800;

        // when
        Map<Cat.Breed, Set<Cat>> catMap = catLibrary.mapCatsByBreed(nullableCats);

        // then
        Assertions.assertEquals(keySetSize, catMap.keySet().size());
        catMap.values().forEach(catSet -> Assertions.assertTrue(catSet.size() > 0));
        Assertions.assertEquals(nonNullCatNumber, catMap.values().stream().mapToInt(Collection::size).sum());
    }

    @Test
    @DisplayName("Should get an empty map when given an empty list")
    void shouldReturnEmptyBreedMapWhenEmptyList() {
        // when
        Map<Cat.Breed, Set<Cat>> catMap = catLibrary.mapCatsByBreed(new ArrayList<>());

        // then
        Assertions.assertTrue(catMap.isEmpty());
    }

    @Test
    @DisplayName("Should get cat names mapped by breed")
    void shouldMapCatNamesByBreed() {
        // given
        int keySetSize = 5;

        // when
        Map<Cat.Breed, String> catMap = catLibrary.mapCatNamesByBreed(cats.subList(0, 25));

        // then
        Assertions.assertEquals(keySetSize, catMap.keySet().size());
        Assertions.assertEquals("Cat names: tUjFY, gBhNT, qdFKy, lQSMh, pWQrO.", catMap.get(Cat.Breed.BRITISH));
        Assertions.assertEquals("Cat names: rAROw, dskMG.", catMap.get(Cat.Breed.MAINE_COON));
        Assertions.assertEquals("Cat names: drMoM, hlbEk, tTsBW, xKDDH.", catMap.get(Cat.Breed.MUNCHKIN));
        Assertions.assertEquals("Cat names: lzobK, kawJr, EZcVi, CZnEs, jHnTS, qYoqF, aQBwH.",
                catMap.get(Cat.Breed.PERSIAN));
        Assertions.assertEquals("Cat names: NRtIT, kgtWf, xSQmJ, JaHVy, jkNYJ, HJkqf, WnBpl.",
                catMap.get(Cat.Breed.SIBERIAN));
    }

    @Test
    @DisplayName("Should get cat names mapped by breed when nullable values")
    void shouldMapNullableCatNamesByBreed() {
        // given
        int keySetSize = 5;

        // when
        Map<Cat.Breed, String> catMap = catLibrary.mapCatNamesByBreed(nullableCats.subList(0, 25));

        // then
        Assertions.assertEquals(keySetSize, catMap.keySet().size());
        Assertions.assertEquals("Cat names: mqBDs, JOmnc.", catMap.get(Cat.Breed.BRITISH));
        Assertions.assertEquals("Cat names: LrbKw, LGmjB, brEOn, AZtiS, KxbDp.", catMap.get(Cat.Breed.MAINE_COON));
        Assertions.assertEquals("Cat names: NDVpl, iJOQy, VeJff.", catMap.get(Cat.Breed.MUNCHKIN));
        Assertions.assertEquals("Cat names: SeWVa, kNqtI, IwGBK, kCgwm.", catMap.get(Cat.Breed.PERSIAN));
        Assertions.assertEquals("Cat names: yQPBS, KmDiM, xbnSF, Tzvhb, KuHFL, oJoif, LziMO.",
                catMap.get(Cat.Breed.SIBERIAN));
    }

    @Test
    @DisplayName("Should get an empty breed-name map when given an empty list")
    void shouldReturnEmptyBreedNameMapWhenEmptyList() {
        // when
        Map<Cat.Breed, String> catMap = catLibrary.mapCatNamesByBreed(new ArrayList<>());

        // then
        Assertions.assertTrue(catMap.isEmpty());
    }

    @Test
    @DisplayName("Should return a map of average results mapped by breed")
    void shouldMapAverageResultsByBreed() {
        // given
        int maxKeySetSize = 5;
        double britishAverage = 147.28;
        double mainCoonAverage = 145.7;
        double munchkinAverage = 145.83;
        double persianAverage = 151.44;
        double siberianAverage = 147.31;

        // when
        Map<Cat.Breed, Double> averageMap = catLibrary.mapAverageResultByBreed(cats);

        // then
        Assertions.assertTrue(maxKeySetSize >= averageMap.keySet().size());
        Assertions.assertEquals(britishAverage,
                Double.valueOf(new DecimalFormat("#.00").format(averageMap.get(Cat.Breed.BRITISH))));
        Assertions.assertEquals(mainCoonAverage,
                Double.valueOf(new DecimalFormat("#.00").format(averageMap.get(Cat.Breed.MAINE_COON))));
        Assertions.assertEquals(munchkinAverage,
                Double.valueOf(new DecimalFormat("#.00").format(averageMap.get(Cat.Breed.MUNCHKIN))));
        Assertions.assertEquals(persianAverage,
                Double.valueOf(new DecimalFormat("#.00").format(averageMap.get(Cat.Breed.PERSIAN))));
        Assertions.assertEquals(siberianAverage,
                Double.valueOf(new DecimalFormat("#.00").format(averageMap.get(Cat.Breed.SIBERIAN))));
    }

    @Test
    @DisplayName("Should return a map of average results mapped by breed when nullable cats")
    void shouldMapAverageResultsByBreedWhenNullableCats() {
        // given
        int maxKeySetSize = 5;
        double britishAverage = 122.04;
        double mainCoonAverage = 121.44;
        double munchkinAverage = 120.7;
        double persianAverage = 114.24;
        double siberianAverage = 112.25;

        // when
        Map<Cat.Breed, Double> averageMap = catLibrary.mapAverageResultByBreed(nullableCats);

        // then
        Assertions.assertTrue(maxKeySetSize >= averageMap.keySet().size());
        Assertions.assertEquals(britishAverage,
                Double.valueOf(new DecimalFormat("#.00").format(averageMap.get(Cat.Breed.BRITISH))));
        Assertions.assertEquals(mainCoonAverage,
                Double.valueOf(new DecimalFormat("#.00").format(averageMap.get(Cat.Breed.MAINE_COON))));
        Assertions.assertEquals(munchkinAverage,
                Double.valueOf(new DecimalFormat("#.00").format(averageMap.get(Cat.Breed.MUNCHKIN))));
        Assertions.assertEquals(persianAverage,
                Double.valueOf(new DecimalFormat("#.00").format(averageMap.get(Cat.Breed.PERSIAN))));
        Assertions.assertEquals(siberianAverage,
                Double.valueOf(new DecimalFormat("#.00").format(averageMap.get(Cat.Breed.SIBERIAN))));
    }

    @Test
    @DisplayName("Should get an empty average map when given an empty list")
    void shouldReturnEmptyAverageMapWhenEmptyList() {
        // when
        Map<Cat.Breed, Double> catMap = catLibrary.mapAverageResultByBreed(new ArrayList<>());

        // then
        Assertions.assertTrue(catMap.isEmpty());
    }

    @Test
    @DisplayName("Should get cats ordered by contest results")
    void shouldGetSortedCatsByContestResults() {
        // given
        int setSize = 1000;

        // when
        SortedSet<Cat> catSet = catLibrary.getOrderedCatsByContestResults(cats);

        // then
        Assertions.assertEquals(setSize, catSet.size());
        catSet.forEach(Assertions::assertNotNull);

        Iterator<Cat> iterator = catSet.iterator();
        while (iterator.hasNext()) {
            Cat firstCat = iterator.next();
            Cat secondCat = null;
            if (iterator.hasNext()) {
                secondCat = iterator.next();
            }
            if (secondCat != null) {
                Assertions.assertTrue(firstCat.getContestResult().getSum() >= secondCat.getContestResult().getSum());
            }
        }
    }

    @Test
    @DisplayName("Should get an empty set when given an empty list")
    void shouldReturnEmptySetWhenEmptyList() {
        // when
        SortedSet<Cat> catSet = catLibrary.getOrderedCatsByContestResults(new ArrayList<>());

        // then
        Assertions.assertTrue(catSet.isEmpty());
    }
}
