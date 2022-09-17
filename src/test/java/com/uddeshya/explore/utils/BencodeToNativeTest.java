package com.uddeshya.explore.utils;

import com.uddeshya.explore.model.BencodeObject;
import com.uddeshya.explore.model.BencodeObjectType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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

    private static Stream<Arguments> fetchDictAndExpectedString() {
        TreeMap<String, BencodeObject> dict1 = new TreeMap<>();
        dict1.put("a", new BencodeObject("b", BencodeObjectType.STRING));
        dict1.put("ab", new BencodeObject(List.of(
                new BencodeObject("abra", BencodeObjectType.STRING)), BencodeObjectType.LIST));

        TreeMap<String, BencodeObject> dict2 = new TreeMap<>();
        dict2.put("key1", new BencodeObject("v1", BencodeObjectType.STRING));
        dict2.put("key2", new BencodeObject(2, BencodeObjectType.INTEGER));

        return Stream.of(
                Arguments.of(dict1,
                        "DICT{a:\"b\",ab:LIST[\"abra\"]}"
                ),
                Arguments.of(dict2,
                        "DICT{key1:\"v1\",key2:<2>}"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("fetchListAndExpectedString")
    public void shouldConvertBencodedListObjectToStringCorrectly(List<BencodeObject> givenList, String expectedString) {
        String actualString = BencodeToString.convertFromList(givenList);
        assertEquals(expectedString, actualString);
    }

    @ParameterizedTest
    @MethodSource("fetchDictAndExpectedString")
    public void shouldConvertBencodedMapObjectToStringCorrectly(Map<String, BencodeObject> givenMap, String expectedString) {
        String actualString = BencodeToString.convertFromMap(givenMap);
        assertEquals(expectedString, actualString);
    }
}
