package com.uddeshya.explore.utils;

import com.uddeshya.explore.model.BencodeObject;

import java.util.List;
import java.util.Map;

public class BencodeToString {
    public static String convertFromList(List<BencodeObject> givenList) {
        String result = "LIST[";
        for (BencodeObject bo : givenList) {
            result += bo.toString() + ",";
        }
        result = result.substring(0, result.length() - 1);
        result += "]";
        return result;
    }

    public static String convertFromMap(Map<String, BencodeObject> givenDict) {
        String result = "DICT{";
        for (Map.Entry<String, BencodeObject> entry : givenDict.entrySet()) {
            result += entry.getKey() + ":" + entry.getValue().toString() + ",";
        }
        result = result.substring(0, result.length() - 1);
        result += "}";
        return result;
    }
}
