package com.uddeshya.explore.utils;

import com.uddeshya.explore.model.IntegerParsingResult;
import com.uddeshya.explore.model.StringParsingResult;
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

    private static Stream<Arguments> fetchArgumentsAndExpectedInteger() {
        return Stream.of(
                Arguments.of("li21e5:abcdee", 1, 21),
                Arguments.of("i4210e", 0, 4210)
        );
    }
    @ParameterizedTest
    @MethodSource("fetchArgumentsAndExpectedString")
    public void shouldParseNextStringFromGivenIndexCorrectly(String encrytedString, int idx, String expectedString) {
        StringParsingResult result = StackParser.parseStringFromIndex(encrytedString, idx);
        assertEquals(true, result.isSuccess());
        assertEquals(expectedString, result.getData());
    }

    @ParameterizedTest
    @MethodSource("fetchArgumentsAndExpectedInteger")
    public void shouldParseNextIntegerFromGivenIndexCorrectly(String encryptedString, int idx, int expectedInteger) {
        IntegerParsingResult result = StackParser.parseIntegerFromIndex(encryptedString, idx);
        assertEquals(true, result.isSuccess());
        assertEquals(expectedInteger, result.getData());
    }
}
