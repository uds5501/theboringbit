package com.uddeshya.explore.client;

import com.uddeshya.explore.constants.Constants;
import com.uddeshya.explore.model.*;
import com.uddeshya.explore.utils.StackParser;

import java.util.*;

public class Bencrypter {
    public static BencodeDecryptionResult decrypt(String original) {
        BencodeDecryptionResult result = new BencodeDecryptionResult(false, new BencodeObject("Error", BencodeObjectType.SPECIAL));
        Stack<BencodeObject> stack = new Stack();
        int totalLength = original.length();
        for (int i = 0; i < totalLength; i++) {
            if (i == 0) {

                char currentCharacter = original.charAt(i);
                if (currentCharacter >= '0' && currentCharacter <= '9') {

                    StringParsingResult parsingResult = StackParser.parseStringFromIndex(original, i);
                    if (!parsingResult.isSuccess()) {
                        return result;
                    }
                    i += parsingResult.getStringLength() + parsingResult.getStringPrefixLength();
                    if (i != totalLength) {
                        return result;
                    }
                    result.Success = true;
                    result.Data = new BencodeObject(parsingResult.getData(), BencodeObjectType.STRING);
                    return result;

                } else if (currentCharacter == 'i') {
                    IntegerParsingResult integerParsingResult = StackParser.parseIntegerFromIndex(original, i);
                    if (!integerParsingResult.isSuccess()) {
                        return result;
                    }
                    i += String.valueOf(integerParsingResult.getData()).length() + 2;
                    result.Success = true;
                    result.Data = new BencodeObject(integerParsingResult.getData(), BencodeObjectType.INTEGER);
                    return result;

                } else {

                    stack.push(new BencodeObject("$", BencodeObjectType.SPECIAL));
                    stack.push(new BencodeObject(String.valueOf(original.charAt(i)), BencodeObjectType.SPECIAL));

                }

            } else {

                while (!stack.peek().getObjectData().equals(Constants.STACK_STARTER) && i < totalLength) {

                    char currentCharacter = original.charAt(i);
                    if (currentCharacter >= '0' && currentCharacter <= '9') {
                        StringParsingResult parsingResult = StackParser.parseStringFromIndex(original, i);
                        if (!parsingResult.isSuccess()) {
                            return result;
                        }
                        i += parsingResult.getStringLength() + parsingResult.getStringPrefixLength();
                        stack.push(new BencodeObject(parsingResult.getData(), BencodeObjectType.STRING));

                    } else if (currentCharacter == 'i') {
                        IntegerParsingResult integerParsingResult = StackParser.parseIntegerFromIndex(original, i);
                        if (!integerParsingResult.isSuccess()) {
                            return result;
                        }
                        i += String.valueOf(integerParsingResult.getData()).length() + 2;
                        stack.push(new BencodeObject(integerParsingResult.getData(), BencodeObjectType.INTEGER));

                    } else if (currentCharacter == 'l' || currentCharacter == 'd') {

                        stack.push(new BencodeObject(String.valueOf(currentCharacter), BencodeObjectType.SPECIAL));
                        i++;

                    } else if (currentCharacter == 'e') {
                        i++;
                        List<BencodeObject> localParsedObjects = new ArrayList<>();
                        while (!stack.peek().equals(Constants.DICTIONARY_STARTER) && !stack.peek().equals(Constants.LIST_STARTER)) {
                            localParsedObjects.add(stack.pop());
                        }
                        Collections.reverse(localParsedObjects);

                        if (stack.peek().equals(Constants.LIST_STARTER)) {

                            stack.pop();
                            stack.push(new BencodeObject(localParsedObjects, BencodeObjectType.LIST));

                        } else {

                            if (localParsedObjects.size() % 2 != 0) {
                                return result;
                            }
                            stack.pop();
                            Map<String, BencodeObject> localDict = new TreeMap<>();
                            for (int ptr = 0; ptr < localParsedObjects.size(); ptr += 2) {
                                localDict.put(localParsedObjects.get(ptr).getValueAsString(), localParsedObjects.get(ptr + 1));
                            }
                            stack.push(new BencodeObject(localDict, BencodeObjectType.DICTIONARY));

                        }

                    } else {
                        return result;
                    }
                }

                if (stack.size() == 2) {
                    result.Data = stack.pop();
                    result.Success = true;
                }
            }
        }
        return result;
    }
}
