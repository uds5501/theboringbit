package com.uddeshya.explore.utils;

import com.uddeshya.explore.model.BencodeObject;
import com.uddeshya.explore.model.BencodeObjectType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BencodeToNativeTest {
    private static Stream<Arguments> fetchListAndExpectedString() {
        return Stream.of(
                Arguments.of(List.of(
                        new BencodeObject("abcd", BencodeObjectType.STRING),
                        new BencodeObject(561, BencodeObjectType.INTEGER),
                        new BencodeObject(List.of(
                                new BencodeObject("creep", BencodeObjectType.STRING)), BencodeObjectType.LIST)),
                        "LIST[\"abcd\",<561>,LIST[\"creep\"]]"
                ),
                Arguments.of(List.of(
                                new BencodeObject("abcd", BencodeObjectType.STRING),
                                new BencodeObject(List.of(
                                        new BencodeObject("creep", BencodeObjectType.STRING),
                                        new BencodeObject(561, BencodeObjectType.INTEGER),
                                        new BencodeObject(189, BencodeObjectType.INTEGER),
                                        new BencodeObject("dance", BencodeObjectType.STRING)), BencodeObjectType.LIST)
                                ),
                        "LIST[\"abcd\",LIST[\"creep\",<561>,<189>,\"dance\"]]"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("fetchListAndExpectedString")
    public void shouldConvertBencodedListObjectToStringCorrectly(List<BencodeObject> givenList, String expectedString) {
        String actualString = BencodeToString.convertFromList(givenList);
        assertEquals(expectedString, actualString);
    }
}
