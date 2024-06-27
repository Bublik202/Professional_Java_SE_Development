package com.epam.autotasks;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CatTestUtils {

    public static final String CATS_CSV_PATH = "src/test/resources/cats.csv";
    public static final String NULLABLE_CATS_CSV_PATH = "src/test/resources/nullable_cats.csv";
    static String[] HEADERS = {"name", "age", "breed", "running", "jumping", "purring"};

    private CatTestUtils() {
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
                    .contestResult(
                            new ContestResult(validateRecord(record, 3) ? Integer.parseInt(record.get(3)) : 0,
                                    validateRecord(record, 4) ? Integer.parseInt(record.get(4)) : 0,
                                    validateRecord(record, 5) ? Integer.parseInt(record.get(5)) : 0))
                    .build());
        }
        return cats;
    }

    private static boolean validateRecord(CSVRecord record, int index) {
        return !Objects.equals(record.get(index), "");
    }
}
