package com.epam.autotasks;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomStringUtils;

public class CatTestUtils {

    public static final String CATS_CSV_PATH = "src/test/resources/cats.csv";
    public static final String CATS_SORTED_BY_NAME_CSV_PATH = "src/test/resources/cats_sorted_by_name.csv";
    public static final String CATS_SORTED_BY_AGE_CSV_PATH = "src/test/resources/cats_sorted_by_age.csv";
    public static final String TALL_CATS_CSV_PATH = "src/test/resources/tall_cats.csv";
    public static final String SMALL_CATS_CSV_PATH = "src/test/resources/small_cats.csv";
    public static final String CATS_WITH_UNIQUE_NAMES_CSV_PATH = "src/test/resources/cats_with_unique_names.csv";
    public static final Integer CAT_COUNT = 1000;
    protected static final String[] HEADERS = {"name", "age", "weight", "height", "breed"};
    private static final Integer MAX_AGE_WEIGHT = 15;
    private static final Integer MIN_HEIGHT = 10;
    private static final Integer MAX_HEIGHT = 40;
    private static final Integer THRESHOLD = 8;
    private static final Random random = new Random();

    private CatTestUtils() {
    }

    @SneakyThrows
    public static void createCSVFile(String path) {
        List<Cat> cats;
        if (CATS_CSV_PATH.equals(path)) {
            cats = generateCats(CAT_COUNT);
        } else {
            cats = generateCatsWithNullableValues(CAT_COUNT);
        }
        FileWriter out = new FileWriter(path);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(HEADERS))) {
            cats.forEach(cat -> {
                try {
                    printer.printRecord(cat.getName(), cat.getAge(), cat.getWeight(), cat.getHeight(), cat.getBreed());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    @SneakyThrows
    public static List<Cat> readCSVFile(String path) {
        List<Cat> cats = new ArrayList<>();
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);
        for (CSVRecord record : records) {
            cats.add(Cat.builder()
                    .name(record.get(0))
                    .age(validateRecord(record, 1) ? Integer.parseInt(record.get(1)) : 0)
                    .weight(validateRecord(record, 2) ? Integer.parseInt(record.get(2)) : 0)
                    .height(validateRecord(record, 3) ? Integer.parseInt(record.get(3)) : 0)
                    .breed(validateRecord(record, 4) ? Cat.Breed.valueOf(record.get(4)) : null)
                    .build());
        }
        return cats;
    }

    private static boolean validateRecord(CSVRecord record, int index) {
        return !Objects.equals(record.get(index), "");
    }

    public static List<Cat> generateCats(int count) {

        List<Cat> cats = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            cats.add(Cat.builder()
                    .name(RandomStringUtils.randomAlphabetic(5))
                    .age(random.nextInt(MAX_AGE_WEIGHT))
                    .weight(random.nextInt(MAX_AGE_WEIGHT))
                    .height(random.nextInt(MIN_HEIGHT, MAX_HEIGHT))
                    .breed(Cat.Breed.values()[random.nextInt(Cat.Breed.values().length)])
                    .build());
        }
        return cats;
    }

    public static List<Cat> generateCatsWithNullableValues(int count) {

        List<Cat> cats = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            cats.add(Cat.builder()
                    .name(getNullableName())
                    .age(getNullableAgeWeight())
                    .weight(getNullableAgeWeight())
                    .height(getNullableHeight())
                    .breed(getNullableBreed())
                    .build());
        }
        return cats;
    }

    private static String getNullableName() {
        if (random.nextInt(10) < THRESHOLD) {
            return RandomStringUtils.randomAlphabetic(5);
        }
        return null;
    }

    private static Cat.Breed getNullableBreed() {
        if (random.nextInt(10) < THRESHOLD) {
            return Cat.Breed.values()[random.nextInt(Cat.Breed.values().length)];
        }
        return null;
    }

    private static Integer getNullableAgeWeight() {
        if (random.nextInt(10) < THRESHOLD) {
            return random.nextInt(MAX_AGE_WEIGHT);
        }
        return null;
    }

    private static Integer getNullableHeight() {
        if (random.nextInt(10) < THRESHOLD) {
            return random.nextInt(MIN_HEIGHT, MAX_HEIGHT);
        }
        return null;
    }
}