package com.uddeshya.explore.model;

public class IntegerParsingResult {
    private int data;
    private boolean success;

    public IntegerParsingResult( boolean success) {
        this.data = 0;
        this.success = success;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
