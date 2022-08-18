package com.decathlon.test;

import com.decathlon.domain.DecathlonPlayer;

import static org.junit.jupiter.api.Assertions.*;

import com.decathlon.exceptions.EmptyOrNullPlayerStringException;
import com.decathlon.exceptions.InvalidPlayerStringException;
import com.decathlon.exceptions.NullDecathlonPlayerException;
import com.decathlon.util.DecathlonParameters;
import com.decathlon.util.DecathlonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/*
 * Uses data of https://en.wikipedia.org/wiki/Decathlon#Benchmarks
 * */
@DisplayName("Test DecathlonUtil class methods which uses Single Player")
public class DecathlonUtilPlayerTest {
    DecathlonPlayer player = new DecathlonPlayer();

    @BeforeEach
    public void buildPlayerObject() {
        player.setName("Menuka Ishan");
        player.setPlayerRank("1");
        player.setHundredMetres(10.395f);
        player.setLongJump(DecathlonUtil.convertToCentiMetres("7.76"));
        player.setShotPut(18.40f);
        player.setHighJump(DecathlonUtil.convertToCentiMetres("2.20"));
        player.setFourHundredMetres(46.17f);
        player.setHundredAndTenHurdles(13.80f);
        player.setDiscusThrow(56.17f);
        player.setPoleVault(DecathlonUtil.convertToCentiMetres("5.28"));
        player.setJavelinThrow(77.19f);
        player.setFifteenHundredsMetres(DecathlonUtil.convertToSeconds("3:53.79"));
    }

    @Test
    @DisplayName("Test player creation success")
    public void testStringToPlayerCreation() {
        String playerString = "Menuka Ishan;10.395;7.76;18.40;2.20;46.17;13.80;56.17;5.28;77.19;3:53.79";
        DecathlonPlayer playerCreated = DecathlonUtil.getPlayersFromString(playerString);
        assertAll("Asserting all player attributed",
                () -> assertEquals("Menuka Ishan", playerCreated.getName()),
                () -> assertEquals(10.395, playerCreated.getHundredMetres(), 0.1),
                () -> assertEquals(776.0, playerCreated.getLongJump()),
                () -> assertEquals(18.40, playerCreated.getShotPut(), 0.1),
                () -> assertEquals(220.0, playerCreated.getHighJump()),
                () -> assertEquals(46.17, playerCreated.getFourHundredMetres(), 0.1),
                () -> assertEquals(13.80, playerCreated.getHundredAndTenHurdles(), 0.1),
                () -> assertEquals(56.17, playerCreated.getDiscusThrow(), 0.1),
                () -> assertEquals(528.0, playerCreated.getPoleVault()),
                () -> assertEquals(77.19, playerCreated.getJavelinThrow(), 0.1),
                () -> assertEquals(233.79, playerCreated.getFifteenHundredsMetres(), 0.1));
    }

    @Test
    @DisplayName("Test Empty Player String creation")
    public void testEmptyPlayerString(){
        assertThrows(EmptyOrNullPlayerStringException.class,
                () -> DecathlonUtil.getPlayersFromString(""));
    }

    @Test
    @DisplayName("Test null Player String creation")
    public void testNullPlayerString(){
        assertThrows(EmptyOrNullPlayerStringException.class,
                () -> DecathlonUtil.getPlayersFromString(null));
    }

    @Test
    @DisplayName("Test Invalid Player String creation")
    public void testInvalidPlayerString(){
        assertThrows(InvalidPlayerStringException.class,
                () -> DecathlonUtil.getPlayersFromString("Menuka Ishan;10.395;7.76;18.40;2.20;46.17;"));
    }

    @Test
    @DisplayName("Test total points success")
    public void testTotalPoints() {
        assertEquals(9990, DecathlonUtil.getTotalPoints(player));
    }

    @Test
    @DisplayName("Test total points null player passed")
    public void testTotalPointsNull() {
        assertThrows(NullDecathlonPlayerException.class,
                () -> DecathlonUtil.getTotalPoints(null));
    }

    @Nested
    @DisplayName("Test methods related to track events")
    class TrackEventTest {
        DecathlonPlayer player2 = new DecathlonPlayer();

        @BeforeEach
        public void buildPlayerObject() {
            player2.setName("Menuka Ishan");
            player2.setPlayerRank("1");
            player2.setHundredMetres(10.395f);
            player2.setLongJump(DecathlonUtil.convertToCentiMetres("7.76"));
            player2.setShotPut(18.40f);
            player2.setHighJump(DecathlonUtil.convertToCentiMetres("2.20"));
            player2.setFourHundredMetres(46.17f);
            player2.setHundredAndTenHurdles(13.80f);
            player2.setDiscusThrow(56.17f);
            player2.setPoleVault(DecathlonUtil.convertToCentiMetres("5.28"));
            player2.setJavelinThrow(77.19f);
            player2.setFifteenHundredsMetres(DecathlonUtil.convertToSeconds("3:53.79"));
        }

