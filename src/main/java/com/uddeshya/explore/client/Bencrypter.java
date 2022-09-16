package com.uddeshya.explore.client;

import com.uddeshya.explore.constants.Constants;
import com.uddeshya.explore.model.BencodeDecryptionResult;
import com.uddeshya.explore.model.BencodeObject;
import com.uddeshya.explore.model.BencodeObjectType;

import java.util.*;

public class Bencrypter {
    public static BencodeDecryptionResult decrypt(String original) {
        BencodeDecryptionResult result = new BencodeDecryptionResult(false, new BencodeObject("Error", BencodeObjectType.SPECIAL));
        Stack<BencodeObject> stack = new Stack();
        int totalLength = original.length();
        for (int i=0; i<totalLength; i++) {
            if (i == 0) {

                char currentCharacter = original.charAt(i);
                if (currentCharacter >= '0' && currentCharacter <= '9') {

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
                    while (stringLength != 0) {
                        str += original.charAt(i);
                        i++;
                        stringLength--;
                    }
                    if (i != totalLength) {
                        return result;
                    }
                    result.Success = true;
                    result.Data = new BencodeObject(str, BencodeObjectType.STRING);
                    return result;

                } else if (currentCharacter == 'i') {

                    i++;
                    int intData = 0;
                    while (original.charAt(i) != 'e') {
                        if (original.charAt(i) < '0' || original.charAt(i) > '9') {
                            return result;
                        }
                        intData = intData * 10 + (original.charAt(i) - '0');
                        i++;
                    }
                    result.Success = true;
                    result.Data = new BencodeObject(intData, BencodeObjectType.INTEGER);
                    return result;

                } else {

                    stack.push(new BencodeObject('$', BencodeObjectType.SPECIAL));
                    stack.push(new BencodeObject(original.charAt(i), BencodeObjectType.SPECIAL));

                }

            } else {

                while (!stack.peek().getObjectData().equals(Constants.STACK_STARTER) && i < totalLength) {

                    char currentCharacter = original.charAt(i);
                    if (currentCharacter >= '0' && currentCharacter <= '9') {

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
                        while (stringLength != 0) {
                            str += original.charAt(i);
                            i++;
                            stringLength--;
                        }
                        stack.push(new BencodeObject(str, BencodeObjectType.STRING));

                    } else if (currentCharacter == 'i') {

                        i++;
                        int intData = 0;
                        while (original.charAt(i) != 'e') {
                            if (original.charAt(i) < '0' || original.charAt(i) > '9') {
                                return result;
                            }
                            intData = intData * 10 + (original.charAt(i) - '0');
                            i++;
                        }
                        stack.push(new BencodeObject(intData, BencodeObjectType.INTEGER));

                    } else if (currentCharacter == 'l' || currentCharacter == 'd') {

                        stack.push(new BencodeObject(currentCharacter, BencodeObjectType.SPECIAL));
                        i++;

                    } else if (currentCharacter == 'e') {

                        List<BencodeObject> localParsedObjects = new ArrayList<>();
                        while (!stack.peek().equals(Constants.DICTIONARY_STARTER) && !stack.peek().equals(Constants.LIST_STARTER)) {
                            localParsedObjects.add(stack.pop());
                        }
                        Collections.reverse(localParsedObjects);

                        if (stack.peek().equals(Constants.LIST_STARTER)) {

                            stack.pop();
                            stack.push(new BencodeObject(localParsedObjects, BencodeObjectType.LIST));

                        } else {

                            if (localParsedObjects.size() % 2 != 0) return result;
                            stack.pop();
                            Map<String, BencodeObject> localDict = new HashMap<>();
                            for(int ptr = 0; ptr<localParsedObjects.size(); ptr+=2) {
                                localDict.put(localParsedObjects.get(ptr).getValueAsString(), localParsedObjects.get(ptr+1));
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

// String - length:string --> 3:abc
// Integers - i<integer>e --> i39427e
// List --> l1:ai98el1:bee --> [a,9,[b]]
// Dictionary --> d1:a1:b1:cl1:yi8eee --> {"a": "b", "c": ["y", 8]}

// l1:al1:bee
// [$, BencodedObj("L", SPECIAL), BencodedObj("a", STRING), BencodedObj(L, SPECIAL),BencodedObj("b", STRING)  ]
// [$, BencodedObj("L", SPECIAL), BencodedObj("a", STRING), BencodedObj(<"b">, LIST) ]
