package com.epam.autotasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class CatTestUtils {

    public static final LocalDate TEST_DATE = LocalDate.now();
    public static final String TEST_NAME_WITH_ATTENDANT = "Alex0";
    private static final String TEST_NAME_BASE = "Alex";
    private static final Integer TEST_ROOM_COUNT = 3;
    private static final Integer TEST_CAT_COUNT = 3;
    private static final Integer THRESHOLD = 8;
    private static final Integer MAX_AGE = 15;
    private static final Integer START_DATE = 2020;
    private static final Integer END_DATE = 2023;
    private static final Random random = new Random();

    private CatTestUtils() {
    }

    public static List<Cat> generateCatsWithoutAttendant(int count) {

        List<Cat> cats = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            cats.add(Cat.builder()
                    .name(RandomStringUtils.randomAlphabetic(5))
                    .age(random.nextInt(MAX_AGE))
                    .breed(Cat.Breed.values()[random.nextInt(Cat.Breed.values().length)])
                    .lastCheckUpDate(createRandomDate(START_DATE, END_DATE))
                    .build());
        }
        return cats;
    }

    public static List<ShelterRoom> generateRooms(int count, int catsPerRoom, boolean nullableValues) {
        List<ShelterRoom> rooms = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            rooms.add(new ShelterRoom(
                    nullableValues ? generateCatsWithNullableValues(catsPerRoom) :
                            generateCatsWithoutAttendant(catsPerRoom)));
        }
        return rooms;
    }

    public static List<Cat> generateCatsWithNullableValues(int count) {

        List<Cat> cats = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            cats.add(Cat.builder()
                    .name(getNullableName())
                    .age(getNullableAge())
                    .breed(getNullableBreed())
                    .lastCheckUpDate(getNullableDate())
                    .build());
        }
        return cats;
    }

    public static List<ShelterRoom> generateRoomsWithDefinedCats(Cat.Staff attendant) {
        List<ShelterRoom> rooms = new ArrayList<>();

        for (int i = 0; i < TEST_ROOM_COUNT; i++) {
            ShelterRoom room = new ShelterRoom(new ArrayList<>());

            for (int j = 0; j < TEST_CAT_COUNT; j++) {
                Cat cat = Cat.builder()
                        .name(TEST_NAME_BASE + j)
                        .age(2)
                        .breed(Cat.Breed.values()[i])
                        .lastCheckUpDate(TEST_DATE.plusMonths(j))
                        .build();

                if (TEST_NAME_WITH_ATTENDANT.equals(cat.getName())) {
                    cat.setAttendant(attendant);
                }
                room.getCats().add(cat);
            }
            rooms.add(room);
        }
        return rooms;
    }

    private static String getNullableName() {
        if (random.nextInt(10) < THRESHOLD) {
            return RandomStringUtils.randomAlphabetic(5);
        }
        return null;
    }

    private static Integer getNullableAge() {
        if (random.nextInt(10) < THRESHOLD) {
            return random.nextInt(MAX_AGE);
        }
        return null;
    }

    private static Cat.Breed getNullableBreed() {
        if (random.nextInt(10) < THRESHOLD) {
            return Cat.Breed.values()[random.nextInt(Cat.Breed.values().length)];
        }
        return null;
    }

    private static LocalDate getNullableDate() {
        if (random.nextInt(10) < THRESHOLD) {
            return createRandomDate(START_DATE, END_DATE);
        }
        return null;
    }

    private static LocalDate createRandomDate(int startYear, int endYear) {
        int day = random.nextInt(1, 29);
        int month = random.nextInt(1, 13);
        int year = random.nextInt(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
}