        @Test
        @DisplayName("Test hundred metres track success")
        public void testHundredMetres() {
            assertEquals(1000, DecathlonUtil.countTrackEvent(DecathlonParameters.HUNDRED_METRES_A,
                    DecathlonParameters.HUNDRED_METRES_B,
                    DecathlonParameters.HUNDRED_METRES_C,
                    player2.getHundredMetres()));
        }

        @Test
        @DisplayName("Test hundred metres track for zero")
        public void testHundredMetresForZero() {
            assertEquals(4758, DecathlonUtil.countTrackEvent(DecathlonParameters.HUNDRED_METRES_A,
                    DecathlonParameters.HUNDRED_METRES_B,
                    DecathlonParameters.HUNDRED_METRES_C,
                    0));
        }

        @Test
        @DisplayName("Test four hundred metres track success")
        public void testFourHundredMetres() {
            assertEquals(1000, DecathlonUtil.countTrackEvent(DecathlonParameters.FOUR_HUNDRED_METRES_A,
                    DecathlonParameters.FOUR_HUNDRED_METRES_B,
                    DecathlonParameters.FOUR_HUNDRED_METRES_C,
                    player2.getFourHundredMetres()));
        }

        @Test
        @DisplayName("Test four hundred metres track for zero")
        public void testFourHundredMetresForZero() {
            assertEquals(4475, DecathlonUtil.countTrackEvent(DecathlonParameters.FOUR_HUNDRED_METRES_A,
                    DecathlonParameters.FOUR_HUNDRED_METRES_B,
                    DecathlonParameters.FOUR_HUNDRED_METRES_C,
                    0));
        }

        @Test
        @DisplayName("Test hundred and ten metres hurdles track success")
        public void testHundredAndTenMetres() {
            assertEquals(1000, DecathlonUtil.countTrackEvent(DecathlonParameters.HUNDRED_AND_TEN_METRES_A,
                    DecathlonParameters.HUNDRED_AND_TEN_METRES_B,
                    DecathlonParameters.HUNDRED_AND_TEN_METRES_C,
                    player2.getHundredAndTenHurdles()));
        }

        @Test
        @DisplayName("Test hundred and ten metres hurdles track for zero")
        public void testHundredAndTenMetresForZero() {
            assertEquals(3568, DecathlonUtil.countTrackEvent(DecathlonParameters.HUNDRED_AND_TEN_METRES_A,
                    DecathlonParameters.HUNDRED_AND_TEN_METRES_B,
                    DecathlonParameters.HUNDRED_AND_TEN_METRES_C,
                    0));
        }

        @Test
        @DisplayName("Test fifteen hundred metres track success")
        public void testFifteenHundredMetres() {
            assertEquals(1000, DecathlonUtil.countTrackEvent(DecathlonParameters.FIFTEEN_HUNDRED_METRES_A,
                    DecathlonParameters.FIFTEEN_HUNDRED_METRES_B,
                    DecathlonParameters.FIFTEEN_HUNDRED_METRES_C,
                    player2.getFifteenHundredsMetres()));
        }

        @Test
        @DisplayName("Test fifteen hundred metres track for zero")
        public void testFifteenHundredMetresForZero() {
            assertEquals(3438, DecathlonUtil.countTrackEvent(DecathlonParameters.FIFTEEN_HUNDRED_METRES_A,
                    DecathlonParameters.FIFTEEN_HUNDRED_METRES_B,
                    DecathlonParameters.FIFTEEN_HUNDRED_METRES_C,
                    0));
        }

        @Test
        @DisplayName("Test total track events points success")
        public void testTotalTrackEventsPoints() {
            assertEquals(4000, DecathlonUtil.countTotalTrackEventPoints(player2));
        }

        @Test
        @DisplayName("Test total track events points for null player passed")
        public void testTotalTrackEventsPointsNull() {
            assertThrows(NullDecathlonPlayerException.class,
                    () -> DecathlonUtil.countTotalTrackEventPoints(null));
        }

    }


    @Nested
    @DisplayName("Test methods related to field events")
    class FieldEventTest {
        DecathlonPlayer player = new DecathlonPlayer();

        /*
         * Due to countFieldEvent() signature, it is not possible to compile with illegal characters
         * */
        @BeforeEach
        public void buildPlayerObject() {
            player.setName("Menuka Ishan");
            player.setPlayerRank("1");
            player.setHundredMetres(10.395f);
            player.setLongJump(DecathlonUtil.convertToCentiMetres("7.76"));
            player.setShotPut(18.40f);
            player.setHighJump(DecathlonUtil.convertToCentiMetres("2.20"));
            player.setFourHundredMetres(46.17f);
            player.setHundredAndTenHurdles(13.80f);
            player.setDiscusThrow(56.17f);
            player.setPoleVault(DecathlonUtil.convertToCentiMetres("5.28"));
            player.setJavelinThrow(77.19f);
            player.setFifteenHundredsMetres(DecathlonUtil.convertToSeconds("3:53.79"));
        }

