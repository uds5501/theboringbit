package com.uddeshya.explore.utils;

import com.uddeshya.explore.model.IntegerParsingResult;
import com.uddeshya.explore.model.StringParsingResult;

public class StackParser {
    public static StringParsingResult parseStringFromIndex(String original, int idx) {

        StringParsingResult result = new StringParsingResult("error", false);
        int stringLength = 0;
        while (original.charAt(idx) != ':') {
            if (original.charAt(idx) < '0' || original.charAt(idx) > '9') {
                return result;
            }
            stringLength = stringLength * 10 + (original.charAt(idx) - '0');
            idx++;
        }
        idx++;
        String str = "";
        result.setStringLength(stringLength);
        while (stringLength != 0) {
            str += original.charAt(idx);
            idx++;
            stringLength--;
        }
        result.setData(str);
        result.setSuccess(true);
        return result;
    }

    public static IntegerParsingResult parseIntegerFromIndex(String original, int idx) {
        IntegerParsingResult result = new IntegerParsingResult(false);
        idx++;
        int intData = 0;
        while (original.charAt(idx) != 'e') {
            if (original.charAt(idx) < '0' || original.charAt(idx) > '9') {
                return result;
            }
            intData = intData * 10 + (original.charAt(idx) - '0');
            idx++;
        }
        result.setSuccess(true);
        result.setData(intData);
        return result;
    }
}
