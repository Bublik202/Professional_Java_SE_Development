package com.epam.autotasks;

import static com.epam.autotasks.CatTestUtils.TEST_DATE;
import static com.epam.autotasks.CatTestUtils.TEST_NAME_WITH_ATTENDANT;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ShelterServiceTest {

    private ShelterService shelterService;


    @BeforeEach
    public void setUp() {
        shelterService = new ShelterService();
    }

    @Test
    @DisplayName("Should check an attendant is assigned to each cat.")
    void shouldAssignNonNullAttendants() {
        // given
        List<ShelterRoom> rooms = CatTestUtils.generateRoomsWithDefinedCats(null);

        // when
        shelterService.assignAttendants(rooms);

        // then
        for (ShelterRoom room : rooms) {
            for (Cat cat : room.getCats()) {
                Assertions.assertNotNull(cat.getAttendant());
            }
        }
    }

    @ParameterizedTest
    @EnumSource(Cat.Staff.class)
    @DisplayName("Should check assigned previously attendants are not updated.")
    void shouldNotUpdateAttendants(Cat.Staff attendant) {
        // given
        List<ShelterRoom> rooms = CatTestUtils.generateRoomsWithDefinedCats(attendant);

        // when
        shelterService.assignAttendants(rooms);

        // then
        for (ShelterRoom room : rooms) {
            for (Cat cat : room.getCats()) {
                if ((TEST_NAME_WITH_ATTENDANT).equals(cat.getName())) {
                    Assertions.assertEquals(attendant, cat.getAttendant());
                } else {
                    Assertions.assertNotNull(cat.getAttendant());
                }
            }
        }
    }

    @Test
    @DisplayName("Should check a list of cats, who should have a check-up soon, is returned")
    void shouldGetCatListForCheckUp() {
        //given
        List<ShelterRoom> rooms = CatTestUtils.generateRoomsWithDefinedCats(null);
        int expectedCount = 6;

        // when
        List<Cat> checkUpList = shelterService.getCheckUpList(rooms, TEST_DATE.plusMonths(1).plusDays(1));

        // then
        Assertions.assertNotNull(checkUpList);
        Assertions.assertEquals(expectedCount, checkUpList.size());
    }

    @Test
    @DisplayName("Should check cats with last check-up date the same as input date are not returned")
    void shouldGetCatListForCheckUpDateExclusively() {
        //given
        List<ShelterRoom> rooms = CatTestUtils.generateRoomsWithDefinedCats(null);
        int expectedCount = 3;

        // when
        List<Cat> checkUpList = shelterService.getCheckUpList(rooms, TEST_DATE.plusMonths(1));

        // then
        Assertions.assertEquals(expectedCount, checkUpList.size());
    }

    @Test
    @DisplayName("Should check a list of British cats is returned")
    void shouldGetBritishCats() {
        // given
        List<ShelterRoom> rooms = CatTestUtils.generateRoomsWithDefinedCats(null);
        Cat.Breed breed = Cat.Breed.BRITISH;
        int expectedCount = 3;

        // when
        List<Cat> british = shelterService.getCatsByBreed(rooms, breed);

        // then
        Assertions.assertNotNull(british);
        Assertions.assertEquals(expectedCount, british.size());
    }

    @Test
    @DisplayName("Should check an empty list is returned when no cats by breed are found")
    void shouldGetEmptyList() {
        // given
        List<ShelterRoom> rooms = CatTestUtils.generateRoomsWithDefinedCats(null);
        Cat.Breed breed = Cat.Breed.SIBERIAN;

        // when
        List<Cat> siberians = shelterService.getCatsByBreed(rooms, breed);

        // then
        Assertions.assertNotNull(siberians);
        Assertions.assertTrue(siberians.isEmpty());
    }

    @Test
    @DisplayName("Should return an empty list when given an empty list.")
    void shouldReturnEmptyListWhenGivenEmptyList() {
        // given
        List<ShelterRoom> rooms = new ArrayList<>();

        List<Cat> checkUpList = shelterService.getCheckUpList(rooms, LocalDate.now());
        List<Cat> catsByBreed = shelterService.getCatsByBreed(rooms, null);

        Assertions.assertTrue(checkUpList.isEmpty());
        Assertions.assertTrue(catsByBreed.isEmpty());

        shelterService.assignAttendants(rooms);

        Assertions.assertTrue(rooms.isEmpty());
    }

    @Test
    @DisplayName("Should process an empty list without updates or exceptions.")
    void shouldNotUpdateEmptyListOrThrowAnException() {
        // given
        List<ShelterRoom> rooms = new ArrayList<>();

        // when
        shelterService.assignAttendants(rooms);

        // then
        Assertions.assertTrue(rooms.isEmpty());
    }
}
