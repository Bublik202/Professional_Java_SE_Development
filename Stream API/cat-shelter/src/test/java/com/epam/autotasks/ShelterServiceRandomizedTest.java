package com.epam.autotasks;

import static com.epam.autotasks.CatTestUtils.TEST_DATE;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShelterServiceRandomizedTest {

    private ShelterService shelterService;
    private final Integer ROOM_COUNT = 10;
    private final Integer CAT_COUNT = 100;


    @BeforeEach
    public void setUp() {
        shelterService = new ShelterService();
    }

    @Test
    @DisplayName("Should check an attendant is assigned to each cat.")
    void shouldAssignNonNullAttendants() {
        // given
        List<ShelterRoom> rooms = CatTestUtils.generateRooms(ROOM_COUNT, CAT_COUNT, false);

        // verify 'before' values
        for (ShelterRoom room : rooms) {
            for (Cat cat : room.getCats()) {
                Assertions.assertNull(cat.getAttendant());
            }
        }

        // when
        shelterService.assignAttendants(rooms);

        // then
        for (ShelterRoom room : rooms) {
            for (Cat cat : room.getCats()) {
                Assertions.assertNotNull(cat.getAttendant());
            }
        }
    }

    @Test
    @DisplayName("Should check nullable date values are processed properly without throwing an exception")
    void shouldGetCatListForCheckUpWithNullableDates() {
        // given
        List<ShelterRoom> rooms = CatTestUtils.generateRooms(ROOM_COUNT, CAT_COUNT, true);

        // when
        List<Cat> checkUpList = shelterService.getCheckUpList(rooms, TEST_DATE);

        // then
        Assertions.assertNotNull(checkUpList);
        Assertions.assertFalse(checkUpList.isEmpty());
    }

    @Test
    @DisplayName("Should check nullable breed values are processed properly without throwing an exception")
    void shouldGetBritishCatsWhenNullableBreed() {
        // given
        List<ShelterRoom> rooms = CatTestUtils.generateRooms(ROOM_COUNT, CAT_COUNT, true);

        // when
        List<Cat> britishCats = shelterService.getCatsByBreed(rooms, Cat.Breed.MUNCHKIN);

        // then
        Assertions.assertNotNull(britishCats);
        Assertions.assertFalse(britishCats.isEmpty());
    }
}