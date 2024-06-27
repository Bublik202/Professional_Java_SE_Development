package com.epam.autotasks;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CatContestHelperTest {

    public CatContestHelper catContestHelper;
    private static List<Cat> cats;
    private static List<Cat> nullableCats;

    @BeforeEach
    public void setUp() {
        catContestHelper = new CatContestHelper();
        cats = CatTestUtils.readCSVFile(CatTestUtils.CATS_CSV_PATH);
        nullableCats = CatTestUtils.readCSVFile(CatTestUtils.NULLABLE_CATS_CSV_PATH);
    }

    @Test
    @DisplayName("Should get a number of carriers for cats")
    void shouldGetCarrierNumber() {
        // given
        Integer expectedCarrierNumber = 238;

        // when
        Integer carrierNumber = catContestHelper.getCarrierNumber(cats);
        // then
        Assertions.assertEquals(expectedCarrierNumber, carrierNumber);
    }

    @Test
    @DisplayName("Should get a number of carriers for cats with nullable values")
    void shouldGetNullableCarrierNumber() {
        // given
        Integer expectedCarrierNumber = 193;

        // when
        Integer carrierNumber = catContestHelper.getCarrierNumber(nullableCats);
        // then
        Assertions.assertEquals(expectedCarrierNumber, carrierNumber);
    }

    @Test
    @DisplayName("Should return 0 carriers when given an empty list")
    void shouldGet0CarrierWhenEmptyList() {
        // given
        Integer expectedCarrierNumber = 0;

        // when
        Integer carrierNumber = catContestHelper.getCarrierNumber(new ArrayList<>());
        // then
        Assertions.assertEquals(expectedCarrierNumber, carrierNumber);
    }

    @Test
    @DisplayName("Should get a carrier id")
    void shouldGetCarrierId() {
        // given
        String expectedCarrierId = "CFTDCBRINVYMUNGSFSIBOCTMAITBAPERRQUPERMMUBRIDSESIBKUQPEROBEBRI";

        // when
        String carrierId = catContestHelper.getCarrierId(cats.subList(40, 50));
        // then
        Assertions.assertEquals(expectedCarrierId, carrierId);
    }

    @Test
    @DisplayName("Should get a carrier id when nullable values")
    void shouldGetNullableCarrierId() {
        // given
        String expectedCarrierId = "CFPZDBRIZAKSIBRNUSIBROEMAIBANBRI";

        // when
        String carrierId = catContestHelper.getCarrierId(nullableCats.subList(100, 110));
        // then
        Assertions.assertEquals(expectedCarrierId, carrierId);
    }

    @Test
    @DisplayName("Should return CF when given an empty list")
    void shouldGetCFCarrierIdWhenEmptyList() {
        // given
        String expectedCarrierId = "CF";

        // when
        String carrierId = catContestHelper.getCarrierId(new ArrayList<>());
        // then
        Assertions.assertEquals(expectedCarrierId, carrierId);
    }

    @Test
    @DisplayName("Should get a number of awards for cats")
    void shouldGetAwardsNumber() {
        // given
        Integer expectedAwardNumber = 17257;

        // when
        Integer awardNumber = catContestHelper.countTeamAwards(cats);
        // then
        Assertions.assertEquals(expectedAwardNumber, awardNumber);
    }

    @Test
    @DisplayName("Should get a number of awards for cats with nullable values")
    void shouldGetNullableAwardsNumber() {
        // given
        Integer expectedAwardNumber = 13457;

        // when
        Integer awardNumber = catContestHelper.countTeamAwards(nullableCats);
        // then
        Assertions.assertEquals(expectedAwardNumber, awardNumber);
    }

    @Test
    @DisplayName("Should return 0 awards when given an empty list")
    void shouldGet0AwardsWhenEmptyList() {
        // given
        Integer expectedAwardNumber = 0;

        // when
        Integer awardNumber = catContestHelper.countTeamAwards(new ArrayList<>());
        // then
        Assertions.assertEquals(expectedAwardNumber, awardNumber);
    }
}
