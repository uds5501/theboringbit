package com.uddeshya.explore.constants;

import com.uddeshya.explore.model.BencodeObject;
import com.uddeshya.explore.model.BencodeObjectType;

public class Constants {
    public static final BencodeObject STACK_STARTER = new BencodeObject("$", BencodeObjectType.SPECIAL);
    public static final BencodeObject LIST_STARTER = new BencodeObject("l", BencodeObjectType.SPECIAL);
    public static final BencodeObject DICTIONARY_STARTER = new BencodeObject("d", BencodeObjectType.SPECIAL);
}
