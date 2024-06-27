package com.epam.autotasks;

import com.google.common.collect.ImmutableTable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CatDataProcessorTest {

    public CatDataProcessor catDataProcessor;
    private static List<Cat> cats;
    private static List<Cat> nullableCats;

    @BeforeEach
    public void setUp() {
        catDataProcessor = new CatDataProcessor();
        cats = CatTestUtils.readCSVFile(CatTestUtils.CATS_CSV_PATH);
        nullableCats = CatTestUtils.readCSVFile(CatTestUtils.NULLABLE_CATS_CSV_PATH);
    }

    @Test
    @DisplayName("Should get a cat table mapped by name and breed")
    void shouldCreateCatTable() {
        // given
        int mapSize = 1000;
        int mapColumnSize = 5;

        // when
        ImmutableTable<String, Cat.Breed, Integer> catMap = catDataProcessor.createCatTable(cats);

        // then
        Assertions.assertEquals(mapSize, catMap.size());
        Assertions.assertEquals(mapSize, catMap.rowMap().size());
        Assertions.assertEquals(mapColumnSize, catMap.columnMap().size());
        catMap.values().forEach(Assertions::assertNotNull);
    }

    @Test
    @DisplayName("Should get a cat table mapped by name and breed when nullable values")
    void shouldCreateNullableCatTable() {
        // given
        int mapSize = 646;
        int mapColumnSize = 5;

        // when
        ImmutableTable<String, Cat.Breed, Integer> catMap = catDataProcessor.createCatTable(nullableCats);

        // then
        Assertions.assertEquals(mapSize, catMap.size());
        Assertions.assertEquals(mapSize, catMap.rowMap().size());
        Assertions.assertEquals(mapColumnSize, catMap.columnMap().size());
        catMap.values().forEach(Assertions::assertNotNull);
    }

    @Test
    @DisplayName("Should get an empty map when given an empty list")
    void shouldGetEmptyTable() {
        // when
        ImmutableTable<String, Cat.Breed, Integer> catMap = catDataProcessor.createCatTable(new ArrayList<>());

        // then
        Assertions.assertTrue(catMap.isEmpty());
    }

    @Test
    @DisplayName("Should get a cat json array")
    void shouldCreateCatJson() {
        // given
        Map<String, Object> firstCat = createCat("tUjFY", "6", "12", "5", "53", "63", "121", "BRITISH", "12");
        Map<String, Object> secondCat = createCat("lzobK", "27", "2", "54", "78", "60", "192", "PERSIAN", "8");
        Map<String, Object> thirdCat = createCat("NRtIT", "32", "12", "67", "46", "29", "142", "SIBERIAN", "9");
        List<Map<String, Object>> expectedCats = List.of(firstCat, secondCat, thirdCat);

        // when
        JSONArray catJson = catDataProcessor.createCatJson(cats.subList(0, 3));

        // then
        Assertions.assertFalse(catJson.isEmpty());
        for (int i = 0; i < expectedCats.size(); i++) {
            for (String key : expectedCats.get(i).keySet()) {
                if ("contestResult".equals(key)) {
                    JSONObject contestResults = (JSONObject) ((JSONObject) catJson.get(i)).get(key);
                    Assertions.assertEquals(((JSONObject) expectedCats.get(i).get(key)).get("running"),
                            contestResults.get("running").toString());
                    Assertions.assertEquals(((JSONObject) expectedCats.get(i).get(key)).get("purring"),
                            contestResults.get("purring").toString());
                    Assertions.assertEquals(((JSONObject) expectedCats.get(i).get(key)).get("jumping"),
                            contestResults.get("jumping").toString());
                    Assertions.assertEquals(((JSONObject) expectedCats.get(i).get(key)).get("sum"),
                            contestResults.get("sum").toString());

                } else {
                    Assertions.assertEquals(expectedCats.get(i).get(key),
                            ((JSONObject) catJson.get(i)).get(key).toString());
                }
            }
        }
    }

    @Test
    @DisplayName("Should get a nullable cat json array")
    void shouldCreateNullableCatJson() {
        // given
        Map<String, Object> firstCat = createCat("mqBDs", "11", "13", "60", "18", "51", "129", "BRITISH", "5");
        Map<String, Object> secondCat = createCat("vxsTj", "3", "10", "83", "87", "5", "175", null, "0");
        Map<String, Object> thirdCat = createCat("SeWVa", "0", "5", "16", "47", "73", "136", "PERSIAN", "2");
        List<Map<String, Object>> expectedCats = List.of(firstCat, secondCat, thirdCat);

        // when
        JSONArray catJson = catDataProcessor.createCatJson(nullableCats.subList(0, 3));

        // then
        Assertions.assertFalse(catJson.isEmpty());
        for (int i = 0; i < expectedCats.size(); i++) {
            for (String key : expectedCats.get(i).keySet()) {
                if (i == 1 && "breed".equals(key)) {
                    Assertions.assertThrows(JSONException.class, () -> ((JSONObject) catJson.get(1)).get("breed"));
                } else {
                    if ("contestResult".equals(key)) {
                        JSONObject contestResults = (JSONObject) ((JSONObject) catJson.get(i)).get(key);
                        Assertions.assertEquals(((JSONObject) expectedCats.get(i).get(key)).get("running"),
                                contestResults.get("running").toString());
                        Assertions.assertEquals(((JSONObject) expectedCats.get(i).get(key)).get("purring"),
                                contestResults.get("purring").toString());
                        Assertions.assertEquals(((JSONObject) expectedCats.get(i).get(key)).get("jumping"),
                                contestResults.get("jumping").toString());
                        Assertions.assertEquals(((JSONObject) expectedCats.get(i).get(key)).get("sum"),
                                contestResults.get("sum").toString());

                    } else {
                        Assertions.assertEquals(expectedCats.get(i).get(key),
                                ((JSONObject) catJson.get(i)).get(key).toString());
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Should get an empty JSON when given an empty list")
    void shouldGetEmptyJson() {
        // when
        JSONArray catMap = catDataProcessor.createCatJson(new ArrayList<>());

        // then
        Assertions.assertTrue(catMap.isEmpty());
    }

    private Map<String, Object> createCat(String name, String awards, String weight, String running, String purring,
                                          String jumping, String sum, String breed, String age) {
        Map<String, Object> catValues = new HashMap<>();
        catValues.put("name", name);
        catValues.put("awards", awards);
        catValues.put("weight", weight);

        JSONObject catContest = new JSONObject();
        catContest.put("running", running);
        catContest.put("purring", purring);
        catContest.put("jumping", jumping);
        catContest.put("sum", sum);
        catValues.put("contestResult", catContest);
        catValues.put("breed", breed);
        catValues.put("age", age);
        return catValues;
    }
}
