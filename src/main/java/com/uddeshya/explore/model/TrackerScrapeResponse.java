package com.uddeshya.explore.model;

import java.nio.ByteBuffer;

import static com.uddeshya.explore.constants.TrackerConstants.ACTION_ERROR;

public class TrackerScrapeResponse {
    private int action;
    private int transactionId;
    private int seeders;
    private int completed;
    private int leechers;
    private String error;


    public TrackerScrapeResponse(int action, int transactionId, int seeders, int completed, int leechers, String error) {
        this.action = action;
        this.transactionId = transactionId;
        this.seeders = seeders;
        this.completed = completed;
        this.leechers = leechers;
        this.error = error;
    }

    public int getAction() {
        return action;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getSeeders() {
        return seeders;
    }

    public int getCompleted() {
        return completed;
    }

    public int getLeechers() {
        return leechers;
    }

    public String getError() {
        return error;
    }

    public static TrackerScrapeResponseBuilder newBuilder() {
        return new TrackerScrapeResponseBuilder();
    }

    @Override
    public String toString() {
        return "TrackerScrapeResponse{" +
                "action=" + action +
                ", transactionId=" + transactionId +
                ", seeders=" + seeders +
                ", completed=" + completed +
                ", leechers=" + leechers +
                ", error='" + error + '\'' +
                '}';
    }

    public static TrackerScrapeResponse parseBytesToTrackerScrapeResponse(byte[] responseBuffer) {
        ByteBuffer fetchedBytes = ByteBuffer.wrap(responseBuffer);
        int action = fetchedBytes.getInt();
        int transactionId = fetchedBytes.getInt();
        TrackerScrapeResponse.TrackerScrapeResponseBuilder builder = TrackerScrapeResponse.newBuilder()
                .setAction(action)
                .setTransactionId(transactionId);
        if (action == ACTION_ERROR) {
            builder = builder.setError(new String(fetchedBytes.array()));
        } else {
            builder = builder.setSeeders(fetchedBytes.getInt())
                    .setCompleted(fetchedBytes.getInt())
                    .setLeechers(fetchedBytes.getInt());
        }
        return builder.build();
    }

    public static class TrackerScrapeResponseBuilder {
        private int action;
        private int transactionId;
        private int seeders;
        private int completed;
        private int leechers;
        private String error;

        public TrackerScrapeResponseBuilder setAction(int action) {
            this.action = action;
            return this;
        }

        public TrackerScrapeResponseBuilder setTransactionId(int transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public TrackerScrapeResponseBuilder setSeeders(int seeders) {
            this.seeders = seeders;
            return this;
        }

        public TrackerScrapeResponseBuilder setCompleted(int completed) {
            this.completed = completed;
            return this;
        }

        public TrackerScrapeResponseBuilder setLeechers(int leechers) {
            this.leechers = leechers;
            return this;
        }

        public TrackerScrapeResponseBuilder setError(String error) {
            this.error = error;
            return this;
        }

        public TrackerScrapeResponse build() {
            return new TrackerScrapeResponse(action, transactionId, seeders, completed, leechers, error);
        }
    }
}
