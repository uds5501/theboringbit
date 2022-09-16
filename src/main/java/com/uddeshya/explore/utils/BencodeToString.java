package com.uddeshya.explore.utils;

import com.uddeshya.explore.model.BencodeObject;

import java.util.List;
import java.util.Map;

public class BencodeToString {
    public static String convertFromList(List<BencodeObject> givenList) {
        String result = "LIST[";
        for(int i=0; i<givenList.size(); i++) {
            result = result + givenList.get(i).toString() + ",";
        }
        result = result.substring(0, result.length()-1);
        result += "]";
        return result;
    }

    public static String convertFromMap(Map<String, BencodeObject> valueAsDictionary) {
        return "";
    }
}
