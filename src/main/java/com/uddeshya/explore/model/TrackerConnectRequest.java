package com.uddeshya.explore.model;

import java.util.concurrent.ThreadLocalRandom;

import static com.uddeshya.explore.constants.TrackerConstants.ACTION_CONNECT;
import static com.uddeshya.explore.constants.TrackerConstants.TRACKER_CONNECTION_MAGIC_NUMBER;

public class TrackerConnectRequest {
    private final long protocolId = TRACKER_CONNECTION_MAGIC_NUMBER;
    private final int action = ACTION_CONNECT;

    public long getProtocolId() {
        return protocolId;
    }

    public int getAction() {
        return action;
    }

    public int getTransactionID() {
        return transactionID;
    }

    private final int transactionID;

    public TrackerConnectRequest() {
        this.transactionID = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public TrackerConnectRequest(int transactionID) {
        this.transactionID = transactionID;
    }

}
