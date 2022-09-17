package com.uddeshya.explore.model;

import com.uddeshya.explore.utils.BencodeToString;

import java.util.List;
import java.util.Map;

public class BencodeObject<T> {
    private T objectData;
    private BencodeObjectType objectType;

    public BencodeObject(T obj, BencodeObjectType type) {
        objectData = obj;
        objectType = type;
    }

    public T getObjectData() {
        return objectData;
    }

    public BencodeObjectType getObjectType() {
        return objectType;
    }

    public int getValueAsInt() {
        return objectType == BencodeObjectType.INTEGER ? (int) objectData : 0;
    }

    public String getValueAsString() {
        return objectType == BencodeObjectType.STRING || objectType == BencodeObjectType.SPECIAL ? (String) objectData : "";
    }

    public List<BencodeObject> getValueAsList() {
        return objectType == BencodeObjectType.LIST ? (List<BencodeObject>) objectData : List.of();
    }

    public Map<String, BencodeObject> getValueAsDictionary() {
        return objectType == BencodeObjectType.DICTIONARY ? (Map<String, BencodeObject>) objectData : Map.of();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (this.getObjectType() != ((BencodeObject) obj).getObjectType()) {
            return false;
        }
        return this.toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return super.hashCode() + this.toString().length();
    }

    @Override
    public String toString() {
        if (this.objectType == BencodeObjectType.SPECIAL) {
            return "SP<" + getValueAsString() + ">";
        }
        if (this.objectType == BencodeObjectType.STRING) {
            return "\"" + getValueAsString() + "\"";
        }
        if (this.objectType == BencodeObjectType.INTEGER) {
            return "<" + getValueAsInt() + ">";
        }
        if (this.objectType == BencodeObjectType.LIST) {
            return BencodeToString.convertFromList(getValueAsList());
        } else {
            return BencodeToString.convertFromMap(getValueAsDictionary());
        }
    }
}

