package com.uddeshya.explore.model;

public class StringParsingResult {
    private String data;
    private int stringLength;
    private boolean success;

    public StringParsingResult(String data, boolean success) {
        this.data = data;
        this.success = success;
        this.stringLength = 0;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStringLength() {
        return stringLength;
    }

    public void setStringLength(int stringLength) {
        this.stringLength = stringLength;
    }

    public int getStringPrefixLength() {
        if (!this.data.isBlank()) {
            return String.valueOf(this.data.length()).length() + 1;
        }
        return 0;
    }
}

