package com.uddeshya.explore.utils;

import com.uddeshya.explore.model.StringParsingResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackParserTest {
    private static Stream<Arguments> fetchArgumentsAndExpectedString() {
        return Stream.of(
            Arguments.of("li21e5:abcdee", 5, "abcde"),
            Arguments.of("2:ab", 0, "ab")
        );
    }

    @ParameterizedTest
    @MethodSource("fetchArgumentsAndExpectedString")
    public void shouldParseNextStringFromGivenIndexCorrectly(String encrytedString, int idx, String expectedString) {
        StringParsingResult result = StackParser.parseStringFromIndex(encrytedString, idx);
        assertEquals(true, result.isSuccess());
        assertEquals(expectedString, result.getData());
    }
}
