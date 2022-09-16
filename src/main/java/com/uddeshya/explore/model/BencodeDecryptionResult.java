package com.uddeshya.explore.model;

public class BencodeDecryptionResult {

    public boolean Success;
    public BencodeObject Data;

    public BencodeDecryptionResult(boolean success, BencodeObject data) {
        this.Success = success;
        this.Data = data;
    }
}
