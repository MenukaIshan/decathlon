package com.decathlon.test;

import static org.junit.jupiter.api.Assertions.*;

import com.decathlon.exceptions.IllegalTimeStringException;
import com.decathlon.exceptions.NullMetresStringException;
import com.decathlon.util.DecathlonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/*
 * Uses data of https://en.wikipedia.org/wiki/Decathlon#Benchmarks
 * */
@DisplayName("Test Conversions Methods in DecathlonUtil class")
public class DecathlonUtilConvertTest {

    @Nested
    @DisplayName("Test the Minutes:Seconds to seconds conversion method")
    class MinutesSecondsConversionTest {
        @Test
        @DisplayName("Test to covert minute:seconds to seconds successfully")
        public void testConvertToSeconds() {
            Assertions.assertEquals(233.79, DecathlonUtil.convertToSeconds("3:53.79"), 0.1);
        }

        @Test
        @DisplayName("Test our come when Illegal Character in minutes field")
        public void testWhenIllegalCharacterInMinutes() {
            assertThrows(NumberFormatException.class,
                    () -> DecathlonUtil.convertToSeconds("y:53.98"));
        }

        @Test
        @DisplayName("Test our come when Illegal Character in seconds field")
        public void testWhenIllegalCharacterInSeconds() {
            assertThrows(NumberFormatException.class,
                    () -> DecathlonUtil.convertToSeconds("3:u3.98"));
        }

        @Test
        @DisplayName("Test when Time String contains two colons ")
        public void testWhenTwoColonsInTimeString() {
            assertThrows(IllegalTimeStringException.class,
                    () -> DecathlonUtil.convertToSeconds("8:9:7"));
        }

        @Test
        @DisplayName("Test when Time String contains multiple colons ")
        public void testWhenMultipleColonsInTimeString() {
            assertThrows(IllegalTimeStringException.class,
                    () -> DecathlonUtil.convertToSeconds("1:93.56:45.21:45.23:10.11"));
        }

        @Test
        @DisplayName("Test when Empty String passed")
        public void testWhenEmptyTimeString() {
            assertThrows(IllegalTimeStringException.class,
                    () -> DecathlonUtil.convertToSeconds(""));
        }

        @Test
        @DisplayName("Test when null String passed")
        public void testWhenNullTimeString() {
            assertThrows(NullPointerException.class,
                    () -> DecathlonUtil.convertToSeconds(null));
        }

        @Test
        @DisplayName("Test Time conversion within 1 nano second")
        public void testTimeDurationForTimeConversion() {
            assertTimeout(Duration.ofNanos(1), () -> DecathlonUtil.convertToSeconds("3:53.79"));
        }
    }

    /*
    * This is required in some tract events, even though wikipedia article take input in metres
    * source - https://www.sportcalculators.com/decathlon-calculator
    * */
    @Nested
    @DisplayName("Test metres to centimeter conversion")
    class MetresToCentiMetresConversionTest {

        @Test
        @DisplayName("Test metres to centimetres successfully")
        public void testMetresToCentimetres() {
            assertEquals(776.0, DecathlonUtil.convertToCentiMetres("7.76"));
        }

        @Test
        @DisplayName("Test metres without decimal points to centimetres ")
        public void testMetresStringWithoutDecimal() {
            assertEquals(500.0, DecathlonUtil.convertToCentiMetres("5"));
        }

        @Test
        @DisplayName("Test Illegal Character in metres string")
        public void testIllegalCharacterInMetresString(){
            assertThrows(NumberFormatException.class,
                    () -> DecathlonUtil.convertToCentiMetres("1m0"));
        }

        @Test
        @DisplayName("Test empty metres string")
        public void testEmptyMetresString(){
            assertThrows(NumberFormatException.class,
                    () -> DecathlonUtil.convertToCentiMetres(""));
        }

        @Test
        @DisplayName("Test null metres string")
        public void testNullMetresString(){
            assertThrows(NullMetresStringException.class,
                    () -> DecathlonUtil.convertToCentiMetres(null));
        }
    }
}
