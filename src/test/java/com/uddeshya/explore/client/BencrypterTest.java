package com.uddeshya.explore.client;

import com.uddeshya.explore.model.BencodeDecryptionResult;
import com.uddeshya.explore.model.BencodeObject;
import com.uddeshya.explore.model.BencodeObjectType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BencrypterTest {
    private static Stream<Arguments> getBencodedStrings() {
        return Stream.of(
                Arguments.of("1:a",
                        new BencodeObject("a", BencodeObjectType.STRING)),
                Arguments.of("67:There are many variations of passages of Lorem Ipsum available, but",
                        new BencodeObject("There are many variations of passages of Lorem Ipsum available, but", BencodeObjectType.STRING)),
                Arguments.of("i58273e",
                        new BencodeObject(58273, BencodeObjectType.INTEGER)),
                Arguments.of("l4:abcdi561el5:creepee",
                        new BencodeObject(List.of(
                                new BencodeObject("abcd", BencodeObjectType.STRING),
                                new BencodeObject(561, BencodeObjectType.INTEGER),
                                new BencodeObject(List.of(
                                        new BencodeObject("creep", BencodeObjectType.STRING)), BencodeObjectType.LIST
                                )
                        ), BencodeObjectType.LIST)),
                Arguments.of("d4:key12:v14:key2i2ee",
                        new BencodeObject(Map.of(
                                "key1", new BencodeObject("v1", BencodeObjectType.STRING),
                                "key2", new BencodeObject(2, BencodeObjectType.INTEGER)
                        ), BencodeObjectType.DICTIONARY)),
                Arguments.of("d2:k1d4:k_l1li21e5:abcdeeee",
                        new BencodeObject(Map.of(
                                "k1", new BencodeObject(Map.of(
                                        "k_l1", new BencodeObject(List.of(
                                                new BencodeObject(21, BencodeObjectType.INTEGER),
                                                new BencodeObject("abcde", BencodeObjectType.STRING)
                                        ), BencodeObjectType.LIST)
                                ), BencodeObjectType.DICTIONARY)
                        ), BencodeObjectType.DICTIONARY))
        );
    }

    @ParameterizedTest
    @MethodSource("getBencodedStrings")
    public void shouldDecryptValidBencodeEncryptedDataCorrectly(String original, BencodeObject expected) {
        BencodeDecryptionResult result = Bencrypter.decrypt(original);
        assertEquals(true, result.Success);
        assertEquals(expected, result.Data);
    }
}
