package com.uddeshya.explore.model;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class TrackerAnnounceResponse {
    private static final int PEER_ADDRESSES = 10;
    private final int action;
    private final int transactionID;
    private final int interval;
    private final int leechers;
    private final int seeders;
    private final List<Address> addressList;


    public int getAction() {
        return action;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getInterval() {
        return interval;
    }

    public int getLeechers() {
        return leechers;
    }

    public int getSeeders() {
        return seeders;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    @Override
    public String toString() {
        return "TrackerAnnounceResponse{" +
                "action=" + action +
                ", transactionID=" + transactionID +
                ", interval=" + interval +
                ", leechers=" + leechers +
                ", seeders=" + seeders +
                ", addressList=" + addressList +
                '}';
    }

    public static TrackerAnnounceResponse parseBytesToTrackerAnnounceResponse(byte[] responseBuffer) {
        ByteBuffer fetchedBytes = ByteBuffer.wrap(responseBuffer);
        int action = fetchedBytes.getInt();
        int transactionId = fetchedBytes.getInt();
        int interval = fetchedBytes.getInt();
        int leechers = fetchedBytes.getInt();
        int seeders = fetchedBytes.getInt(); // 20 right here.
        List<Address> addressList = new ArrayList<>();
        for (int i = 0; i < PEER_ADDRESSES; i++) {
            int ipInt = fetchedBytes.getInt();
            short port = fetchedBytes.getShort();
            if (ipInt == 0) {
                break;
            }
            addressList.add(Address.newBuilder()
                    .setHost(ipInt)
                    .setPort(port)
                    .build());
        }
        return TrackerAnnounceResponse.newBuilder()
                .setAction(action)
                .setTransactionID(transactionId)
                .setInterval(interval)
                .setLeechers(leechers)
                .setSeeders(seeders)
                .addAllAddress(addressList).build();
    }


    private TrackerAnnounceResponse(int action, int transactionID, int interval, int leechers, int seeders, List<Address> addressList) {
        this.action = action;
        this.transactionID = transactionID;
        this.interval = interval;
        this.leechers = leechers;
        this.seeders = seeders;
        this.addressList = addressList;
    }

    public static TrackerAnnounceResponseBuilder newBuilder() {
        return new TrackerAnnounceResponseBuilder();
    }

    public static class TrackerAnnounceResponseBuilder {
        private int action;
        private int transactionID;
        private int interval;
        private int leechers;
        private int seeders;
        private List<Address> addressList;

        public TrackerAnnounceResponseBuilder setAction(int action) {
            this.action = action;
            return this;
        }

        public TrackerAnnounceResponseBuilder setTransactionID(int transactionID) {
            this.transactionID = transactionID;
            return this;
        }

        public TrackerAnnounceResponseBuilder setInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public TrackerAnnounceResponseBuilder setLeechers(int leechers) {
            this.leechers = leechers;
            return this;
        }

        public TrackerAnnounceResponseBuilder setSeeders(int seeders) {
            this.seeders = seeders;
            return this;
        }

        public TrackerAnnounceResponseBuilder addAllAddress(List<Address> addressList) {
            this.addressList = addressList;
            return this;
        }

        public TrackerAnnounceResponse build() {
            return new TrackerAnnounceResponse(this.action, this.transactionID, this.interval, this.leechers, this.seeders,
                    this.addressList);
        }
    }
}
