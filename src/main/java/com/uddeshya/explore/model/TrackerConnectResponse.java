package com.uddeshya.explore.model;


import java.nio.ByteBuffer;

public class TrackerConnectResponse {
    private final int action;
    private final int transactionID;
    private final long connectionID;

    public int getAction() {
        return action;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public long getConnectionID() {
        return connectionID;
    }

    private TrackerConnectResponse(int action, int transactionID, long connectionID) {
        this.action = action;
        this.transactionID = transactionID;
        this.connectionID = connectionID;
    }

    public static TrackerConnectResponse parseBytesToTrackerResponse(byte[] bytes) {
        ByteBuffer fetchedBytes = ByteBuffer.wrap(bytes);
        return TrackerConnectResponse.newBuilder()
                .setAction(fetchedBytes.getInt())
                .setTransactionID(fetchedBytes.getInt())
                .setConnectionID(fetchedBytes.getLong())
                .build();
    }

    public static TrackerConnectResponseBuilder newBuilder() {
        return new TrackerConnectResponseBuilder();
    }

    public static class TrackerConnectResponseBuilder {
        private int action;
        private int transactionID;
        private long connectionID;

        public TrackerConnectResponseBuilder() {
        }

        public TrackerConnectResponseBuilder setAction(int action) {
            this.action = action;
            return this;
        }

        public TrackerConnectResponseBuilder setTransactionID(int transactionID) {
            this.transactionID = transactionID;
            return this;
        }

        public TrackerConnectResponseBuilder setConnectionID(long connectionID) {
            this.connectionID = connectionID;
            return this;
        }

        public TrackerConnectResponse build() {
            return new TrackerConnectResponse(action, transactionID, connectionID);
        }
    }
}

