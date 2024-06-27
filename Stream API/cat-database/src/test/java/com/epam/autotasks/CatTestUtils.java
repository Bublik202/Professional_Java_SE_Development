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
    public static final String NULLABLE_CATS_CSV_PATH = "src/test/resources/nullable_cats.csv";
    private static final String[] HEADERS = {"name", "age", "breed"};
    public static final Integer CAT_COUNT = 1000;
    public static Random random = new Random();
    public static final Integer THRESHOLD = 8;

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
                    printer.printRecord(cat.getName(), cat.getAge(), cat.getBreed());
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
                    .breed(validateRecord(record, 2) ? Cat.Breed.valueOf(record.get(2)) : null)
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
                    .age(random.nextInt(15))
                    .breed(Cat.Breed.getBreedByCode(new Random().nextInt(5)))
                    .build());
        }
        return cats;
    }

    public static List<Cat> generateCatsWithNullableValues(int count) {

        List<Cat> cats = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            cats.add(Cat.builder()
                    .name(getNullableName())
                    .age(getNullableAge())
                    .breed(getNullableBreed())
                    .build());
        }
        return cats;
    }

    private static Cat.Breed getNullableBreed() {
        if (random.nextInt(10) < THRESHOLD) {
            return Cat.Breed.getBreedByCode(random.nextInt(5));
        }
        return null;
    }

    private static String getNullableName() {
        if (random.nextInt(10) < THRESHOLD) {
            return RandomStringUtils.randomAlphabetic(5);
        }
        return null;
    }

    private static Integer getNullableAge() {
        if (random.nextInt(10) < THRESHOLD) {
            return random.nextInt(15);
        }
        return null;
    }
}