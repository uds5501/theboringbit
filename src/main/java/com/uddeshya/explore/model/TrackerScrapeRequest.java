package com.uddeshya.explore.model;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadLocalRandom;

import static com.uddeshya.explore.constants.TrackerConstants.ACTION_SCRAPE;

public class TrackerScrapeRequest {
    private long connectionID;
    private final int action = ACTION_SCRAPE;
    private int transactionID;
    private String infoHash;

    public long getConnectionID() {
        return connectionID;
    }

    public int getAction() {
        return action;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public String getInfoHash() {
        return infoHash;
    }

    public TrackerScrapeRequest(long connectionID, String infoHash) {
        this.connectionID = connectionID;
        this.transactionID = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        ;
        this.infoHash = infoHash;
    }

    public byte[] parseRequestToBytes() {
        ByteBuffer buf = ByteBuffer.allocate(56);
        buf.putLong(this.connectionID);
        buf.putInt(this.action);
        buf.putInt(this.transactionID);
        buf.put(this.infoHash.getBytes());
        return buf.array();
    }
}
