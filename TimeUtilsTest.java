package com.ohlat8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TimeUtilsTest {

    @Test
    public void testNegativeInput() {
        String result = TimeUtils.secToTime(-5);
        assertEquals("-1", result, "negative input not handled correctly");
    }

    @Test
    public void testZeroInput() {
        String result = TimeUtils.secToTime(0);
        assertEquals("0:00:00", result, "zero input not handled correctly");
    }

    @ParameterizedTest(name = "Seconds converted to minutes")
    @CsvSource({ "60, 0:01:00", "120, 0:02:00"})
    public void testMinutes(int seconds, String time) {
        String message = "time not converted correctly";
        String result = TimeUtils.secToTime(seconds);
        assertEquals(time, result, message);
    }

    @ParameterizedTest(name = "Seconds converted to hours")
    @CsvSource({ "3600, 1:00:00", "7200, 2:00:00"})
    public void testHours(int seconds, String time) {
        String message = "time not converted correctly";
        String result = TimeUtils.secToTime(seconds);
        assertEquals(time, result, message);
    }

    @ParameterizedTest(name = "Seconds converted to hours minutes and seconds")
    @CsvSource({ "3665, 1:01:05", "3700, 1:01:40", "8274, 2:17:54", "21355, 5:55:55", "31999, 8:53:19", "13856977, 3849:09:37"})
    public void testTime(int seconds, String time) {
        String message = "time not converted correctly";
        String result = TimeUtils.secToTime(seconds);
        assertEquals(time, result, message);
    }

    @ParameterizedTest(name = "Highest possible integer value converted correctly")
    @CsvSource({"2147483647, 596523:14:07"})
    public void testHighestTime(int seconds, String time) {
        String message = "time not converted correctly";
        String result = TimeUtils.secToTime(seconds);
        assertEquals(time, result, message);
    }
}
