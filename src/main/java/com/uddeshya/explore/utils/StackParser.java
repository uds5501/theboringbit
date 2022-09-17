package com.uddeshya.explore.utils;

import com.uddeshya.explore.model.StringParsingResult;

public class StackParser {
    public static StringParsingResult parseStringFromIndex(String original, int i) {

        StringParsingResult result = new StringParsingResult("error", false);
        int stringLength = 0;
        while (original.charAt(i) != ':') {
            if (original.charAt(i) < '0' || original.charAt(i) > '9') {
                return result;
            }
            stringLength = stringLength * 10 + (original.charAt(i) - '0');
            i++;
        }
        i++;
        String str = "";
        result.setStringLength(stringLength);
        while (stringLength != 0) {
            str += original.charAt(i);
            i++;
            stringLength--;
        }
        result.setData(str);
        result.setSuccess(true);
        return result;
    }
}
