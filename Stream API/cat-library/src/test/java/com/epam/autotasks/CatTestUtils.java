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
    public static final Integer CAT_COUNT = 1000;
    private static final String[] HEADERS = {"name", "age", "breed", "weight", "awards", "running", "jumping", "purring"};
    private static final Integer MAX_AGE_WEIGHT = 15;
    private static final Integer MAX_AWARDS = 35;
    private static final Integer MAX_COMPETITION_RESULT = 100;
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
                    printer.printRecord(cat.getName(), cat.getAge(), cat.getBreed(), cat.getWeight(), cat.getAwards(),
                            cat.getContestResult().getRunning(), cat.getContestResult().getJumping(),
                            cat.getContestResult().getPurring());
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
                    .weight(validateRecord(record, 3) ? Integer.parseInt(record.get(3)) : 0)
                    .awards(validateRecord(record, 4) ? Integer.parseInt(record.get(4)) : 0)
                    .contestResult(
                            new ContestResult(validateRecord(record, 5) ? Integer.parseInt(record.get(5)) : 0,
                                    validateRecord(record, 6) ? Integer.parseInt(record.get(6)) : 0,
                                    validateRecord(record, 7) ? Integer.parseInt(record.get(7)) : 0))
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
                    .breed(Cat.Breed.values()[random.nextInt(Cat.Breed.values().length)])
                    .weight(random.nextInt(MAX_AGE_WEIGHT))
                    .awards(random.nextInt(MAX_AWARDS))
                    .contestResult(new ContestResult(random.nextInt(MAX_COMPETITION_RESULT),
                            random.nextInt(MAX_COMPETITION_RESULT), random.nextInt(MAX_COMPETITION_RESULT)))
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
                    .breed(getNullableBreed())
                    .weight(getNullableAgeWeight())
                    .awards(getNullableAwards())
                    .contestResult(new ContestResult(getNullableCompetitionResult(),
                            getNullableCompetitionResult(), getNullableCompetitionResult()))
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

    private static Integer getNullableAwards() {
        if (random.nextInt(10) < THRESHOLD) {
            return random.nextInt(MAX_AWARDS);
        }
        return null;
    }

    private static Integer getNullableCompetitionResult() {
        if (random.nextInt(10) < THRESHOLD) {
            return random.nextInt(MAX_COMPETITION_RESULT);
        }
        return null;
    }
}