        @Test
        @DisplayName("Test Long jump event success")
        public void testLongJump() {
            assertEquals(1000, DecathlonUtil.countFieldEvent(DecathlonParameters.LONG_JUMP_A,
                    DecathlonParameters.LONG_JUMP_B,
                    DecathlonParameters.LONG_JUMP_C,
                    player.getLongJump()));
        }

        @Test
        @DisplayName("Test when zero value for Long jump")
        public void testLongJumpZeroValue() {
            assertEquals(0, DecathlonUtil.countFieldEvent(DecathlonParameters.LONG_JUMP_A,
                    DecathlonParameters.LONG_JUMP_B,
                    DecathlonParameters.LONG_JUMP_C,
                    0));
        }

        @Test
        @DisplayName("Test Shot put event success")
        public void testShotPut() {
            assertEquals(1000, DecathlonUtil.countFieldEvent(DecathlonParameters.SHOT_PUT_A,
                    DecathlonParameters.SHOT_PUT_B,
                    DecathlonParameters.SHOT_PUT_C,
                    player.getShotPut()));
        }

        @Test
        @DisplayName("Test Shot put event for zero")
        public void testShotPutForZero() {
            assertEquals(0, DecathlonUtil.countFieldEvent(DecathlonParameters.SHOT_PUT_A,
                    DecathlonParameters.SHOT_PUT_B,
                    DecathlonParameters.SHOT_PUT_C,
                    0));
        }

        @Test
        @DisplayName("Test High jump event success")
        public void testHighJump() {
            assertEquals(992, DecathlonUtil.countFieldEvent(DecathlonParameters.HIGH_JUMP_A,
                    DecathlonParameters.HIGH_JUMP_B,
                    DecathlonParameters.HIGH_JUMP_C,
                    player.getHighJump()));
        }

        @Test
        @DisplayName("Test High jump event for zero")
        public void testHighJumpForZero() {
            assertEquals(0, DecathlonUtil.countFieldEvent(DecathlonParameters.HIGH_JUMP_A,
                    DecathlonParameters.HIGH_JUMP_B,
                    DecathlonParameters.HIGH_JUMP_C,
                    0));
        }

        @Test
        @DisplayName("Test Discus throw event success")
        public void testDiscusThrow() {
            assertEquals(1000, DecathlonUtil.countFieldEvent(DecathlonParameters.DISCUS_THROW_A,
                    DecathlonParameters.DISCUS_THROW_B,
                    DecathlonParameters.DISCUS_THROW_C,
                    player.getDiscusThrow()));
        }

        @Test
        @DisplayName("Test Discus throw event for zero")
        public void testDiscusThrowForZero() {
            assertEquals(0, DecathlonUtil.countFieldEvent(DecathlonParameters.DISCUS_THROW_A,
                    DecathlonParameters.DISCUS_THROW_B,
                    DecathlonParameters.DISCUS_THROW_C,
                    0));
        }

        @Test
        @DisplayName("Test Pole vault event success")
        public void testPoleVault() {
            assertEquals(998, DecathlonUtil.countFieldEvent(DecathlonParameters.POLE_VAULT_A,
                    DecathlonParameters.POLE_VAULT_B,
                    DecathlonParameters.POLE_VAULT_C,
                    player.getPoleVault()));
        }

        @Test
        @DisplayName("Test Pole vault event for zero")
        public void testPoleVaultForZero() {
            assertEquals(0, DecathlonUtil.countFieldEvent(DecathlonParameters.POLE_VAULT_A,
                    DecathlonParameters.POLE_VAULT_B,
                    DecathlonParameters.POLE_VAULT_C,
                    0));
        }

        @Test
        @DisplayName("Test Javelin throw event success")
        public void testJavelinThrow() {
            assertEquals(1000, DecathlonUtil.countFieldEvent(DecathlonParameters.JAVELIN_THROW_A,
                    DecathlonParameters.JAVELIN_THROW_B,
                    DecathlonParameters.JAVELIN_THROW_C,
                    player.getJavelinThrow()));
        }

        @Test
        @DisplayName("Test Javelin throw event for zero")
        public void testJavelinThrowForZero() {
            assertEquals(0, DecathlonUtil.countFieldEvent(DecathlonParameters.JAVELIN_THROW_A,
                    DecathlonParameters.JAVELIN_THROW_B,
                    DecathlonParameters.JAVELIN_THROW_C,
                    0));
        }

        @Test
        @DisplayName("Test total field events points success")
        public void testTotalFieldEventsPoints() {
            assertEquals(5990, DecathlonUtil.countTotalFieldEventsPoints(player));
        }

        @Test
        @DisplayName("Test total field events points null player passed")
        public void testTotalFieldEventsPointsNull() {
            assertThrows(NullDecathlonPlayerException.class,
                    () -> DecathlonUtil.countTotalFieldEventsPoints(null));
        }
    }


}